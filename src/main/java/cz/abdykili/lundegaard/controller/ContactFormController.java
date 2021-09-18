package cz.abdykili.lundegaard.controller;

import cz.abdykili.lundegaard.service.imp.ContactFormServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/contact-forms/")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ContactFormController {

    private final ContactFormServiceImp contactFormService;

    @PostMapping
    public ContactFormResponse saveContactForm(@Valid @RequestBody ContactFormRequestDto contactFormRequestDto){
        return contactFormService.saveContactForm(contactFormRequestDto);
    }

    @GetMapping("{id}")
    public ContactFormResponseDto findContactForm(@PathVariable Long id){
        return contactFormService.findContactForm(id);
    }


}
