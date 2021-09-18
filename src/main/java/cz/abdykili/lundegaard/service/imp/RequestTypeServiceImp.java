package cz.abdykili.lundegaard.service.imp;

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

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class RequestTypeServiceImp implements RequestTypeService{

    private final RequestTypeRepository requestTypeRepository;
    private final RequestTypeMapper requestTypeMapper;

    @Override
    public RequestTypeResponseDto saveRequestType(RequestTypeRequestDto requestTypeRequestDto) {
        if(requestTypeRepository.existsByRequestTypeName(requestTypeRequestDto.getRequestTypeName())){
            throw new RuntimeException("Request type with this name already exists");
        }
        final RequestTypeEntity requestTypeEntity = requestTypeMapper.toEntity(requestTypeRequestDto);
        final RequestTypeEntity save = requestTypeRepository.save(requestTypeEntity);
        final RequestTypeResponseDto requestTypeResponse = requestTypeMapper.toResponse(save);
        return requestTypeResponse;
    }

    @Override
    public List<RequestTypeResponseDto> findAllRequestTypes() {
        final List<RequestTypeEntity> all = requestTypeRepository.findAll();
        final List<RequestTypeResponseDto> collect = all.stream()
                .map(request -> requestTypeMapper.toResponse(request))
                .collect(Collectors.toList());
        return collect;
    }

    @Override
    public RequestTypeResponseDto findRequestType(Long id) {
        final RequestTypeEntity requestTypeEntity = requestTypeRepository.findById(id)
                .orElseThrow(() -> new RequestTypeNotFoundException(String.format("Request type with id %s not found", id),
                        HttpStatus.NOT_FOUND));
        final RequestTypeResponseDto requestTypeResponse = requestTypeMapper.toResponse(requestTypeEntity);
        return requestTypeResponse;
    }
}
