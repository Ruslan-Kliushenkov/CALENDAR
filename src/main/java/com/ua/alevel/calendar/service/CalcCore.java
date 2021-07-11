package com.ua.alevel.calendar.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CalcCore {

    public static String calcDifference(String firstDate, String secondDate, int format) {

        int[] first = new int[6];
        int[] second = new int[6];

        switch (format) {
            case (1) -> {
                first = Correct.convertInIntArrayOneAndTwo(firstDate);
                second = Correct.convertInIntArrayOneAndTwo(secondDate);
            }
            case (2) -> {
                first = Correct.convertInIntArrayOneAndTwo(firstDate);
                second = Correct.convertInIntArrayOneAndTwo(secondDate);
            }
            case (3) -> {
                first = Correct.convertInIntArrayThree(firstDate);
                second = Correct.convertInIntArrayThree(secondDate);
            }
            case (4) -> {
                first = Correct.convertInIntArrayFour(firstDate);
                second = Correct.convertInIntArrayFour(secondDate);
            }
        }

        long f = first[0] * 86400L + first[1] * 2592000L + first[2] * 31536000L + first[3] * 3600L + first[4] * 60L + first[5];
        long s = second[0] * 86400L + second[1] * 2592000L + second[2] * 31536000L + second[3] * 3600L + second[4] * 60L + second[5];
        long difference = Math.abs(f - s);
        long leapYear = difference / (4 * 31536000L);
        difference += leapYear * 2592000L;
        /*return switch (format) {
            case (1) -> Correct.outFormatOne(giveMeAnswerArray(difference));
            case (2) -> Correct.outFormatTwo(giveMeAnswerArray(difference));
            case (3) -> Correct.outFormatThree(giveMeAnswerArray(difference));
            case (4) -> Correct.outFormatFour(giveMeAnswerArray(difference));
            default -> Correct.outFormatTwo(giveMeAnswerArray(difference));*/
        return Correct.outForDifference(giveMeAnswerArray(difference));
    }

    public static String calcAdd(String data, String add, int format) {
        int[] date = new int[6];
        int[] addArray = new int[6];
        switch (format) {
            case (1) -> {
                date = Correct.convertInIntArrayOneAndTwo(data);
                addArray = Correct.giveMeTimeInArray(add);
            }
            case (2) -> {
                date = Correct.convertInIntArrayOneAndTwo(data);
                addArray = Correct.giveMeTimeInArray(add);
            }
            case (3) -> {
                date = Correct.convertInIntArrayThree(data);
                addArray = Correct.giveMeTimeInArray(add);
            }
            case (4) -> {
                date = Correct.convertInIntArrayFour(data);
                addArray = Correct.giveMeTimeInArray(add);
            }
        }
        long dateLong = Math.abs(date[0] * 86400L + date[1] * 2592000L + date[2] * 31536000L + date[3] * 3600L + date[4] * 60L + date[5]);
        long addLong = Math.abs(addArray[0] * 86400L + addArray[1] * 2592000L + addArray[2] * 31536000L + addArray[3] * 3600L + addArray[4] * 60L + addArray[5]);
        long answer = dateLong + addLong;
        return switch (format) {
            case (1) -> Correct.outFormatOne(giveMeAnswerArray(answer));
            case (2) -> Correct.outFormatTwo(giveMeAnswerArray(answer));
            case (3) -> Correct.outFormatThree(giveMeAnswerArray(answer));
            case (4) -> Correct.outFormatFour(giveMeAnswerArray(answer));
            default -> throw new IllegalStateException("Unexpected value: " + format);
        };


    }

    public static String calcSubtract(String data, String add, int format) {
        int[] date = new int[6];
        int[] addArray = new int[6];
        switch (format) {
            case (1) -> {
                date = Correct.convertInIntArrayOneAndTwo(data);
                addArray = Correct.giveMeTimeInArray(add);
            }
            case (2) -> {
                date = Correct.convertInIntArrayOneAndTwo(data);
                addArray = Correct.giveMeTimeInArray(add);
            }
            case (3) -> {
                date = Correct.convertInIntArrayThree(data);
                addArray = Correct.giveMeTimeInArray(add);
            }
            case (4) -> {
                date = Correct.convertInIntArrayFour(data);
                addArray = Correct.giveMeTimeInArray(add);
            }
        }
        long dateLong = date[0] * 86400L + date[1] * 2592000L + date[2] * 31536000L + date[3] * 3600L + date[4] * 60L + date[5];
        long addLong = addArray[0] * 86400L + addArray[1] * 2592000L + addArray[2] * 31536000L + addArray[3] * 3600L + addArray[4] * 60L + addArray[5];
        long answer = dateLong - addLong;
        return switch (format) {
            case (1) -> Correct.outFormatOne(giveMeAnswerArray(answer));
            case (2) -> Correct.outFormatTwo(giveMeAnswerArray(answer));
            case (3) -> Correct.outFormatThree(giveMeAnswerArray(answer));
            case (4) -> Correct.outFormatFour(giveMeAnswerArray(answer));
            default -> throw new IllegalStateException("Unexpected value: " + format);
        };
    }

    public static List<String> calcQueue(List<String> dates, int format) {
        List<Long> answer = new ArrayList<>();
        List<Long> sortedAnswer;
        List<String> sortedAnswerToString = new ArrayList<>();


        dates.forEach(s -> {
            int[] value = new int[6];
            switch (format) {
                case (1) -> value = Correct.convertInIntArrayOneAndTwo(s);
                case (2) -> value = Correct.convertInIntArrayOneAndTwo(s);
                case (3) -> value = Correct.convertInIntArrayThree(s);
                case (4) -> value = Correct.convertInIntArrayFour(s);
            }
            long dateLong = value[0] * 86400L + value[1] * 2592000L + value[2] * 31536000L + value[3] * 3600L + value[4] * 60L + value[5];
            answer.add(dateLong);
        });
        sortedAnswer = answer.stream().sorted().collect(Collectors.toList());
        for (int i = 0; i < sortedAnswer.size(); i++) {
            switch (format) {
                case (1) -> sortedAnswerToString.add(Correct.outFormatOne(giveMeAnswerArray(sortedAnswer.get(i))));
                case (2) -> sortedAnswerToString.add(Correct.outFormatTwo(giveMeAnswerArray(sortedAnswer.get(i))));
                case (3) -> sortedAnswerToString.add(Correct.outFormatThree(giveMeAnswerArray(sortedAnswer.get(i))));
                case (4) -> sortedAnswerToString.add(Correct.outFormatFour(giveMeAnswerArray(sortedAnswer.get(i))));
                default -> throw new IllegalStateException("Unexpected value: " + format);
            }
        }

        return sortedAnswerToString;
    }

    public static List<String> calcReversQueue(List<String> dates, int format) {
        List<Long> answer = new ArrayList<>();
        List<Long> sortedAnswer;
        List<String> sortedAnswerToString = new ArrayList<>();


        dates.forEach(s -> {
            int[] value = new int[6];
            switch (format) {
                case (1) -> value = Correct.convertInIntArrayOneAndTwo(s);
                case (2) -> value = Correct.convertInIntArrayOneAndTwo(s);
                case (3) -> value = Correct.convertInIntArrayThree(s);
                case (4) -> value = Correct.convertInIntArrayFour(s);
            }
            long dateLong = value[0] * 86400L + value[1] * 2592000L + value[2] * 31536000L + value[3] * 3600L + value[4] * 60L + value[5];
            answer.add(dateLong);
        });
        sortedAnswer = answer.stream().sorted().collect(Collectors.toList());
        for (int i = sortedAnswer.size() - 1; i > 0; i--) {
            switch (format) {
                case (1) -> sortedAnswerToString.add(Correct.outFormatOne(giveMeAnswerArray(sortedAnswer.get(i))));
                case (2) -> sortedAnswerToString.add(Correct.outFormatTwo(giveMeAnswerArray(sortedAnswer.get(i))));
                case (3) -> sortedAnswerToString.add(Correct.outFormatThree(giveMeAnswerArray(sortedAnswer.get(i))));
                case (4) -> sortedAnswerToString.add(Correct.outFormatFour(giveMeAnswerArray(sortedAnswer.get(i))));
                default -> throw new IllegalStateException("Unexpected value: " + format);
            }
        }

        return sortedAnswerToString;
    }

    public static int[] giveMeAnswerArray(long answer) {
        long[] data = new long[6];
        data[2] =  (answer / 31536000L);
        answer = answer % 31536000L;
        data[1] = (answer / 2592000L);
        answer = answer % 2592000L;
        data[0] = (answer / 86400L);
        answer = answer % 86400L;
        data[3] = (answer / 3600);
        answer = answer % 3600;
        data[4] = (answer / 60);
        answer = answer % 60;
        data[5] =  answer;
        int[] back = new int[6];
        for (int i = 0; i < back.length ; i++) {
            back[i] = (int)data[i];
        }
        return back;
    }

}