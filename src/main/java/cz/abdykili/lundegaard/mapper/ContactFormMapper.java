package cz.abdykili.lundegaard.mapper;

import cz.abdykili.lundegaard.domain.ContactFormEntity;
import cz.abdykili.lundegaard.model.request.ContactFormRequestDto;
import cz.abdykili.lundegaard.model.response.ContactFormResponse;
import cz.abdykili.lundegaard.model.response.ContactFormResponseDto;
import org.mapstruct.Mapper;

@Mapper
public interface ContactFormMapper {

    ContactFormEntity toEntity(ContactFormRequestDto contactFormRequestDto);

    ContactFormResponseDto toDtoResponse(ContactFormEntity contactFormEntity);

    ContactFormResponse toResponse(ContactFormEntity contactFormEntity);

}
