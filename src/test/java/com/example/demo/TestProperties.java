package com.example.demo;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author luobo.cs@raycloud.com
 * @since 2021/7/30 9:38 下午
 */
public class TestProperties {

    public static void main(String[] args) {
        ArrayList<String> strings = new ArrayList<>();
        strings.add("no-such-method-error-2.0-SNAPSHOT.jar");
        strings.add("no-such-method-error-1.0-SNAPSHOT.jar");
        strings.add("no-such-method-error-0.1-SNAPSHOT.jar");
        final String[] sorts = strings.toArray(new String[0]);
        Arrays.sort(sorts, String.CASE_INSENSITIVE_ORDER);
        for (String string : sorts) {
            System.out.println(string);
        }
    }

}
