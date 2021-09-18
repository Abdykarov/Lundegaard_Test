package cz.abdykili.lundegaard.service;

public interface ContactFormService {

    ContactFormResponse saveContactForm(ContactFormRequestDto contactFormRequestDto);

    ContactFormResponseDto findContactForm(Long id);
}
