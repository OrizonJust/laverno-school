package ru.laverno.utils;

import ru.laverno.exception.ParamValidationException;

public class ParamValidator {

    private ParamValidator() {}

    public static void validateEmail(String email) {
        if (email == null || !email.matches(Const.EMAIL_PATTERN)) {
            throw new ParamValidationException(String.format("Адрес электронной почты [email=%s] не прошёл валидацию!", email));
        }
    }

    public static void validatePhone(String phone) {
        if (phone == null || !phone.matches(Const.PHONE_PATTERN)) {
            throw new ParamValidationException(String.format("Номер телефона [phone=%s] не прошёл валидацию!", phone));
        }
    }
}
