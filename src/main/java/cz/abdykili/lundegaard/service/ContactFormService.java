package cz.abdykili.lundegaard.service;

import cz.abdykili.lundegaard.model.request.ContactFormRequestDto;
import cz.abdykili.lundegaard.model.response.ContactFormResponse;
import cz.abdykili.lundegaard.model.response.ContactFormResponseDto;

import java.util.List;

public interface ContactFormService {

    ContactFormResponse saveContactForm(ContactFormRequestDto contactFormRequestDto);

    ContactFormResponseDto findContactForm(Long id);

    List<ContactFormResponseDto> findAllContactForms();
}
