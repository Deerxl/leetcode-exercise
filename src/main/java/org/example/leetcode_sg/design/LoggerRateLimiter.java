package org.example.leetcode_sg.design;

import java.util.HashMap;
import java.util.Map;

/**
 * <a href="https://www.hellointerview.com/community/questions/logger-rate-limiter/cm5eh7nri04wz838onqhny0wc">Leetcode 359. Logger Rate Limiter</a>
 */
public class LoggerRateLimiter {

    public static Map<String, Integer> map = new HashMap<>();

    public static boolean shouldPrintMessage(int timestamp, String message) {
        if (!map.containsKey(message) || timestamp - map.get(message) >= 10) {
            map.put(message, timestamp);
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        System.out.println(shouldPrintMessage(1, "foo"));
        System.out.println(shouldPrintMessage(2, "bar"));
        System.out.println(shouldPrintMessage(3, "foo"));
        System.out.println(shouldPrintMessage(8, "bar"));
        System.out.println(shouldPrintMessage(10, "foo"));
        System.out.println(shouldPrintMessage(11, "foo"));

    }

}
