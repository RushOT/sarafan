package com.remnant.sarafan.controllers;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;
import com.remnant.sarafan.domain.Message;
import com.remnant.sarafan.domain.jsonViews.MessageView;
import com.remnant.sarafan.repositories.MessageRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("messages")
public class MessageController {
    private final MessageRepository messageRepository;

    @Autowired
    public MessageController(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @GetMapping
    @JsonView(MessageView.IdName.class)
    public List<Message> list() {
        return messageRepository.findAll();
    }
    
    @GetMapping("{id}")
    @JsonView(MessageView.IdName.class)
    public Message show(@PathVariable("id") Message message) {
        return message;
    }

    @PostMapping
    public Message store(@RequestBody Message message) {
        message.setCreationDate(LocalDateTime.now());
        messageRepository.save(message);
        return message;
    }

    @PutMapping("{id}")
    public Message update(
        @PathVariable("id") Message currentMessage,
        @RequestBody Message message
    ) {
        BeanUtils.copyProperties(message, currentMessage, "id");

        return messageRepository.save(currentMessage);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Message message) {
        messageRepository.delete(message);
    }
}