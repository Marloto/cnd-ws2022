package de.thi.inf.sesa.configclientdemo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExampleRestController {
    @Value("${sesa.example:unknown}")
    private String msg;

    @GetMapping("/hello")
    public Message hello() {
        return new Message(msg);
    }
}
