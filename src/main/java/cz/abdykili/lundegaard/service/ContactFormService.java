package cz.abdykili.lundegaard.service;

import cz.abdykili.lundegaard.model.request.ContactFormRequestDto;
import cz.abdykili.lundegaard.model.response.ContactFormResponse;
import cz.abdykili.lundegaard.model.response.ContactFormResponseDto;

import java.util.List;

/**
 * Contact form service implementation class can be used to save contact form.
 * Also provides methods for fetching existing entities from database.
 */
public interface ContactFormService {

    /**
     * Save new contact form into database.
     * Throws request not found exception when request type id doesnt exist in db.
     *
     * @param contactFormRequestDto - Request dto class contains information about form, should be transformed to entity class
     * @return id of new created entity
     */
    ContactFormResponse saveContactForm(ContactFormRequestDto contactFormRequestDto);

    /**
     * Find contact form entity by id.
     * Throws contact form not found exception when id doesnt exist in database.
     *
     * @param id - id of contact form, placed in db
     * @return - contact form dto with full information about form
     */
    ContactFormResponseDto findContactForm(Long id);

    /**
     * Fetch all contact form entities from db.
     * Transform entity to response dto.
     *
     * @return list of contact form response dtos
     */
    List<ContactFormResponseDto> findAllContactForms();
}
