package net.vv2.util;

import org.apache.commons.lang3.StringUtils;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhongfs
 */
public class HandleQuotaShowToolUtil {
    private HandleQuotaShowToolUtil() {

    }

    public static List<String> HandleFormatDataMark(List<String> dataMarkResultList) {
        List<String> dataMarkReturnList = new ArrayList<>();
        for (String dataMark : dataMarkResultList) {
            StringBuffer tmp = new StringBuffer();
            if (dataMark.startsWith("20")) {
                if (dataMark.length() == 6) {
                    tmp.append(dataMark.substring(2, 4));
                    tmp.append("-");
                    tmp.append(dataMark.substring(4, 6));
                } else if (dataMark.length() == 8) {
                    tmp.append(dataMark.substring(4, 6));
                    tmp.append("-");
                    tmp.append(dataMark.substring(6, 8));
                } else if (dataMark.length() == 17 && dataMark.contains("-")) {
                    tmp.append(dataMark.substring(4, 8));
                    tmp.append("-");
                    tmp.append(dataMark.substring(13, 17));
                } else {
                    tmp.append(dataMark);
                }
            }
            dataMarkReturnList.add(tmp.toString());
        }
        return dataMarkReturnList;
    }

    public static List<String> HandleFormatDataMarkWithOblique(List<String> dataMarkResultList) {
        List<String> dataMarkReturnList = new ArrayList<>();
        for (String dataMark : dataMarkResultList) {
            StringBuffer tmp = new StringBuffer();
            if (dataMark.startsWith("20")) {
                if (dataMark.length() == 6) {
                    tmp.append(dataMark.substring(2, 4));
                    tmp.append("/");
                    tmp.append(dataMark.substring(4, 6));
                } else if (dataMark.length() == 8) {
                    tmp.append(dataMark.substring(4, 6));
                    tmp.append("/");
                    tmp.append(dataMark.substring(6, 8));
                } else if (dataMark.length() == 17 && dataMark.contains("-")) {
                    tmp.append(dataMark.substring(4, 8));
                    tmp.append("/");
                    tmp.append(dataMark.substring(13, 17));
                } else {
                    tmp.append(dataMark);
                }
            }
            dataMarkReturnList.add(tmp.toString());
        }
        return dataMarkReturnList;
    }


    public static String HandleFormatDataMarkWithOblique(String dataMark) {
        StringBuffer tmp = new StringBuffer();
        if (dataMark.startsWith("20")) {
            if (dataMark.length() == 6) {
                tmp.append(dataMark.substring(2, 4));
                tmp.append("/");
                tmp.append(dataMark.substring(4, 6));
            } else if (dataMark.length() == 8) {
                tmp.append(dataMark.substring(4, 6));
                tmp.append("/");
                tmp.append(dataMark.substring(6, 8));
            } else if (dataMark.length() == 17 && dataMark.contains("-")) {
                tmp.append(dataMark.substring(4, 8));
                tmp.append("/");
                tmp.append(dataMark.substring(13, 17));
            } else {
                tmp.append(dataMark);
            }
        }
        return tmp.toString();
    }


    public static String HandleQuotaValue(String quotaValue) {
        String handleQuotaValue = null;
        if (!StringUtils.isBlank(quotaValue)) {
            if (quotaValue.equals("-")) {
                handleQuotaValue = "0";
            } else if (quotaValue.endsWith(".00") || quotaValue.endsWith(".0")) {
                handleQuotaValue = String.valueOf(Double.valueOf(quotaValue).intValue());
            } else {
                handleQuotaValue = quotaValue;
            }
        } else {
            handleQuotaValue = "0";
        }
        return handleQuotaValue;
    }


    public static String calculateWithPercentageSymbol(Integer a, Integer b) {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        if (b == 0) {
            return "-";
        }
        double cal_value = (a * 1.0 / b) * 100;
        String value = decimalFormat.format(cal_value);
        return HandleQuotaShowToolUtil.HandleQuotaValue(value) + "%";
    }

}
