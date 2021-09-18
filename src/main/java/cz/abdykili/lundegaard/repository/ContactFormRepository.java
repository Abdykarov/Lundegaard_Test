package cz.abdykili.lundegaard.repository;

import cz.abdykili.lundegaard.domain.ContactFormEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactFormRepository extends JpaRepository<ContactFormEntity, Long> {
}
