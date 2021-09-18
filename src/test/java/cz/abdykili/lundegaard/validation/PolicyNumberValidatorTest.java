package cz.abdykili.lundegaard.validation;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PolicyNumberValidatorTest implements WithAssertions {

    private final PolicyNumberValidator policyNumberValidator = new PolicyNumberValidator();

    @Test
    void policyNumberValidator_TestIsValid() {
        //arrange
        final String s = "testPolicy5443";
        //act
        final boolean result = policyNumberValidator.isValid(s, null);
        //assert
        assertThat(result).isTrue();
    }

    @Test
    void policyNumberValidator_TestIsNotValid() {
        //arrange
        final String s = "testInvalid!re343";
        //act
        final boolean result = policyNumberValidator.isValid(s, null);
        //assert
        assertThat(result).isFalse();
    }
}