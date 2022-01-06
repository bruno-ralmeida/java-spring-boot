package br.com.alura.forum.controller.dto;

import br.com.alura.forum.models.Response;
import br.com.alura.forum.models.StatusTopic;
import br.com.alura.forum.models.Topic;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TopicDetailDto {
    private Long id;
    private String title;
    private String message;
    private LocalDateTime creationDate;
    private String authorName;
    private StatusTopic status;
    private List<ResponseDto> responses;

    public TopicDetailDto(Topic topic){
        this.id = topic.getId();
        this.title = topic.getTitulo();
        this.message = topic.getMensagem();
        this.creationDate = topic.getDataCriacao();
        //this.authorName = topic.getAutor().getNome();
        this.status = topic.getStatus();
        this.responses =  new ArrayList<>();
        this.responses.addAll(topic.getRespostas().stream().map(ResponseDto::new).collect(Collectors.toList()));
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

    public String getAuthorName() {
        return authorName;
    }

    public StatusTopic getStatus() {
        return status;
    }

    public List<ResponseDto> getResponses() {
        return responses;
    }
}
