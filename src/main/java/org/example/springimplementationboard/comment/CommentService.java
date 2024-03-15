package org.example.springimplementationboard.comment;

import lombok.RequiredArgsConstructor;
import org.example.springimplementationboard.Board.BoardEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    public CommentDto createComment(CommentDto commentDto) {
        CommentEntity commentEntity = new CommentEntity(commentDto.id(), commentDto.body(), false, new BoardEntity(commentDto.boardId()));
        commentRepository.save(commentEntity);
        System.out.println(commentEntity);
        return new CommentDto(commentEntity.getId(), commentEntity.getBoard().getId(), commentEntity.getBody());
    }
}
