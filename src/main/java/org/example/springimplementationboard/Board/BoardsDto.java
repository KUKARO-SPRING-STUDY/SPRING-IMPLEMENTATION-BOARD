package org.example.springimplementationboard.Board;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

public record BoardsDto(
        List<BoardDto> boards,
        Long cursor,
        boolean hasMore
) {
}
