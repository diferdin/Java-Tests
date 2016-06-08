package com.diferdin.basket;

import java.text.DecimalFormat;

/**
 * Created by LONADF on 07/06/2016.
 */
public class Utils {
    private static Utils ourInstance = new Utils();

    public static Utils getInstance() {
        return ourInstance;
    }

    private Utils() {
    }

    public static double formatNumber(double number) {
        DecimalFormat formatter = new DecimalFormat("####.##");
        return Double.parseDouble(formatter.format(number));
    }
}
