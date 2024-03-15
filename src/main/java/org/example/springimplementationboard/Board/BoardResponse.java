package org.example.springimplementationboard.Board;

import java.util.List;

public record BoardResponse(
        List<BoardDto> data
) {
}
