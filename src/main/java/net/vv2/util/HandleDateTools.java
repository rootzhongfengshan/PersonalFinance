package net.vv2.util;

import com.xiaoleilu.hutool.date.DateUtil;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.xiaoleilu.hutool.date.DateUtil;

/**
 * @author zhongfs
 */
public class HandleDateTools {
    private HandleDateTools() {

    }

    public static Map<String, String> dealWithStartDateAndEndDate(String start_date, String end_date) {
        boolean flag = StringUtils.isEmpty(start_date) || StringUtils.isEmpty(end_date);
        if (flag) {
            Date today = DateUtil.date();
            start_date = DateUtil.beginOfMonth(today).toString("yyyy-MM-dd");
            end_date = DateUtil.formatDate(today);
        }
        Map<String, String> map = new HashMap<>(8);
        map.put("start_date", start_date);
        map.put("end_date", end_date);
        return map;
    }

    public static String dealWithStartDate(String start_date) {
        boolean flag = StringUtils.isEmpty(start_date);
        if (flag) {
            Date today = DateUtil.date();
            start_date = DateUtil.beginOfMonth(today).toString("yyyy-MM-dd");
        }
        return start_date;
    }

    public static String dealWithEndDate(String end_date) {
        boolean flag = StringUtils.isEmpty(end_date);
        if (flag) {
            Date today = DateUtil.date();
            end_date = DateUtil.formatDate(today);
        }
        return end_date;
    }

    public static String returnToDay(String end_date) {
        boolean flag = StringUtils.isEmpty(end_date);
        if (flag) {
            Date today = DateUtil.date();
            end_date = DateUtil.formatDate(today);
        }
        return end_date;
    }

    public static String getStartDayOfMonth(String start_date) {
        boolean flag = StringUtils.isEmpty(start_date);
        if (flag) {
            Date today = DateUtil.date();
            start_date = DateUtil.beginOfMonth(today).toString("yyyy-MM-dd");
        }
        return start_date + "-01";
    }

    public static String getEndDayOfMonth(String end_date) {
        boolean flag = StringUtils.isEmpty(end_date);
        if (flag) {
            Date today = DateUtil.date();
            end_date = DateUtil.endOfMonth(today).toString("yyyy-MM-dd");
            ;
        }
        return end_date;
    }
}
