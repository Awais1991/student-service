package org.assessment.student.util;

import java.util.Random;

public  class RollNumberGenerator {

    private RollNumberGenerator() {
    }

    public static String generateRollNo(int len){
        Random random = new Random();
        StringBuilder randomNumberBuilder = new StringBuilder();

        for (int i = 0; i < len; i++) {
            int digit = random.nextInt(10); // Generates a random digit (0-9)
            randomNumberBuilder.append(digit);
        }
        return randomNumberBuilder.toString();
    }

}
