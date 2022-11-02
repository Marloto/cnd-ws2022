package de.thi.inf.cnd.spring.controller;

import java.util.UUID;

public class Example {
    private UUID id;
    private String something;

    public Example() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getSomething() {
        return something;
    }

    public void setSomething(String something) {
        this.something = something;
    }
}
