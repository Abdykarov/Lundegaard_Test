package cz.abdykili.lundegaard.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RequestTypeEntity extends AbstractDateEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String requestName;
}
