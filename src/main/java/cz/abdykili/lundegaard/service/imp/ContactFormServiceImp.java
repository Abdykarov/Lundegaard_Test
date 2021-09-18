package cz.abdykili.lundegaard.service.imp;

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
public class ContactFormServiceImp implements ContactFormService {

    private final ContactFormRepository contactFormRepository;
    private final RequestTypeRepository requestTypeRepository;
    private final ContactFormMapper contactFormMapper;

    @Override
    public ContactFormResponse saveContactForm(ContactFormRequestDto contactFormRequestDto) {

        final ContactFormEntity contactFormEntity = contactFormMapper.toEntity(contactFormRequestDto);
        final Long requestTypeId = contactFormRequestDto.getRequestTypeId();
        final RequestTypeEntity requestTypeEntity = requestTypeRepository.findById(requestTypeId)
                .orElseThrow(() -> new RequestTypeNotFoundException(String.format("Request type with id %s not found", requestTypeId),
                        HttpStatus.NOT_FOUND));
        contactFormEntity.setRequestType(requestTypeEntity);
        final ContactFormEntity save = contactFormRepository.save(contactFormEntity);
        final ContactFormResponse response = contactFormMapper.toResponse(save);
        return response;
    }

    @Override
    @Transactional(readOnly = true)
    public ContactFormResponseDto findContactForm(Long id) {
        final ContactFormEntity contactFormEntity = contactFormRepository.findById(id)
                .orElseThrow(() -> new ContactFormNotFoundException(String.format("Contact form with id %s not found", id),
                        HttpStatus.NOT_FOUND));
        final ContactFormResponseDto response = contactFormMapper.toDtoResponse(contactFormEntity);

        return response;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ContactFormResponseDto> findAllContactForms() {

        final List<ContactFormEntity> contactFormEntityList = contactFormRepository.findAll();

        final List<ContactFormResponseDto> responseList = contactFormEntityList.stream()
                .map(contact -> contactFormMapper.toDtoResponse(contact))
                .collect(Collectors.toList());

        return responseList;
    }
}
