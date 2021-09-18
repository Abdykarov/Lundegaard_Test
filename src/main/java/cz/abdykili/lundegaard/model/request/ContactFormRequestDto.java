package cz.abdykili.lundegaard.model.request;

import cz.abdykili.lundegaard.validation.ContactNameConstraint;
import cz.abdykili.lundegaard.validation.ContactSurnameConstraint;
import cz.abdykili.lundegaard.validation.PolicyNumberConstraint;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ContactFormRequestDto {

    @NotBlank
    @PolicyNumberConstraint
    String policyNumber;
    @NotBlank
    @ContactNameConstraint
    String name;
    @NotBlank
    @ContactSurnameConstraint
    String surname;
    @NotBlank
    @Size(max = 512)
    String requestMessage;
    @NotNull
    Long requestTypeId;

}
