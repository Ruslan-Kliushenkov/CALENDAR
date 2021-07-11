package com.ua.alevel.calendar.service;

import java.util.Arrays;
import java.util.Locale;


public class Correct {


    public static boolean checkCorrect(String date, int format) {
        int[] array = switch (format) {
            case (1) -> Arrays.copyOf(convertInIntArrayOneAndTwo(date), 6);
            case (2) -> Arrays.copyOf(convertInIntArrayOneAndTwo(date), 6);
            case (3) -> Arrays.copyOf(convertInIntArrayThree(date), 6);
            case (4) -> Arrays.copyOf(convertInIntArrayFour(date), 6);
            case (5) -> Arrays.copyOf(giveMeTimeInArray(date), 6);
            default -> new int[6];
        };

        int day = array[0];
        int mont = array[1];
        int year = array[2];
        int hour = array[3];
        int minutes = array[4];
        int seconds = array[5];

        for (int j : array) {
            if (j < 0) {
                return false;
            }
        }

        if (mont > 12) {
            return false;
        }
        if (year < 0 || year > 3000) {
            return false;
        }
        if (hour >= 60) {
            return false;
        }
        if (minutes >= 60) {
            return false;
        }
        if (seconds >= 60) {
            return false;
        }
        int daysOfMont = Arrays.stream(Months.values()).filter(s -> s.ordinal() == mont - 1).mapToInt(Months::getDays).reduce((s1, s2) -> s1).orElse(0);
        if (mont == 2 & year % 4 == 0) {
            daysOfMont++;
        }

        if (format!= 5){
            if (day > daysOfMont) {
                return false;
            }
        }

        return true;
    }


    public static int[] convertInIntArrayOneAndTwo(String date) {
        date = date + " ";
        String firstDate = "1";
        if (date.equals("//")) {
            date = "1/1/0 0:0:0";
        }
        if (date.startsWith("/")) {
            date = firstDate + date;
        }
        String[] dateInArray = date.split("/");
        if (dateInArray.length != 3) {
            return new int[]{70, 70, 70, 70, 70, 70};
        }
        if (dateInArray[1].equals("")) {
            dateInArray[1] = "1";
        }
        if (dateInArray[0].equals("")) {
            dateInArray[0] = "1";
        }
        if (dateInArray[2].equals("")) {
            dateInArray[2] = "0 0:0:0";
        }
        if (dateInArray[2].startsWith(" ")) {
            dateInArray[2] = "0" + dateInArray[2];
        }

        String[] answer = new String[6];
        answer[0] = dateInArray[0];
        answer[1] = dateInArray[1];

        String[] hours = dateInArray[2].split(" ");
        if (!dateInArray[2].matches(":")) {
            answer[2] = dateInArray[2].replaceAll(" ", "");
            answer[3] = "0";
            answer[4] = "0";
            answer[5] = "0";
        }
        ;
        if (hours.length == 1) {
            answer[2] = dateInArray[2].replaceAll(" ", "");
            answer[3] = "0";
            answer[4] = "0";
            answer[5] = "0";
        } else if (hours.length > 2) {
            answer[3] = "-1";
            answer[4] = "-1";
            answer[5] = "-1";
        } else if (hours.length == 2) {
            answer[2] = hours[0];
            if (hours[1].split(":").length == 3) {
                String[] minutes = hours[1].split(":");

                answer[3] = minutes[0];
                answer[4] = minutes[1];
                answer[5] = minutes[2];

            } else if (hours[1].split(":").length == 2) {
                String[] minutes = hours[1].split(":");
                answer[3] = "0";
                answer[4] = minutes[0];
                answer[5] = minutes[1];
            } else if (hours[1].split(":").length == 1) {
                String[] minutes = hours[1].split(":");
                answer[3] = "0";
                answer[4] = "0";
                answer[5] = minutes[0];
            }
        }

        for (int i = 0; i < answer.length; i++) {
            answer[i] = answer[i].replaceAll(" ", "");
        }

        for (int i = 0; i < answer.length; i++) {
            if (!answer[i].matches("\\d*")) {
                answer[i] = "-3";
            }
        }

        int[] result = new int[6];
        for (int i = 0; i < result.length; i++) {
            result[i] = Integer.parseInt(answer[i]);
        }
        return result;
    }

    public static String outFormatTwo(int[] answer) {
        return answer[0] + "/" + answer[1] + "/" + answer[2] + " " + answer[3] + ":" + answer[4] + ":" + answer[5];
    }

