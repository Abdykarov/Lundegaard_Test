package cz.abdykili.lundegaard.controller;

import cz.abdykili.lundegaard.model.request.ContactFormRequestDto;
import cz.abdykili.lundegaard.model.request.RequestTypeRequestDto;
import cz.abdykili.lundegaard.model.response.ContactFormResponse;
import cz.abdykili.lundegaard.model.response.ContactFormResponseDto;
import cz.abdykili.lundegaard.model.response.RequestTypeResponseDto;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Integration tests for request type controller
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RequestTypeControllerIT implements WithAssertions {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void saveRequestType() {
        //arrange
        final RequestTypeRequestDto requestTypeRequestDto = new RequestTypeRequestDto()
                .setRequestTypeName("Complain");

        final ResponseEntity<RequestTypeResponseDto> response = testRestTemplate.postForEntity("/api/v1/request-types/",requestTypeRequestDto, RequestTypeResponseDto.class);

        //act
        final RequestTypeResponseDto body = response.getBody();

        //assert
        SoftAssertions.assertSoftly(softAssertions -> {
            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
            assertThat(body).isNotNull();
            assertThat(body.getId()).isEqualTo(4);
            assertThat(body.getRequestTypeName()).isEqualTo("Complain");
        });
    }

    @Test
    void findAllRequestTypes() {

        //arrange
        final String pathUrl = "/api/v1/request-types/";
        final ResponseEntity<RequestTypeResponseDto[]> response = testRestTemplate.getForEntity(pathUrl,RequestTypeResponseDto[].class);

        //act
        RequestTypeResponseDto[] body = response.getBody();

        //assert
        SoftAssertions.assertSoftly(softAssertions -> {
            softAssertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
            softAssertions.assertThat(body).isNotNull();
            softAssertions.assertThat(body).hasSize(4);
        });
    }

    @Test
    void findRequestType() {

        //arrange
        final ResponseEntity<RequestTypeResponseDto> response = testRestTemplate.getForEntity("/api/v1/request-types/3", RequestTypeResponseDto.class);

        //act
        final RequestTypeResponseDto body = response.getBody();

        //assert
        SoftAssertions.assertSoftly(softAssertions -> {
            softAssertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
            softAssertions.assertThat(body).isNotNull();
            softAssertions.assertThat(body.getId()).isEqualTo(3L);
            softAssertions.assertThat(body.getRequestTypeName()).isEqualTo("Complaint");
        });
    }
}
