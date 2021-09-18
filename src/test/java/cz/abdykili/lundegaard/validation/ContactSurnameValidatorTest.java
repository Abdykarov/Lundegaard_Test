package cz.abdykili.lundegaard.validation;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ContactSurnameValidatorTest implements WithAssertions {

    private final ContactSurnameValidator contactSurnameValidator = new ContactSurnameValidator();

    @Test
    void contactSurnameValidator_TestIsValid() {
        //arrange
        final String s = "Komarek";
        //act
        final boolean result = contactSurnameValidator.isValid(s, null);
        //assert
        assertThat(result).isTrue();
    }

    @Test
    void contactSurnameValidator_TestIsNotValid() {
        //arrange
        final String s = "Komarek123";
        //act
        final boolean result = contactSurnameValidator.isValid(s, null);
        //assert
        assertThat(result).isFalse();
    }

}