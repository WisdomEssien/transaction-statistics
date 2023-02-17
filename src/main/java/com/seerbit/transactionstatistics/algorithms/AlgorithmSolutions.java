package com.seerbit.transactionstatistics.algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class AlgorithmSolutions {

    /*
        QUESTION
        Given an array of integers and a value, determine if there are any two integers in the
        array whose sum is equal to the given value.

     */

    public static boolean posibleTwoNumbers(int[] inputArr, int value) {
        List<Integer> input = Arrays.stream(inputArr).boxed().collect(Collectors.toList());
        List<Integer> inputClone = new ArrayList<>(input);

        for(Integer element : input){
            inputClone.remove(element);
            if(Collections.frequency(inputClone, value - element) > 0){
                return true;
            }
        }
        return false;
    }


    /*
        QUESTION
        Given a sorted array of integers, return the low and high index of the given key. Return
        -1 if not found. The array length can be in the millions with many duplicates.
     */
    public static String lowAndHighIndexes(int[] inputArr, int value) {
        List<Integer> input = Arrays.stream(inputArr).boxed().collect(Collectors.toList());
        int lowIndex = input.indexOf(value);
        int highIndex = input.lastIndexOf(value);
        return lowIndex == -1 && highIndex == -1 ? "-1" : "low index = " + lowIndex + "\nhigh index = " + highIndex;
    }



    /*
        QUESTION
        You are given an array (list) of interval pairs as input where each interval has a start
        and end timestamp. The input array is sorted by starting timestamps. You are required
        to merge overlapping intervals and return output array (list).
     */


    public static void main(String[] args){
        System.out.println(posibleTwoNumbers(new int[]{2, -3, 5, 9, 6, -1}, -4));
        System.out.println(lowAndHighIndexes(new int[]{1, 2, 3, 9, 9, 5, 99, 88, 55}, 7));
    }

}
