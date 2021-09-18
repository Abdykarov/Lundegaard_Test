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

import java.util.List;
import java.util.stream.Collectors;

/**
 * Request Type service implementation class is used to
 * add new types and fetch already existing entities.
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class RequestTypeServiceImp implements RequestTypeService {

    private final RequestTypeRepository requestTypeRepository;
    private final RequestTypeMapper requestTypeMapper;

    /**
     * Saving new request type. If request type with such name already exists in system,
     * then throw runtime exception.
     * @param requestTypeRequestDto - incoming dto contains new request type's name.
     * @return - response dto, which contains id and name.
     */
    @Override
    public RequestTypeResponseDto saveRequestType(RequestTypeRequestDto requestTypeRequestDto) {
        log.info("Saving a new request type");
        log.debug("Saving a new request type | Incoming request dto - {}", requestTypeRequestDto);
        if (requestTypeRepository.existsByRequestTypeName(requestTypeRequestDto.getRequestTypeName())) {
            throw new RuntimeException("Request type with this name already exists");
        }
        final RequestTypeEntity requestTypeEntity = requestTypeMapper.toEntity(requestTypeRequestDto);
        final RequestTypeEntity save = requestTypeRepository.save(requestTypeEntity);
        log.info("Saving a new request type |  Dto successfully uploaded to database");
        final RequestTypeResponseDto requestTypeResponse = requestTypeMapper.toResponse(save);
        log.debug("Saving a new request type | Outgoing request type response dto {} ", requestTypeResponse);

        return requestTypeResponse;
    }

    /**
     * Fetch all request type entities from db.
     * Transform entity to response dto.
     *
     * @return the list of all request types
     */
    @Override
    public List<RequestTypeResponseDto> findAllRequestTypes() {
        log.info("Fetching all request types");
        final List<RequestTypeEntity> all = requestTypeRepository.findAll();
        final List<RequestTypeResponseDto> collect = all.stream()
                .map(request -> requestTypeMapper.toResponse(request))
                .collect(Collectors.toList());
        log.debug("Fetching all request types | Size : {} , Outgoing list {} ", collect.size(), collect);

        return collect;
    }

    /**
     * Fetching already existing request type by his id.
     * In case if request type with such id doesnt exist in database,
     * then throw request type not found exception, message contains incorrect id.
     * @param id - id of existing request type
     * @return - response dto, which contains id and name.
     */
    @Override
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
