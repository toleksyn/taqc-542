package com.softserve.edu.task_01;

/*
 * Given an integer m > 1. Get the largest integer k, which satisfies the condition 4^k < m.
 * */

public class FirstTask {

    public int getMaximumPowerOfANumber(int m) {
        int number = 1;
        int k = 0;
        if (m > 1) {
            while (number * 4 < m) {
                number *= 4;
                k++;
            }
        } else {
            throw new IllegalArgumentException("Number must be > 0");
        }
        return k;
    }
}
