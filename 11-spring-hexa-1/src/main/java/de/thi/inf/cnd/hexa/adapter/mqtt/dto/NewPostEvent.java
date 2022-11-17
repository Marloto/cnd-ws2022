package de.thi.inf.cnd.hexa.adapter.mqtt.dto;

import lombok.Data;

@Data
public class NewPostEvent {
    private String id;
    private String title;
    private String content;
}
