package cz.abdykili.lundegaard.controller;

import cz.abdykili.lundegaard.model.request.ContactFormRequestDto;
import cz.abdykili.lundegaard.model.response.ContactFormResponse;
import cz.abdykili.lundegaard.model.response.ContactFormResponseDto;
import cz.abdykili.lundegaard.service.impl.ContactFormServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/contact-forms")
@CrossOrigin
public class ContactFormController {

    private final ContactFormServiceImpl contactFormService;

    @PostMapping
    public ContactFormResponse saveContactForm(@Valid @RequestBody ContactFormRequestDto contactFormRequestDto) {
        return contactFormService.saveContactForm(contactFormRequestDto);
    }

    @GetMapping
    public List<ContactFormResponseDto> findAllContactForms() {
        return contactFormService.findAllContactForms();
    }

    @GetMapping("{id}")
    public ContactFormResponseDto findContactForm(@PathVariable Long id) {
        return contactFormService.findContactForm(id);
    }


}
