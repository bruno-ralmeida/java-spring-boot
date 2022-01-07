package br.com.alura.forum.repository;

import br.com.alura.forum.models.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TopicRepository  extends JpaRepository<Topic, Long> {
    Page<Topic> findByCursoName(String courseName, Pageable pageable);
}
