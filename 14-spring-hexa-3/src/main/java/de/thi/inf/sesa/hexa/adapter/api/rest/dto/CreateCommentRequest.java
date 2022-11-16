package de.thi.inf.sesa.hexa.adapter.api.rest.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateCommentRequest {
    private String content;
}
