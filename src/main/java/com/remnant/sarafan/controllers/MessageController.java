package com.remnant.sarafan.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.remnant.sarafan.exceptions.NotFoundException;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("message")
public class MessageController {
    private int counter = 4;
    private List<Map<String, String>> messages = new ArrayList<Map<String, String>>() {{
        add(new HashMap<String, String>() {{ put("id", "1"); put("text", "First Message"); }});
        add(new HashMap<String, String>() {{ put("id", "2"); put("text", "Second Message"); }});
        add(new HashMap<String, String>() {{ put("id", "3"); put("text", "Third Message"); }});
    }};

    @GetMapping
    public List<Map<String, String>> list() {
        return messages;
    }
    
    @GetMapping("{id}")
    public Map<String, String> show(@PathVariable String id) {
        return search(id);
    }

    @PostMapping
    public Map<String, String> store(@RequestBody Map<String, String> message) {
        message.put("id", String.valueOf(counter++));
        messages.add(message);

        return message;
    }

    @PutMapping("{id}")
    public Map<String, String> update(@PathVariable String id, @RequestBody Map<String, String> message) {
        Map<String, String> messageFound = search(id);
        messageFound.putAll(message);
        messageFound.put("id", id);

        return messageFound;
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable String id) {
        Map<String, String> message = search(id);
        messages.remove(message);
    }

    private Map<String, String> search(String id) {
        return messages.stream().filter(message -> message.get("id").equals(id)).findFirst()
                .orElseThrow(NotFoundException::new);
    }
}