    public static String outFormatOne(int[] answer) {
        StringBuilder sb = new StringBuilder();
        if (answer[0] / 10 == 0) {
            sb.append("0").append(answer[0]);
        } else {
            sb.append(answer[0]);
        }

        sb.append("/");

        if (answer[1] / 10 == 0) {
            sb.append("0").append(answer[1]);
        } else {
            sb.append(answer[1]);
        }

        sb.append("/");

        if (answer[2] / 10 == 0) {
            sb.append("000").append(answer[2]);
        } else if (answer[2] / 100 == 0) {
            sb.append("00").append(answer[2]);
        } else if (answer[2] / 1000 == 0) {
            sb.append("0").append(answer[2]);
        } else {
            sb.append(answer[2]);
        }

        sb.append(" ");

        if (answer[3] / 10 == 0) {
            sb.append("0").append(answer[3]);
        } else {
            sb.append(answer[3]);
        }

        sb.append(":");

        if (answer[4] / 10 == 0) {
            sb.append("0").append(answer[4]);
        } else {
            sb.append(answer[4]);
        }
        sb.append(":");

        if (answer[5] / 10 == 0) {
            sb.append("0").append(answer[5]);
        } else {
            sb.append(answer[5]);
        }

        return sb.toString();
    }

    public static int[] convertInIntArrayFour(String date) {
        date = date + " ";
        String firstDate = "1";
        if (date.equals("--")) {
            date = "01-Январь-00 0:0:0";
        }
        if (date.startsWith("-")) {
            date = firstDate + date;
        }
        String[] dateInArray = date.split("-");

        if (dateInArray.length != 3) {
            return new int[]{70, 70, 70, 70, 70, 70};
        }
        if (dateInArray[1].equals("")) {
            dateInArray[1] = "1";
        }
        if (dateInArray[0].equals("")) {
            dateInArray[0] = "1";
        }
        if (dateInArray[2].equals("")) {
            dateInArray[2] = "0 0:0:0";
        }
        if (dateInArray[2].startsWith(" ")) {
            dateInArray[2] = "0" + dateInArray[2];
        }

        String[] answer = new String[6];
        answer[0] = dateInArray[0];
        if (Arrays.stream(Months.values()).anyMatch(months -> months.getInRussian().toLowerCase(Locale.ROOT).equals(dateInArray[1].toLowerCase(Locale.ROOT)))) {
            int mount = Arrays.stream(Months.values())
                    .filter(months -> months.getInRussian()
                            .equals(dateInArray[1])).map(Months::getMountsNumber).reduce((s1, s2) -> s1).orElse(-1);
            answer[1] = String.valueOf(mount);
        } else {
            answer[1] = "600";
        }

        if (answer[1].startsWith("0")) {
            answer[1] = answer[1].replaceAll("0", "");
        }
        String[] hours = dateInArray[2].split(" ");
        if (!dateInArray[2].matches(":")) {
            answer[2] = dateInArray[2].replaceAll(" ", "");
            answer[3] = "0";
            answer[4] = "0";
            answer[5] = "0";
        }
        ;
        if (hours.length == 1) {
            answer[2] = dateInArray[2].replaceAll(" ", "");
            answer[3] = "0";
            answer[4] = "0";
            answer[5] = "0";
        } else if (hours.length > 2) {
            answer[3] = "-1";
            answer[4] = "-1";
            answer[5] = "-1";
        } else if (hours.length == 2) {
            answer[2] = hours[0];
            if (hours[1].split(":").length == 3) {
                String[] minutes = hours[1].split(":");

                answer[3] = minutes[0];
                answer[4] = minutes[1];
                answer[5] = minutes[2];

            } else if (hours[1].split(":").length == 2) {
                String[] minutes = hours[1].split(":");
                answer[3] = "0";
                answer[4] = minutes[0];
                answer[5] = minutes[1];
            } else if (hours[1].split(":").length == 1) {
                String[] minutes = hours[1].split(":");
                answer[3] = "0";
                answer[4] = "0";
                answer[5] = minutes[0];
            }
        }

        for (int i = 0; i < answer.length; i++) {
            answer[i] = answer[i].replaceAll(" ", "");
        }

        for (int i = 0; i < answer.length; i++) {
            if (!answer[i].matches("\\d*")) {
                answer[i] = "-3";
            }
        }

        int[] result = new int[6];
        for (int i = 0; i < result.length; i++) {
            result[i] = Integer.parseInt(answer[i]);
        }
        return result;
    }

    public static int[] giveMeTimeInArray(String time) {

        String[] t = time.replaceAll(" ","").split(",");
        if (t.length < 6) {
            return new int[]{-1, -1, -1, -1, -1, -1};
        }
        for (String s : t) {
            if (s.matches("\\D")) {
                return new int[]{-1, -1, -1, -1, -1, -1};
            }
        }
        int[] result = new int[6];
        result[2] = Integer.parseInt(t[0]);
        result[1] = Integer.parseInt(t[1]);
        result[0] = Integer.parseInt(t[2]);
        result[3] = Integer.parseInt(t[3]);
        result[4] = Integer.parseInt(t[4]);
        result[5] = Integer.parseInt(t[5]);

        return result;
    }

