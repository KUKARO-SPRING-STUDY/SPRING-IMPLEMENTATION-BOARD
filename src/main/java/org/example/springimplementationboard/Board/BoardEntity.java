package org.example.springimplementationboard.Board;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
public class BoardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String body;
    @Column(nullable = false, columnDefinition = "boolean default false")
    private boolean isDeleted;

    public BoardEntity(Long id, String title, String body) {
        this.id = id;
        this.title = title;
        this.body = body;
    }
}
