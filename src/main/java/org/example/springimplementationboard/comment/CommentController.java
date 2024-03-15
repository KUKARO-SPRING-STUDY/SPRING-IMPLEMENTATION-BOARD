package org.example.springimplementationboard.comment;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController {
    private final CommentService commentService;

    @PostMapping("")
    public CommentResponse createComment(@RequestBody CommentRequest commentRequest) {
        CommentDto dto = commentService.createComment(new CommentDto(null, commentRequest.boardId(), commentRequest.body()));
        return new CommentResponse(List.of(dto));
    }
}
