package de.thi.inf.sesa.hexa.adapter.mqtt.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class NewPostCreatedEvent {
    private UUID id;
    private String title;
    private UUID userRef;
}
