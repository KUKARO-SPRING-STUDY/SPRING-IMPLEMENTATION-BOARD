package org.example.springimplementationboard.comment;

public record CommentRequest(
        Long boardId,
        String body
) {
}
