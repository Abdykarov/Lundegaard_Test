package cz.abdykili.lundegaard.mapper;

import cz.abdykili.lundegaard.domain.RequestTypeEntity;
import cz.abdykili.lundegaard.model.request.RequestTypeRequestDto;
import cz.abdykili.lundegaard.model.response.RequestTypeResponseDto;
import org.mapstruct.Mapper;

@Mapper(uses = ContactFormMapper.class)
public interface RequestTypeMapper {

    RequestTypeEntity toEntity(RequestTypeRequestDto requestTypeRequestDto);

    RequestTypeResponseDto toResponse(RequestTypeEntity requestTypeEntity);
}