    public static int[] convertInIntArrayThree(String date) {
        date = date + " ";
        String firstDate = "Январь";
        if (date.equals("--")) {
            date = "Январь-01-0 0:0:0";
        }
        if (date.startsWith("-")) {
            date = firstDate + date;
        }
        String[] dateInArray = date.split("-");

        if (dateInArray.length != 3) {
            return new int[]{70, 70, 70, 70, 70, 70};
        }
        if (dateInArray[1].equals("")) {
            dateInArray[1] = "1";
        }
        if (dateInArray[0].equals("")) {
            dateInArray[0] = "Январь";
        }
        if (dateInArray[2].equals("")) {
            dateInArray[2] = "0 0:0:0";
        }
        if (dateInArray[2].startsWith(" ")) {
            dateInArray[2] = "0" + dateInArray[2];
        }

        String[] answer = new String[6];
        answer[0] = dateInArray[1];
        if (answer[0].startsWith("0")) {
            answer[0] = answer[0].replaceAll("0", "");
        }
        if (Arrays.stream(Months.values()).anyMatch(months -> months.getInRussian().toLowerCase(Locale.ROOT).equals(dateInArray[0].toLowerCase(Locale.ROOT)))) {
            int mount = Arrays.stream(Months.values())
                    .filter(months -> months.getInRussian()
                            .equals(dateInArray[0])).map(Months::getMountsNumber).reduce((s1, s2) -> s1).orElse(-1);
            answer[1] = String.valueOf(mount);
        } else {
            answer[1] = "600";
        }

        if (answer[0].startsWith("0")) {
            answer[0] = answer[0].replaceAll("0", "");
        }
        String[] hours = dateInArray[2].split(" ");
        if (!dateInArray[2].matches(":")) {
            answer[2] = dateInArray[2].replaceAll(" ", "");
            answer[3] = "0";
            answer[4] = "0";
            answer[5] = "0";
        }
        ;
        if (hours.length == 1) {
            answer[2] = dateInArray[2].replaceAll(" ", "");
            answer[3] = "0";
            answer[4] = "0";
            answer[5] = "0";
        } else if (hours.length > 2) {
            answer[3] = "-1";
            answer[4] = "-1";
            answer[5] = "-1";
        } else if (hours.length == 2) {
            answer[2] = hours[0];
            if (hours[1].split(":").length == 3) {
                String[] minutes = hours[1].split(":");

                answer[3] = minutes[0];
                answer[4] = minutes[1];
                answer[5] = minutes[2];

            } else if (hours[1].split(":").length == 2) {
                String[] minutes = hours[1].split(":");
                answer[3] = "0";
                answer[4] = minutes[0];
                answer[5] = minutes[1];
            } else if (hours[1].split(":").length == 1) {
                String[] minutes = hours[1].split(":");
                answer[3] = "0";
                answer[4] = "0";
                answer[5] = minutes[0];
            }
        }

        for (int i = 0; i < answer.length; i++) {
            answer[i] = answer[i].replaceAll(" ", "");
        }

        for (int i = 0; i < answer.length; i++) {
            if (!answer[i].matches("\\d*")) {
                answer[i] = "-3";
            }
        }

        int[] result = new int[6];
        for (int i = 0; i < result.length; i++) {
            result[i] = Integer.parseInt(answer[i]);
        }
        return result;

    }

    public static String outFormatThree(int[] answer) {
        String mount = Arrays.stream(Months.values()).filter(months -> months.getMountsNumber() == answer[1]).findFirst().map(Months::getInRussian).get();
        return mount + "-" + answer[0] + "-" + answer[2] + " " + answer[3]/*hh*/ + ":" + answer[4]/*mm*/ + ":" + answer[5]/*ss*/;
    }

    public static String outFormatFour(int[] answer) {
        String mount = Arrays.stream(Months.values()).filter(months -> months.getMountsNumber() == answer[1]).findFirst().map(Months::getInRussian).get();
        if (answer[0] / 10 == 0){
            return "0" +answer[0] + "-" + mount + "-" + +answer[2] + " " + answer[3]/*hh*/ + ":" + answer[4]/*mm*/ + ":" + answer[5]/*ss*/;
        }else {
            return answer[0] + "-" + mount + "-" + +answer[2] + " " + answer[3]/*hh*/ + ":" + answer[4]/*mm*/ + ":" + answer[5]/*ss*/;
        }
    }

    public static String outForDifference(int[] answer){
        return answer[0] + " дней," + answer[1] + " месяцев, " + answer[2] + " год(ов), " + answer[3] + " час(ов)," + answer[4] + " минут(ы)," + answer[5] + " секунд.";
    }

}