package org.example.springimplementationboard.Board;

import lombok.RequiredArgsConstructor;
import org.example.springimplementationboard.comment.CommentDto;
import org.example.springimplementationboard.common.exception.DataNotFoundException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    public BoardDto createBoard(BoardDto dto) {
        BoardEntity boardEntity = new BoardEntity(null, dto.title(), dto.body());
        boardRepository.save(boardEntity);
        return new BoardDto(boardEntity.getId(),
                boardEntity.getTitle(),
                boardEntity.getBody(),
                getCommentsByBoardEntity(boardEntity));
    }

    /**
     * pageable대신 직접 넣어서 내부에서 Pageable초기화하는 방법도 가능
     */
    public List<BoardDto> getBoards(Pageable pageable, boolean hasBody) {
        List<BoardEntity> boardEntities = boardRepository.findAll(pageable)
                .stream()
                .toList();
        return boardEntities.stream()
                .filter(board -> !board.isDeleted())
                .map(boardEntity -> new BoardDto(boardEntity.getId(),
                        boardEntity.getTitle(),
                        getBody(boardEntity, hasBody),
                        getCommentsByBoardEntity(boardEntity)))
                .toList();
    }

    public BoardDto getBoardById(Long id) {
        BoardEntity boardEntity = boardRepository
                .findById(id)
                .filter(board -> !board.isDeleted())
                .orElseThrow(() -> new DataNotFoundException(id, "Board not found"));
        return new BoardDto(boardEntity.getId(),
                boardEntity.getTitle(),
                boardEntity.getBody(),
                getCommentsByBoardEntity(boardEntity));
    }

    public BoardDto updateBoard(BoardDto dto) {
        BoardEntity existBoard = boardRepository
                .findById(dto.id())
                .orElseThrow(() -> new DataNotFoundException(dto.id(), "Board not found"));
        if (existBoard.isDeleted()) {
            throw new DataNotFoundException(dto.id(), "Board is deleted");
        }

        BoardEntity updatedBoard = boardRepository.save(makeBoardEntityByExistBoardAndBoardDto(dto, existBoard));
        return new BoardDto(updatedBoard.getId(),
                updatedBoard.getTitle(),
                updatedBoard.getBody(),
                getCommentsByBoardEntity(updatedBoard));
    }

    private String getBody(BoardEntity boardEntity, boolean hasBody) {
        if (hasBody) {
            return boardEntity.getBody();
        }
        return null;
    }


    private List<CommentDto> getCommentsByBoardEntity(BoardEntity boardEntity) {
        return boardEntity.getComments()
                .stream()
                .map(commentEntity -> new CommentDto(commentEntity.getId(), null, commentEntity.getBody()))
                .toList();
    }

    private BoardEntity makeBoardEntityByExistBoardAndBoardDto(BoardDto dto, BoardEntity entity) {
        return new BoardEntity(entity.getId(),
                dto.title() == null ? entity.getTitle() : dto.title(),
                dto.body() == null ? entity.getBody() : dto.body(),
                entity.isDeleted());
    }

    public void deleteBoard(Long id) {
        BoardEntity existBoard = boardRepository
                .findById(id)
                .orElseThrow(() -> new DataNotFoundException(id, "Board not found"));
        if (existBoard.isDeleted()) {
            throw new DataNotFoundException(id, "Board is deleted");
        }
        BoardEntity deletedBoard = new BoardEntity(existBoard.getId(), existBoard.getTitle(), existBoard.getBody(), true);
        boardRepository.save(deletedBoard);
    }
}
