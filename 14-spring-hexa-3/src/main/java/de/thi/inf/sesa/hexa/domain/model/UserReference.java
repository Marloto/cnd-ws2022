package de.thi.inf.sesa.hexa.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
public class UserReference {
    private final UUID reference;
}
