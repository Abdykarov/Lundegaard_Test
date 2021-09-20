package cz.abdykili.lundegaard.service.impl;

import cz.abdykili.lundegaard.domain.RequestTypeEntity;
import cz.abdykili.lundegaard.exception.RequestTypeNotFoundException;
import cz.abdykili.lundegaard.mapper.RequestTypeMapper;
import cz.abdykili.lundegaard.model.request.RequestTypeRequestDto;
import cz.abdykili.lundegaard.model.response.RequestTypeResponseDto;
import cz.abdykili.lundegaard.repository.RequestTypeRepository;
import cz.abdykili.lundegaard.service.RequestTypeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@Slf4j
@RequiredArgsConstructor
public class RequestTypeServiceImpl implements RequestTypeService {

    private final RequestTypeRepository requestTypeRepository;
    private final RequestTypeMapper requestTypeMapper;

    @Override
    @Transactional
    public RequestTypeResponseDto saveRequestType(RequestTypeRequestDto requestTypeRequestDto) {
        log.info("Saving a new request type");
        log.debug("Saving a new request type | Incoming request dto - {}", requestTypeRequestDto);
        if (requestTypeRepository.existsByRequestTypeName(requestTypeRequestDto.getRequestTypeName())) {
            throw new RuntimeException("Request type with this name already exists");
        }
        final RequestTypeEntity requestTypeEntity = requestTypeMapper.toEntity(requestTypeRequestDto);
        requestTypeRepository.save(requestTypeEntity);
        log.info("Saving a new request type |  Entity successfully uploaded to database");
        final RequestTypeResponseDto requestTypeResponse = requestTypeMapper.toResponse(requestTypeEntity);
        log.debug("Saving a new request type | Outgoing request type response dto {} ", requestTypeResponse);

        return requestTypeResponse;
    }

    @Override
    @Transactional(readOnly = true)
    public List<RequestTypeResponseDto> findAllRequestTypes() {
        log.info("Fetching all request types");
        final List<RequestTypeEntity> all = requestTypeRepository.findAll();
        final List<RequestTypeResponseDto> collect = all.stream()
                .map(request -> requestTypeMapper.toResponse(request))
                .collect(Collectors.toList());
        log.debug("Fetching all request types | Size : {} , Outgoing list {} ", collect.size(), collect);

        return collect;
    }

    @Override
    @Transactional(readOnly = true)
    public RequestTypeResponseDto findRequestType(Long id) {
        log.info("Fetching request type");
        log.debug("Fetching request type | Incoming request type id - {}", id);
        final RequestTypeEntity requestTypeEntity = requestTypeRepository.findById(id)
                .orElseThrow(() -> new RequestTypeNotFoundException(String.format("Request type with id %s not found", id),
                        HttpStatus.NOT_FOUND));
        log.info("Fetching request type | Entity was successfully found from database, name: {}", requestTypeEntity.getRequestTypeName());
        final RequestTypeResponseDto requestTypeResponse = requestTypeMapper.toResponse(requestTypeEntity);
        log.debug("Fetching request type | Outgoing request response dto {} ", requestTypeResponse);

        return requestTypeResponse;
    }
}
