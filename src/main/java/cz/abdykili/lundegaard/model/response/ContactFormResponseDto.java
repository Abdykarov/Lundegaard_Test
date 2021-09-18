package cz.abdykili.lundegaard.model.response;

import cz.abdykili.lundegaard.domain.RequestTypeEntity;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
