package org.assessment.student.enums;

import org.assessment.student.exception.ApplicationException;

import java.util.HashMap;
import java.util.Map;

public enum Gender {
    M,F,O;

    static Map<String, Gender> genderValueMap;
    static {
        genderValueMap = new HashMap<>();
        for (Gender gender : Gender.values()) {
            genderValueMap.put(gender.name(), gender);
        }
    }

    public static boolean validate(String value) {
        if (!genderValueMap.containsKey(value)){
            throw new ApplicationException(ErrorCode.INVALID_VALUE.getCode(), "Invalid gender value");
        }
        return true;
    }
    public static Gender getByName(String name){
        return genderValueMap.getOrDefault(name, Gender.O);
    }
}
