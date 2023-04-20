package ru.laverno.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class UtilsTest {

    @Test
    void shouldConvertPhoneNumberIntoFormat() {
        final var phone = "8903-550-69-58";
        System.out.printf("Phone - %s", phone);
        final var actual = Utils.convertPhoneIntoFormat(phone);
        System.out.printf("Phone after - %s", actual);
        Assertions.assertEquals("79035506958", actual);
    }
}
