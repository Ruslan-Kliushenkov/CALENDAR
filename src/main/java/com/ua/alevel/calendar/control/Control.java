package com.ua.alevel.calendar.control;

import com.ua.alevel.calendar.service.CalcCore;
import com.ua.alevel.calendar.service.Correct;
import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Control {
    private int dateFormat;
    BufferedReader read = new BufferedReader(new InputStreamReader(System.in));

    @SneakyThrows
    public void startConfig() {
        System.out.println("Пожалуйста, выберите формат ввода/вывода");
        System.out.println("1. dd/mm/yyyy \"01/01/0000\"");
        System.out.println("2. d/m/yyyy \"1/11/0000\"");
        System.out.println("3. d-mmm-yyyy \"december-1-1212\"");
        System.out.println("4. dd-mmm-yyyy 00:00 \"01-march-1789 00:00\"");
        switch (read.readLine()) {
            case ("1") -> dateFormat = 1;
            case ("2") -> dateFormat = 2;
            case ("3") -> dateFormat = 3;
            case ("4") -> dateFormat = 4;
            default -> {
                System.out.println("Неподдерживаемый формат");
                startConfig();
            }
        }
        cmdList();
    }

    public void cmdList() {
        System.out.println("Список доступных команд:");
        System.out.println("1 - вычислить разницу между двумя датами");
        System.out.println("2 - прибавить к дате 'n' времени");
        System.out.println("3 - вычесть из даты 'n' времени");
        System.out.println("4 - выстроить даты в заданом порядке порядке");
        System.out.println("5 - для настройки формата ввода/вывода");
        System.out.println();
        cmd();
    }

    @SneakyThrows
    public void cmd() {
        String command = read.readLine();
        switch (command.toLowerCase(Locale.ROOT).replaceAll("\\s+", "")) {
            case ("1") -> difference();
            case ("2") -> add();
            case ("3") -> subtract();
            case ("4") -> queue();
            case ("5") -> startConfig();
            default -> unknownCmd(command);
        }
    }

    @SneakyThrows
    private void difference() {
        System.out.println("Введите первую дату");
        String firstDate = read.readLine();
        if (firstDate.equals("0")) {
            cmdList();
        }
        if (!Correct.checkCorrect(firstDate, this.dateFormat)) {
            System.out.println("Некорректный ввод, пожалуйста, попробуйте ещё раз");
            System.out.println("Для выхода в главное меню введите 0");
            difference();
        }
        System.out.println("Введите вторую дату");
        String secondDate = read.readLine();
        if (secondDate.equals("0")) {
            cmdList();
        }
        if (!Correct.checkCorrect(secondDate, this.dateFormat)) {
            System.out.println("Некорректный ввод, пожалуйста, попробуйте ещё раз");
            System.out.println("Для выхода в главное меню введите 0");
            difference();
        }

        System.out.println("Разница: " + CalcCore.calcDifference(firstDate, secondDate, dateFormat));
        cmdList();
    }

    @SneakyThrows
    private void add() {
        System.out.println("Введите стартовую дату для вычислений");
        String date = read.readLine();
        if (date.equals("0")) {
            cmdList();
        }
        if (!Correct.checkCorrect(date, dateFormat)) {
            System.out.println("Некорректный ввод, пожалуйста, попробуйте ещё раз");
            System.out.println("Для выхода в главное меню введите 0");
            add();
        }
        System.out.println("Введите количество добавляемого времени в формате \" yyyy, mm(mounts), dd, hours, mm(minutes), ss\"");
        String add = read.readLine();
        if (date.equals("0")) {
            cmdList();
        }
        if (!Correct.checkCorrect(add, 5)) {
            System.out.println("Некорректный ввод, пожалуйста, попробуйте ещё раз");
            System.out.println("Для выхода в главное меню введите 0");
            add();
        }
        System.out.println("Результат: " + CalcCore.calcAdd(date, add, dateFormat));
        cmdList();
    }

    @SneakyThrows
    private void subtract() {
        System.out.println("Введите стартовую дату для вычислений");
        String date = read.readLine();
        if (date.equals("0")) {
            cmdList();
        }
        if (!Correct.checkCorrect(date, dateFormat)) {
            System.out.println("Некорректный ввод, пожалуйста, попробуйте ещё раз");
            System.out.println("Для выхода в главное меню введите 0");
            subtract();
        }
        System.out.println("Введите количество вычетаемого времени в формате\" yyyy, mm(mounts), dd, hours, mm(minutes), ss\"");
        String add = read.readLine();
        if (date.equals("0")) {
            cmdList();
        }
        if (!Correct.checkCorrect(add, 5)) {
            System.out.println("Некорректный ввод, пожалуйста, попробуйте ещё раз");
            System.out.println("Для выхода в главное меню введите 0");
            subtract();
        }
        System.out.println("Результат: " + CalcCore.calcSubtract(date, add, dateFormat));
        cmdList();
    }

    @SneakyThrows
    private void queue() {
        List<String> dates = new ArrayList<>();
        System.out.println("Вводите даты в выбраном формате.");
        System.out.println("Для сортировки по возрастанию введите 1");
        System.out.println("Для сортировки по убыванию введите 2");
        System.out.println("Для выхода в главное меню введите 0");
        while (true) {
            String date = read.readLine();
            switch (date) {
                case ("0"):
                    cmdList();
                    break;
                case ("1"):
                    CalcCore.calcQueue(dates, dateFormat).forEach(System.out::println);
                    break;
                case ("2"):
                    CalcCore.calcReversQueue(dates, dateFormat).forEach(System.out::println);
                    break;
                default:
                    if (Correct.checkCorrect(date, dateFormat)) {
                        dates.add(date);
                    } else {
                        System.out.println("Некорректный ввод, пожалуйста, попробуйте ещё раз");
                        queue();
                    }

            }

        }
    }

    private void unknownCmd(String command) {
        System.out.println("Неизвестная команда : \"" + command + "\". Пожалуйста, попробуйте ввести снова.");
        cmdList();

    }
}

