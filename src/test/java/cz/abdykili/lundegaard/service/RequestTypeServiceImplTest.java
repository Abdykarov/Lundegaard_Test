package cz.abdykili.lundegaard.service;

import cz.abdykili.lundegaard.domain.RequestTypeEntity;
import cz.abdykili.lundegaard.mapper.RequestTypeMapper;
import cz.abdykili.lundegaard.model.request.RequestTypeRequestDto;
import cz.abdykili.lundegaard.model.response.RequestTypeResponseDto;
import cz.abdykili.lundegaard.repository.RequestTypeRepository;
import cz.abdykili.lundegaard.service.impl.RequestTypeServiceImpl;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RequestTypeServiceImplTest {

    @Mock
    private RequestTypeRepository requestTypeRepository;

    @Mock
    private RequestTypeMapper requestTypeMapper;

    @InjectMocks
    private RequestTypeServiceImpl requestTypeService;


    @Test
    void saveRequestType() {
        // arrange
        final RequestTypeRequestDto requestTypeRequestDto = new RequestTypeRequestDto()
                .setRequestTypeName("Complain");

        final RequestTypeEntity requestTypeEntity = (RequestTypeEntity) new RequestTypeEntity()
                .setRequestTypeName(requestTypeRequestDto.getRequestTypeName())
                .setId(1L);

        final RequestTypeResponseDto requestTypeResponseDto = new RequestTypeResponseDto()
                .setId(requestTypeEntity.getId())
                .setRequestTypeName(requestTypeEntity.getRequestTypeName());

        when(requestTypeRepository.existsByRequestTypeName(requestTypeRequestDto.getRequestTypeName())).thenReturn(false);
        when(requestTypeMapper.toEntity(requestTypeRequestDto)).thenReturn(requestTypeEntity);
        when(requestTypeRepository.save(requestTypeEntity)).thenReturn(requestTypeEntity);
        when(requestTypeMapper.toResponse(requestTypeEntity)).thenReturn(requestTypeResponseDto);

        // act
        final RequestTypeResponseDto responseDto = requestTypeService.saveRequestType(requestTypeRequestDto);

        // assert
        verify(requestTypeRepository).existsByRequestTypeName(eq(requestTypeRequestDto.getRequestTypeName()));
        verify(requestTypeMapper).toEntity(eq(requestTypeRequestDto));
        verify(requestTypeRepository).save(eq(requestTypeEntity));
        verify(requestTypeMapper).toResponse(eq(requestTypeEntity));

        SoftAssertions.assertSoftly(softAssertions -> {
            softAssertions.assertThat(responseDto.getId()).isEqualTo(1L);
            softAssertions.assertThat(responseDto.getRequestTypeName()).isEqualTo(requestTypeResponseDto.getRequestTypeName());
        });
    }

    @Test
    void saveRequestType_AlreadyExists() {
        // arrange
        final RequestTypeRequestDto requestTypeRequestDto = new RequestTypeRequestDto()
                .setRequestTypeName("Complain");

        when(requestTypeRepository.existsByRequestTypeName("Complain")).thenReturn(true);

        // act
        final RuntimeException runtimeException = assertThrows(RuntimeException.class,
                () -> requestTypeService.saveRequestType(requestTypeRequestDto));

        // assert
        verify(requestTypeRepository).existsByRequestTypeName(eq(requestTypeRequestDto.getRequestTypeName()));

        assertThat(runtimeException.getMessage()).isEqualTo("Request type with this name already exists");
    }

    @Test
    void findAllRequestTypes() {
        //arrange
        final Integer supposedSize = 5;
        final RequestTypeEntity requestTypeEntity = new RequestTypeEntity();
        final RequestTypeResponseDto requestTypeResponseDto = new RequestTypeResponseDto();
        when(requestTypeMapper.toResponse(requestTypeEntity)).thenReturn(requestTypeResponseDto);
        when(requestTypeRepository.findAll()).thenReturn(Arrays.asList(
                requestTypeEntity,
                requestTypeEntity,
                requestTypeEntity,
                requestTypeEntity,
                requestTypeEntity
        ));

        //act
        final List<RequestTypeResponseDto> returnedList = requestTypeService.findAllRequestTypes();

        //assert
        verify(requestTypeRepository).findAll();
        verify(requestTypeMapper, times(5)).toResponse(requestTypeEntity);
        assertThat(supposedSize).isEqualTo(returnedList.size());
    }

    @Test
    void findRequestType() {
        // arrange
        final RequestTypeEntity requestTypeEntity = (RequestTypeEntity) new RequestTypeEntity()
                .setRequestTypeName("Complain")
                .setId(1L);

        final RequestTypeResponseDto requestTypeResponseDto = new RequestTypeResponseDto()
                .setRequestTypeName(requestTypeEntity.getRequestTypeName())
                .setId(1L);
        when(requestTypeRepository.findById(1L)).thenReturn(Optional.of(requestTypeEntity));
        when(requestTypeMapper.toResponse(requestTypeEntity)).thenReturn(requestTypeResponseDto);

        // act
        final RequestTypeResponseDto responseDto = requestTypeService.findRequestType(1L);

        // assert
        verify(requestTypeRepository).findById(eq(1L));
        verify(requestTypeMapper).toResponse(eq(requestTypeEntity));

        SoftAssertions.assertSoftly(softAssertions -> {
            softAssertions.assertThat(responseDto.getId()).isEqualTo(1L);
            softAssertions.assertThat(responseDto.getRequestTypeName()).isEqualTo(requestTypeResponseDto.getRequestTypeName());
        });
    }
}