package cz.abdykili.lundegaard.domain;

import cz.abdykili.lundegaard.validation.ContactNameConstraint;
import cz.abdykili.lundegaard.validation.ContactSurnameConstraint;
import cz.abdykili.lundegaard.validation.PolicyNumberConstraint;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

@Data
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ContactFormEntity extends AbstractDateEntity {
    @PolicyNumberConstraint
    @NotBlank
    String policyNumber;
    @NotBlank
    @ContactNameConstraint
    String name;
    @NotBlank
    @ContactSurnameConstraint
    String surname;
    @NotBlank
    @Column(length = 512)
    String requestMessage;
    @ManyToOne
    @JoinColumn(name = "request_type_id")
    RequestTypeEntity requestType;
}
