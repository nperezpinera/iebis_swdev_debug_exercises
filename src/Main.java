package com.company;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Main {

        public static void main(String [] args) {
            BigDecimal d = new BigDecimal(0.0);
            BigDecimal a = new BigDecimal(1.0);
            BigDecimal x = new BigDecimal(0.1);

            while (d.compareTo(a) != 0) {
                BigDecimal sum = d.add(x);
                d = sum.setScale(2, RoundingMode.FLOOR);

            }

            System.out.println("d is 1");
        }
}
