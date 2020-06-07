package net.vv2.util;

import java.math.BigDecimal;

/**
 * @author zhongfs
 */
public class BigDecimalUtils {
    private BigDecimalUtils() {

    }

    public static BigDecimal getBigDecimalFromString(String value) {
        BigDecimal bigDecimal = new BigDecimal(value);
        return bigDecimal;
    }

    public static String getNegate(String value) {
        BigDecimal bigDecimal = new BigDecimal(value);
        return bigDecimal.negate().stripTrailingZeros().toPlainString();
    }

    public static String AddMoreParam(String... args) {
        BigDecimal bigDecimal = new BigDecimal("0");
        for (String tmp : args) {
            BigDecimal tmpDecimal = new BigDecimal(tmp);
            bigDecimal = bigDecimal.add(tmpDecimal);
        }
        return bigDecimal.stripTrailingZeros().toPlainString();
    }

    public static BigDecimal add(double v1, double v2) {// v1 + v2
        String value1 = String.valueOf(v1);
        String value2 = String.valueOf(v2);
        BigDecimal b1 = new BigDecimal(value1);
        BigDecimal b2 = new BigDecimal(value2);
        return b1.add(b2);
    }

    public static BigDecimal add(String v1, String v2) {// v1 + v2
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.add(b2);
    }

    public static BigDecimal sub(double v1, double v2) {
        String value1 = String.valueOf(v1);
        String value2 = String.valueOf(v2);
        BigDecimal b1 = new BigDecimal(value1);
        BigDecimal b2 = new BigDecimal(value2);
        return b1.subtract(b2);
    }

    public static String sub(String v1, String v2) {
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.subtract(b2).stripTrailingZeros().toPlainString();
    }

    public static BigDecimal mul(double v1, double v2) {
        String value1 = String.valueOf(v1);
        String value2 = String.valueOf(v2);
        BigDecimal b1 = new BigDecimal(value1);
        BigDecimal b2 = new BigDecimal(value2);
        return b1.multiply(b2);
    }

    public static BigDecimal div(double v1, double v2) {
        String value1 = String.valueOf(v1);
        String value2 = String.valueOf(v2);
        BigDecimal b1 = new BigDecimal(value1);
        BigDecimal b2 = new BigDecimal(value2);
        // 2 = 保留小数点后两位   ROUND_HALF_UP = 四舍五入
        // 应对除不尽的情况
        return b1.divide(b2, 2, BigDecimal.ROUND_HALF_UP);
    }

}
