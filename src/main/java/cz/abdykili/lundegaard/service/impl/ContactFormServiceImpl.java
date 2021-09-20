package cz.abdykili.lundegaard.service.impl;

import cz.abdykili.lundegaard.domain.ContactFormEntity;
import cz.abdykili.lundegaard.domain.RequestTypeEntity;
import cz.abdykili.lundegaard.exception.ContactFormNotFoundException;
import cz.abdykili.lundegaard.exception.RequestTypeNotFoundException;
import cz.abdykili.lundegaard.mapper.ContactFormMapper;
import cz.abdykili.lundegaard.model.request.ContactFormRequestDto;
import cz.abdykili.lundegaard.model.response.ContactFormResponse;
import cz.abdykili.lundegaard.model.response.ContactFormResponseDto;
import cz.abdykili.lundegaard.repository.ContactFormRepository;
import cz.abdykili.lundegaard.repository.RequestTypeRepository;
import cz.abdykili.lundegaard.service.ContactFormService;
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
public class ContactFormServiceImpl implements ContactFormService {

    private final ContactFormRepository contactFormRepository;
    private final RequestTypeRepository requestTypeRepository;
    private final ContactFormMapper contactFormMapper;

    @Override
    @Transactional
    public ContactFormResponse saveContactForm(ContactFormRequestDto contactFormRequestDto) {
        log.info("Saving a new contact form");
        log.debug("Saving a new contact form | Incoming request dto - {}", contactFormRequestDto);
        final ContactFormEntity contactFormEntity = contactFormMapper.toEntity(contactFormRequestDto);
        final Long requestTypeId = contactFormRequestDto.getRequestTypeId();
        final RequestTypeEntity requestTypeEntity = requestTypeRepository.findById(requestTypeId)
                .orElseThrow(() -> new RequestTypeNotFoundException(String.format("Request type with id %s not found", requestTypeId),
                        HttpStatus.NOT_FOUND));

        log.info("Saving a new contact form | Request type name - {}", requestTypeEntity.getRequestTypeName());
        contactFormEntity.setRequestType(requestTypeEntity);
        contactFormRepository.save(contactFormEntity);
        log.info("Saving a new contact form | Entity successfully uploaded to database");
        final ContactFormResponse response = contactFormMapper.toResponse(contactFormEntity);
        log.debug("Saving a new contact form | Incoming payload {}, outgoing payload {} ", contactFormRequestDto, response);

        return response;
    }

    @Override
    @Transactional(readOnly = true)
    public ContactFormResponseDto findContactForm(Long id) {
        log.info("Fetching a contact form | Incoming id - {}", id);
        final ContactFormEntity contactFormEntity = contactFormRepository.findById(id)
                .orElseThrow(() -> new ContactFormNotFoundException(String.format("Contact form with id %s not found", id),
                        HttpStatus.NOT_FOUND));
        log.info("Fetching a contact form | Contact form entity name - {}", contactFormEntity.getName());
        final ContactFormResponseDto response = contactFormMapper.toDtoResponse(contactFormEntity);
        log.debug("Fetching a contact form | Incoming id {}, outgoing payload {} ", id, response);

        return response;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ContactFormResponseDto> findAllContactForms() {
        log.info("Fetching all contact forms");
        final List<ContactFormEntity> contactFormEntityList = contactFormRepository.findAll();
        final List<ContactFormResponseDto> responseList = contactFormEntityList.stream()
                .map(contactFormMapper::toDtoResponse)
                .collect(Collectors.toList());
        log.debug("Fetching all contact forms | Outgoing list {} ", responseList);

        return responseList;
    }
}
