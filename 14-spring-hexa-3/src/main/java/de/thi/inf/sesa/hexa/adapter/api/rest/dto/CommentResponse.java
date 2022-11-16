package de.thi.inf.sesa.hexa.adapter.api.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentResponse {
    private UUID id;
    private String content;
    private LocalDateTime date;
}
