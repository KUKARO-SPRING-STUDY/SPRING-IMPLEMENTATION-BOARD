package org.example.springimplementationboard.Board;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public record BoardEntity(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        String id,
        String title,
        String body
) {
}
