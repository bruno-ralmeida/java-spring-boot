package br.com.alura.forum.repository;

import br.com.alura.forum.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseReposotiry extends JpaRepository<Course, Long> {

    public Course findByName(String courseName);
}
