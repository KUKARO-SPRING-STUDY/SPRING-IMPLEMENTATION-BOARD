package org.example.springimplementationboard.Board;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/boards")
public class BoardController {
    private final BoardService boardService;

    @PostMapping("")
    public BoardDto createBoard(@RequestBody BoardRequest boardRequest) {
        return boardService.createBoard(new BoardDto(null, boardRequest.title(), boardRequest.body()));
    }

    @GetMapping("")
    public List<BoardDto> getBoards() {
        return boardService.getBoards();
    }
}
