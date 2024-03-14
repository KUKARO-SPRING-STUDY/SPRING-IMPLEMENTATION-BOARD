package org.example.springimplementationboard.comment;

import jakarta.persistence.*;
import org.example.springimplementationboard.Board.BoardEntity;

@Entity
public record CommentEntity(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        String id,
        String body,
//        @ManyToOne(fetch = FetchType.LAZY)
        @ManyToOne
        @JoinColumn(name = "id")
        BoardEntity board
) {
}
