package de.thi.inf.cnd.spring.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/example")
public class ExampleController {
    @GetMapping("/")
    public String listAll() {
        return "List";
    }
    @GetMapping("/{id}")
    public Example listOne(@PathVariable String id) {
        return new Example();
    }
    @PostMapping("/")
    public String addOne(@RequestBody Example example) {
        return "Add";
    }
    @DeleteMapping("/{id}")
    public String deleteOne() {
        return "Delete";
    }
    @PutMapping("/{id}")
    public String updateOne() {
        return "Update";
    }
}
