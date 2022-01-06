package br.com.alura.forum.controller.dto;

import br.com.alura.forum.models.Response;
import br.com.alura.forum.models.Topic;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class ResponseDto {
    private Long id;
    private String message;
    private LocalDateTime creationDate;
    private String authorName;

    public ResponseDto(Response response) {
        this.id = response.getId();
        this.message = response.getMensagem();
        this.creationDate = response.getDataCriacao();
        this.authorName = response.getAutor().getNome();
    }

    public static List<ResponseDto> converter(List<Response> responses){
        return responses.stream().map(ResponseDto::new).collect(Collectors.toList());
    }

    public Long getId() {
        return id;
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
}
