package cz.abdykili.lundegaard.model.request;


import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RequestTypeRequestDto {
    @NotBlank
    String requestTypeName;
}
