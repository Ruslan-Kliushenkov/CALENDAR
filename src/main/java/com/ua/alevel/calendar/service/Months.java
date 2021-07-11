package com.ua.alevel.calendar.service;

public enum Months {
    DEFAULT("default",-1,-1),
    JANUARY("january",31,1),
    FEBRUARY("february",28,2),
    MARCH("march",31,3),
    APRIL("april",30,4),
    MAY("may",30,5),
    JUNE("june",30,6),
    JULY("july",31,7),
    AUGUST("august",31,8),
    SEPTEMBER("september",30,9),
    OCTOBER("october",31,10),
    NOVEMBER("november",30,11),
    DECEMBER("december",31,12);

    private final String inRussian;
    private final int days;
    private final int mountsNumber;

    Months(String inRussian,int days,int mountsNumber) {
        this.inRussian = inRussian;
        this.days = days;
        this.mountsNumber = mountsNumber;
    }

    public String getInRussian() {
        return inRussian;
    }

    public int getDays() {
        return days;
    }

    public int getMountsNumber() {
        return mountsNumber;
    }



   /* public static int[] getAllMountsNumber(){
        return new int[]{JANUARY.mountsNumber, FEBRUARY.mountsNumber, MARCH.mountsNumber,
                APRIL.mountsNumber, MAY.mountsNumber, JUNE.mountsNumber,
                JULY.mountsNumber, AUGUST.mountsNumber, SEPTEMBER.mountsNumber,
                OCTOBER.mountsNumber, NOVEMBER.mountsNumber, DECEMBER.mountsNumber};
    }*/
}
