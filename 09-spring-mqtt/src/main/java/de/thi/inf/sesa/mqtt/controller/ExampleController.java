package de.thi.inf.sesa.mqtt.controller;

import de.thi.inf.sesa.mqtt.services.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/example")
public class ExampleController {
    @Autowired
    public PublisherService service;

    @GetMapping
    public ResponseEntity doSomething() {
        this.service.publish("Test " + System.currentTimeMillis());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}