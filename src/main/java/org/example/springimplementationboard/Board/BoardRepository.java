package org.example.springimplementationboard.Board;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
    @Query("SELECT b FROM BoardEntity b LEFT JOIN FETCH b.comments")
    Page<BoardEntity> findAll(Pageable pageable);
}
