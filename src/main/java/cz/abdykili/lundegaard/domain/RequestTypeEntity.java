package cz.abdykili.lundegaard.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RequestTypeEntity extends AbstractDateEntity {
    @Column(unique = true)
    String requestTypeName;
}
