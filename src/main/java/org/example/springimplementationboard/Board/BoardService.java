package org.example.springimplementationboard.Board;

import lombok.RequiredArgsConstructor;
import org.example.springimplementationboard.common.exception.DataNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    public BoardDto createBoard(BoardDto dto) {
        BoardEntity boardEntity = new BoardEntity(null, dto.title(), dto.body());
        BoardEntity newEntity = boardRepository.save(boardEntity);
        return new BoardDto(newEntity.getId(), boardEntity.getTitle(), boardEntity.getBody());
    }

    public List<BoardDto> getBoards() {
        List<BoardEntity> boardEntities = boardRepository.findAll();
        return boardEntities.stream()
                .map(boardEntity -> new BoardDto(boardEntity.getId(), boardEntity.getTitle(), boardEntity.getBody()))
                .toList();
    }

    public BoardDto getBoardById(Long id) {
        BoardEntity boardEntity = boardRepository
                .findById(id)
                .orElseThrow(() -> new DataNotFoundException(id, "Board not found"));
        return new BoardDto(boardEntity.getId(), boardEntity.getTitle(), boardEntity.getBody());
    }

    public BoardDto updateBoard(BoardDto dto) {
        BoardEntity existBoard = boardRepository
                .findById(dto.id())
                .orElseThrow(() -> new DataNotFoundException(dto.id(), "Board not found"));
        if (existBoard.isDeleted()) {
            throw new DataNotFoundException(dto.id(), "Board is deleted");
        }

        BoardEntity updatedBoard = boardRepository.save(makeBoardEntityByExistBoardAndBoardDto(dto, existBoard));
        return new BoardDto(updatedBoard.getId(), updatedBoard.getTitle(), updatedBoard.getBody());
    }

    private BoardEntity makeBoardEntityByExistBoardAndBoardDto(BoardDto dto, BoardEntity entity) {
        return new BoardEntity(entity.getId(),
                dto.title() == null ? entity.getTitle() : dto.title(),
                dto.body() == null ? entity.getBody() : dto.body(),
                entity.isDeleted());
    }
}
