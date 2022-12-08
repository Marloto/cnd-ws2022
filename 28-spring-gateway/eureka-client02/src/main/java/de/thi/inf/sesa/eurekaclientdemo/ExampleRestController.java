package de.thi.inf.sesa.eurekaclientdemo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExampleRestController {
    @GetMapping("/hello")
    public Message hello() {
        return new Message("Hello, World!");
    }
}
