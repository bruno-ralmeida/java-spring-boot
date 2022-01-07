package br.com.alura.forum.controller;

import br.com.alura.forum.controller.dto.TopicDetailDto;
import br.com.alura.forum.controller.dto.TopicDto;
import br.com.alura.forum.controller.form.TopicForm;
import br.com.alura.forum.controller.form.TopicUpdateForm;
import br.com.alura.forum.models.Topic;
import br.com.alura.forum.repository.CourseReposotiry;
import br.com.alura.forum.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/topics")
public class TopicController {

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private CourseReposotiry courseReposotiry;

    @GetMapping
    public Page<TopicDto> list(@RequestParam(required = false) String courseName, Pageable pageable) {

        Page<Topic> topics = (courseName == null) ? topicRepository.findAll(pageable) : topicRepository.findByCursoName(courseName, pageable);

        return TopicDto.converter(topics);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicDetailDto> detail(@PathVariable Long id) {
        Optional<Topic> topic = topicRepository.findById(id);

        return topic.isPresent() ? ResponseEntity.ok(new TopicDetailDto(topic.get())) : ResponseEntity.notFound().build();
    }

    @PostMapping
    @Transactional
    public ResponseEntity<TopicDto> register(@RequestBody @Valid TopicForm form, UriComponentsBuilder uriComponentsBuilder) {
        Topic topic = form.converter(courseReposotiry);
        topicRepository.save(topic);

        URI uri = uriComponentsBuilder.path("/topics/{id}").buildAndExpand(topic.getId()).toUri();

        return ResponseEntity.created(uri).body(new TopicDto(topic));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<TopicDto> update(@PathVariable Long id, @RequestBody @Valid TopicUpdateForm form) {
        Optional<Topic> optionalTopic = topicRepository.findById(id);

        if (optionalTopic.isPresent()) {
            Topic topic = form.update(id, topicRepository);
            return ResponseEntity.ok(new TopicDto(topic));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id) {
        Optional<Topic> topic = topicRepository.findById(id);

        if (topic.isPresent()) {
            topicRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }

}

