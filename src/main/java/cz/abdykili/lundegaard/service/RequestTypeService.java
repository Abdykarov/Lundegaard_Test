package cz.abdykili.lundegaard.service;

import cz.abdykili.lundegaard.model.request.RequestTypeRequestDto;
import cz.abdykili.lundegaard.model.response.RequestTypeResponseDto;

import java.util.List;

/**
 * Request Type service implementation class is used to
 * add new types and fetch already existing entities.
 */
public interface RequestTypeService {

    /**
     * Saving new request type. If request type with such name already exists in system,
     * then throw runtime exception.
     * @param requestTypeRequestDto - incoming dto contains new request type's name.
     * @return - response dto, which contains id and name.
     */
    RequestTypeResponseDto saveRequestType(RequestTypeRequestDto requestTypeRequestDto);

    /**
     * Fetch all request type entities from db.
     * Transform entity to response dto.
     *
     * @return the list of all request types
     */
    List<RequestTypeResponseDto> findAllRequestTypes();

    /**
     * Fetching already existing request type by his id.
     * In case if request type with such id doesnt exist in database,
     * then throw request type not found exception, message contains incorrect id.
     * @param id - id of existing request type
     * @return - response dto, which contains id and name.
     */
    RequestTypeResponseDto findRequestType(Long id);
}
