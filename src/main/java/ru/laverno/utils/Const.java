package ru.laverno.utils;

public class Const {

    public static final String EMAIL_PATTERN = "([^\\x00-\\x20\\x22\\x28\\x29\\x2c\\x2e\\x3a-\\x3c\\x3e\\x40\\x5b-\\x5d\\x7f-\\xff]+|\\x22([^\\x0d\\x22\\x5c\\x80-\\xff]|\\x5c[\\x00-\\x7f])*\\x22)(\\x2e([^\\x00-\\x20\\x22\\x28\\x29\\x2c\\x2e\\x3a-\\x3c\\x3e\\x40\\x5b-\\x5d\\x7f-\\xff]+|\\x22([^\\x0d\\x22\\x5c\\x80-\\xff]|\\x5c[\\x00-\\x7f])*\\x22))*\\x40([^\\x00-\\x20\\x22\\x28\\x29\\x2c\\x2e\\x3a-\\x3c\\x3e\\x40\\x5b-\\x5d\\x7f-\\xff]+|\\x5b([^\\x0d\\x5b-\\x5d\\x80-\\xff]|\\x5c[\\x00-\\x7f])*\\x5d)(\\x2e([^\\x00-\\x20\\x22\\x28\\x29\\x2c\\x2e\\x3a-\\x3c\\x3e\\x40\\x5b-\\x5d\\x7f-\\xff]+|\\x5b([^\\x0d\\x5b-\\x5d\\x80-\\xff]|\\x5c[\\x00-\\x7f])*\\x5d))*";

    public static final String PHONE_PATTERN = "(\\+7|7|8)?[\\s\\-]?\\(?9\\d{2}\\)?[\\s\\-]?\\d{3}[\\s\\-]?\\d{2}[\\s\\-]?\\d{2}";

    public static final String USER_NOT_FOUND_EXCEPTION = "Не удалось найти пользователя с идентификатором [id=%s]!";

    public static final String DISCIPLINE_NOT_FOUND_EXCEPTION = "Не удалось найти дисциплину с идентификатором [id=%s]!";

    public static final String NOT_FOUND_ID_EXCEPTION = "Не удалось найти %s с идентификатором [id=%s]!";

    public static final String NOT_FOUND_NAME_EXCEPTION = "Не удалось найти %s с именем [name=%s]!";

    public static final String NOT_FOUND_ANY_EXCEPTION = "Не удалось найти %s!";

    public static final String DISCIPLINE = "дисциплину";
}
