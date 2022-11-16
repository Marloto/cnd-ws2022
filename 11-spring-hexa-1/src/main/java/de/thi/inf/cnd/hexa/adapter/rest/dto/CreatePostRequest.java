package de.thi.inf.cnd.hexa.adapter.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreatePostRequest {
    private String title;
    private String content;
}
