package com.egybank.helpers;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TimeAgo {
    public static final List<Long> times = Arrays.asList(
            TimeUnit.DAYS.toMillis(365),
            TimeUnit.DAYS.toMillis(30),
            TimeUnit.DAYS.toMillis(1),
            TimeUnit.HOURS.toMillis(1),
            TimeUnit.MINUTES.toMillis(1),
            TimeUnit.SECONDS.toMillis(1));
    public static final List<String> timesString = Arrays.asList("year", "month", "day", "hour", "minute", "second");

    public static String toTimeAgo(long date) {
        long duration = Math.abs(System.currentTimeMillis()-date);
        StringBuffer res = new StringBuffer();
        for (int i = 0; i < TimeAgo.times.size(); i++) {
            Long current = TimeAgo.times.get(i);
            long temp = duration / current;
            if (temp > 0) {
                res.append(temp).append(" ").append(TimeAgo.timesString.get(i)).append(temp != 1 ? "s" : "").append(" ago");
                break;
            }
        }
        if ("".equals(res.toString()))
            return "0 seconds ago";
        else
            return res.toString();
    }

    public static String toTimeDifference(long date) {
        long durationNotAbsolute = System.currentTimeMillis()-date;
        boolean isInPast = durationNotAbsolute >= 0;
        long duration = Math.abs(durationNotAbsolute);
        StringBuffer res = new StringBuffer();
        for (int i = 0; i < TimeAgo.times.size(); i++) {
            Long current = TimeAgo.times.get(i);
            long temp = duration / current;
            if (temp > 0) {
                if (!isInPast) {
                    res.append("in ").append(temp).append(" ").append(TimeAgo.timesString.get(i)).append(temp != 1 ? "s" : "");
                }else {
                    res.append("by ").append(temp).append(" ").append(TimeAgo.timesString.get(i)).append(temp != 1 ? "s" : "");
                }
                break;
            }
        }
        if ("".equals(res.toString()))
            return "by 0 seconds";
        else
            return res.toString();
    }
}