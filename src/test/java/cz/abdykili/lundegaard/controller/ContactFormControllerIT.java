package cz.abdykili.lundegaard.controller;

import cz.abdykili.lundegaard.model.request.ContactFormRequestDto;
import cz.abdykili.lundegaard.model.response.ContactFormResponse;
import cz.abdykili.lundegaard.model.response.ContactFormResponseDto;
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
 * Integration tests for contact form controller
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ContactFormControllerIT implements WithAssertions {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void saveContactForm() {
        //arrange
        final ContactFormRequestDto contactFormRequestDto = new ContactFormRequestDto()
                .setRequestTypeId(1L)
                .setName("Adam")
                .setPolicyNumber("gfdgdf4343")
                .setRequestMessage("message")
                .setSurname("Sika");

        final ResponseEntity<ContactFormResponse> response = testRestTemplate.postForEntity("/api/v1/contact-forms/",contactFormRequestDto, ContactFormResponse.class);

        //act
        final ContactFormResponse body = response.getBody();

        //assert
        SoftAssertions.assertSoftly(softAssertions -> {
            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
            assertThat(body).isNotNull();
            assertThat(body.getId()).isEqualTo(2);
        });
    }

    @Test
    void findAllContactForms() {

        //arrange
        final String pathUrl = "/api/v1/contact-forms";
        final ResponseEntity<ContactFormResponseDto[]> response = testRestTemplate.getForEntity(pathUrl,ContactFormResponseDto[].class);

        //act
        ContactFormResponseDto[] body = response.getBody();

        //assert
        SoftAssertions.assertSoftly(softAssertions -> {
            softAssertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
            softAssertions.assertThat(body).isNotNull();
            softAssertions.assertThat(body).hasSize(1);
        });
    }

    @Test
    void findContactForm() {

        //arrange
        final ResponseEntity<ContactFormResponseDto> response = testRestTemplate.getForEntity("/api/v1/contact-forms/1", ContactFormResponseDto.class);

        //act
        final ContactFormResponseDto body = response.getBody();

        //assert
        SoftAssertions.assertSoftly(softAssertions -> {
            softAssertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
            softAssertions.assertThat(body).isNotNull();
            softAssertions.assertThat(body.getId()).isEqualTo(1L);
            softAssertions.assertThat(body.getName()).isEqualTo("Ilias");
            softAssertions.assertThat(body.getSurname()).isEqualTo("Abdykarov");
            softAssertions.assertThat(body.getRequestType().getId()).isEqualTo(3L);
        });
    }
}
