package org.example.springimplementationboard.Board;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/boards")
public class BoardController {
    private final BoardService boardService;

    @PostMapping("")
    public BoardResponse createBoard(@RequestBody BoardRequest boardRequest) {
        BoardDto board = boardService.createBoard(new BoardDto(null, boardRequest.title(), boardRequest.body()));
        return new BoardResponse(List.of(board));
    }

    @GetMapping("")
    public BoardResponse getBoards(Pageable pageable) {
        List<BoardDto> boards = boardService.getBoards(pageable);
        return new BoardResponse(boards);
    }

    @GetMapping("/{id}")
    public BoardResponse getBoardsById(@PathVariable Long id) {
        BoardDto board = boardService.getBoardById(id);
        return new BoardResponse(List.of(board));
    }

    @PatchMapping("")
    public BoardResponse updateBoard(@RequestBody BoardRequest boardRequest) {
        BoardDto board = boardService.updateBoard(new BoardDto(boardRequest.id(), boardRequest.title(), boardRequest.body()));
        return new BoardResponse(List.of(board));
    }

    @DeleteMapping("/{id}")
    public BoardResponse deleteBoard(@PathVariable Long id) {
        boardService.deleteBoard(id);
        return new BoardResponse(List.of());
    }
}
