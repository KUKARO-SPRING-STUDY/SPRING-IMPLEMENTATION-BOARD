package org.example.springimplementationboard.Board;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    public BoardDto createBoard(BoardDto boardDto) {
        BoardEntity boardEntity = new BoardEntity(null, boardDto.title(), boardDto.body());
        BoardEntity newEntity = boardRepository.save(boardEntity);
        return new BoardDto(newEntity.getId(), boardEntity.getTitle(), boardEntity.getBody());
    }

    public List<BoardDto> getBoards() {
        List<BoardEntity> boardEntities = boardRepository.findAll();
        return boardEntities.stream()
                .map(boardEntity -> new BoardDto(boardEntity.getId(), boardEntity.getTitle(), boardEntity.getBody()))
                .toList();
    }
}
