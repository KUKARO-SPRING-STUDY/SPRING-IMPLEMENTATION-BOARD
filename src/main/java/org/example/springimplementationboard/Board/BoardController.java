package org.example.springimplementationboard.Board;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/boards")
public class BoardController {
    private final BoardService boardService;

    @PostMapping("")
    public BoardDto createBoard(BoardRequest boardRequest) {
        return boardService.createBoard(new BoardDto(null, boardRequest.title(), boardRequest.body()));
    }
}
