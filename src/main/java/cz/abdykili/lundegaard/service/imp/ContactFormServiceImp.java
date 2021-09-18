package cz.abdykili.lundegaard.service.imp;

import cz.abdykili.lundegaard.service.ContactFormService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ContactFormServiceImp implements ContactFormService {
    @Override
    public ContactFormResponse saveContactForm(ContactFormRequestDto contactFormRequestDto) {
        return null;
    }

    @Override
    public ContactFormResponseDto findContactForm(Long id) {
        return null;
    }
}
