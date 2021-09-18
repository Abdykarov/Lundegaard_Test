package cz.abdykili.lundegaard.model.response;


import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RequestTypeResponseDto {
    Long id;
    String requestTypeName;
}
