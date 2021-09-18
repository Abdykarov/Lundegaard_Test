package cz.abdykili.lundegaard.validation;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ContactNameValidatorTest implements WithAssertions {
    private final ContactNameValidator contactNameValidator = new ContactNameValidator();

    @Test
    void contactNameValidator_TestIsValid() {
        //arrange
        final String s = "Adam";
        //act
        final boolean result = contactNameValidator.isValid(s, null);
        //assert
        assertThat(result).isTrue();
    }

    @Test
    void contactNameValidator_TestIsNotValid() {
        //arrange
        final String s = "Adam123";
        //act
        final boolean result = contactNameValidator.isValid(s, null);
        //assert
        assertThat(result).isFalse();
    }
}