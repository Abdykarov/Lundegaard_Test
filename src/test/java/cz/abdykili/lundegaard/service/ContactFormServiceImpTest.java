package cz.abdykili.lundegaard.service;

import cz.abdykili.lundegaard.domain.ContactFormEntity;
import cz.abdykili.lundegaard.domain.RequestTypeEntity;
import cz.abdykili.lundegaard.exception.ContactFormNotFoundException;
import cz.abdykili.lundegaard.mapper.ContactFormMapper;
import cz.abdykili.lundegaard.model.request.ContactFormRequestDto;
import cz.abdykili.lundegaard.model.response.ContactFormResponse;
import cz.abdykili.lundegaard.model.response.ContactFormResponseDto;
import cz.abdykili.lundegaard.repository.ContactFormRepository;
import cz.abdykili.lundegaard.repository.RequestTypeRepository;
import cz.abdykili.lundegaard.service.imp.ContactFormServiceImp;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ContactFormServiceImpTest implements WithAssertions {

    @Mock
    private ContactFormRepository contactFormRepository;

    @Mock
    private RequestTypeRepository requestTypeRepository;

    @Mock
    private ContactFormMapper contactFormMapper;

    @InjectMocks
    private ContactFormServiceImp contactFormService;

    @Test
    void saveContactForm() {

        // arrange
        final ContactFormRequestDto contactFormRequestDto = new ContactFormRequestDto()
                .setName("Adam")
                .setRequestTypeId(1L);

        final RequestTypeEntity requestTypeEntity = (RequestTypeEntity) new RequestTypeEntity()
                .setId(1L);

        final ContactFormEntity contactFormEntity = new ContactFormEntity()
                .setName(contactFormRequestDto.getName())
                .setRequestType(requestTypeEntity);

        final ContactFormResponse contactFormResponse = new ContactFormResponse()
                .setId(1L);

        when(contactFormMapper.toEntity(contactFormRequestDto)).thenReturn(contactFormEntity);
        when(requestTypeRepository.findById(1L)).thenReturn(Optional.of(requestTypeEntity));
        when(contactFormRepository.save(contactFormEntity)).thenReturn(contactFormEntity);
        when(contactFormMapper.toResponse(contactFormEntity)).thenReturn(contactFormResponse);

        // act
        final ContactFormResponse formResponse = contactFormService.saveContactForm(contactFormRequestDto);

        // assert
        verify(contactFormMapper).toEntity(eq(contactFormRequestDto));
        verify(contactFormRepository).save(eq(contactFormEntity));
        verify(contactFormMapper).toResponse(eq(contactFormEntity));

        assertThat(formResponse).isEqualTo(contactFormResponse);
    }

    @Test
    void findContactForm() {

        // arrange
        final ContactFormEntity contactFormEntity = (ContactFormEntity) new ContactFormEntity()
                .setName("Adam")
                .setPolicyNumber("123123abcd")
                .setId(1L);

        final ContactFormResponseDto contactFormResponseDto = new ContactFormResponseDto()
                .setName(contactFormEntity.getName())
                .setPolicyNumber(contactFormEntity.getPolicyNumber())
                .setId(1L);
        when(contactFormRepository.findById(1L)).thenReturn(Optional.of(contactFormEntity));
        when(contactFormMapper.toDtoResponse(contactFormEntity)).thenReturn(contactFormResponseDto);

        // act
        final ContactFormResponseDto formResponseDto = contactFormService.findContactForm(1L);

        // assert
        verify(contactFormRepository).findById(eq(1L));
        verify(contactFormMapper).toDtoResponse(eq(contactFormEntity));

        SoftAssertions.assertSoftly(softAssertions -> {
            softAssertions.assertThat(formResponseDto.getId()).isEqualTo(1L);
            softAssertions.assertThat(formResponseDto.getName()).isEqualTo(contactFormResponseDto.getName());
            softAssertions.assertThat(formResponseDto.getPolicyNumber()).isEqualTo(contactFormResponseDto.getPolicyNumber());
        });
    }

    @Test
    void findContactForm__ContactFormNotFoundException() {

        // arrange
        when(contactFormRepository.findById(1L)).thenReturn(Optional.empty());

        // act
        final ContactFormNotFoundException contactFormNotFoundException = assertThrows(ContactFormNotFoundException.class,
                () -> contactFormService.findContactForm(1L));

        // assert
        verify(contactFormRepository).findById(eq(1L));

        assertThat(contactFormNotFoundException.getHttpStatus()).isEqualTo(HttpStatus.NOT_FOUND);

    }

    @Test
    void findAllContactForms() {

        //arrange
        final Integer supposedSize = 5;

        when(contactFormRepository.findAll()).thenReturn(Arrays.asList(
                new ContactFormEntity(),
                new ContactFormEntity(),
                new ContactFormEntity(),
                new ContactFormEntity(),
                new ContactFormEntity()
        ));

        //act
        final List<ContactFormResponseDto> returnedList = contactFormService.findAllContactForms();

        //assert
        verify(contactFormRepository).findAll();
        assertThat(supposedSize).isEqualTo(returnedList.size());
    }
}