package cz.abdykili.lundegaard.service;

import cz.abdykili.lundegaard.model.request.RequestTypeRequestDto;
import cz.abdykili.lundegaard.model.response.RequestTypeResponseDto;

import java.util.List;

public interface RequestTypeService {
    RequestTypeResponseDto saveRequestType(RequestTypeRequestDto requestTypeRequestDto);

    List<RequestTypeResponseDto> findAllRequestTypes();

    RequestTypeResponseDto findRequestType(Long id);
}
