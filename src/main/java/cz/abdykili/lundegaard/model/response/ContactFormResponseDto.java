package cz.abdykili.lundegaard.model.response;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ContactFormResponseDto {
    Long id;
    String policyNumber;
    String name;
    String surname;
    String requestMessage;
    RequestTypeResponseDto requestType;
}
