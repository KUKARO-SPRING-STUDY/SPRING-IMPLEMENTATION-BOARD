package org.example.springimplementationboard.Board;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    public BoardDto createBoard(BoardDto boardDto) {
        BoardEntity boardEntity = new BoardEntity(null, boardDto.title(), boardDto.body());
        BoardEntity newEntity = boardRepository.save(boardEntity);
        return new BoardDto(newEntity.id(), boardEntity.title(), boardEntity.body());
    }
}
