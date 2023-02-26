package com.github.idimabr.utils;

import java.text.DecimalFormat;

public class MoneyUtil {

    private static final DecimalFormat NUMBER_FORMAT = new DecimalFormat("#.##");
    private static final String[] letters = new String[]{
            "", "K", "M", "B", "T", "Q", "QQ", "S", "SS", "O", "N", "D","UN","DD","TR","QT","QN","SD","SPD","OD",
            "ND","VG","UVG","DVG","TVG","QTV","QNV","SEV","SPG","OVG","NVG","TGN","UTG","DTG","DQG","DQQ"};

    public static String formatValue(double value) {
        if (isInvalid(value)) return "0";

        int index = 0;

        double tmp;
        while ((tmp = value / 1000) >= 1) {
            if (index + 1 == letters.length) break;
            value = tmp;
            ++index;
        }

        return NUMBER_FORMAT.format(value) + letters[index];
    }

    public static boolean isInvalid(double value) {
        return value < 0 || Double.isNaN(value) || Double.isInfinite(value);
    }

}
