package com.test.util;


import org.iban4j.CountryCode;
import org.iban4j.Iban;

public class Random {
    private static int shortRandAdd = 0;
    private static int longRandAdd = 0;

    public static int genInt(int from, int to) {
        int tmp = 0;
        if (to >= from)
            tmp = (int) (from + Math.round((Math.random() * (to - from))));
        return tmp;
    }

    public static long genLong(long from, long to) {
        long tmp = 0;
        if (to >= from)
            tmp = (long) (from + Math.round((Math.random() * (to - from))));
        return tmp;
    }

    public static float genFloat(double from, double to) {
        float tmp = .0f;
        if (to >= from)
            tmp = (float) (from + (Math.random() * (to - from)));
        return tmp;
    }

    public static float genFloat(double from, double to, int precision) {
        float number = genFloat(from, to);
        return (float) Math.round(number * Math.pow(10, precision)) / (float) Math.pow(10, precision);
    }

    public static synchronized long genRandNumberByTime() {
        return System.currentTimeMillis() % 10_000_000_000L + longRandAdd++;
    }

    public static synchronized long genShortRandNumberByTime() {
        return (genInt(1, 9) * 10_000_000) + (System.currentTimeMillis() % 10_000_000) + shortRandAdd++;
    }


    public static String genAddress() {
        return "Test " + Random.genString(4) + " Avenue, " + Random.genInt(1,1000);
    }



    public static String genPostalCode() {
        return "" + Random.genInt(1, 9) + Random.genInt(10, 99) + Random.genInt(10, 99);
    }

    public static String genURL(){
        return "http://test" + Random.genString(5) + ".com";
    }

    public static String genTimestart(){
        String[] times = {"Immediatelly", "In about a week", "In about a month"};
        int value = Random.genInt(0,2);
        return times[value];
    }

    public static String genCity() {
        return "New test " + Random.genString(4) + "ville";
    }


    public static String genMobilePhone() {
        return "555" + genInt(1000000, 9999999);
    }



    public static String genEmail(String emailPattern) {
        return emailPattern.substring(0, emailPattern.indexOf("@")) + "+" + genShortRandNumberByTime()
                + emailPattern.substring(emailPattern.indexOf("@"), emailPattern.length());
    }


    public static String genString(int length) {
        String s = "";
        for (int i = 0; i < length; i++) {
            s = s + Character.toString((char) Random.genInt(97, 120));
        }
        return s;
    }

    public static String generateIban() {
        Iban iban = new Iban.Builder()
                .countryCode(CountryCode.DE)
                .bankCode("26750001")
                .accountNumber(String.valueOf(genLong(1_000_000_000L, 9_999_000_000L)))
                .build();
        return iban.toString();
    }
}
