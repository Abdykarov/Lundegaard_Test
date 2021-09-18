package cz.abdykili.lundegaard.repository;

import cz.abdykili.lundegaard.domain.RequestTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestTypeRepository extends JpaRepository<RequestTypeEntity, Long> {
    boolean existsByRequestTypeName(String requestTypeName);
}
