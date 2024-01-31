package org.assessment.student.validate;

import org.assessment.student.enums.ErrorCode;
import org.assessment.student.exception.ApplicationException;

import java.util.Objects;

public interface RequestValidator {

    default void validateNotBlank(String value, String message) {
        if (Objects.isNull(value) || value.trim().isEmpty()) {
            throw new ApplicationException(ErrorCode.VALUE_REQ, message);
        }
    }

    default void validateNotNull(Object value, String message) {
        if (Objects.isNull(value)) {
            throw new ApplicationException(ErrorCode.VALUE_REQ, message);
        }
    }
}
