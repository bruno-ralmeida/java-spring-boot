package br.com.alura.forum.controller.dto;

import br.com.alura.forum.models.Topic;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class TopicDto {
    private Long id;
    private String title;
    private String message;
    private LocalDateTime creationDate;

    public TopicDto(Topic topic){
        this.id = topic.getId();
        this.title = topic.getTitulo();
        this.message = topic.getMensagem();
        this.creationDate = topic.getDataCriacao();
    }

    public static List<TopicDto> converter(List<Topic> topics){
        return topics.stream().map(TopicDto::new).collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }
}