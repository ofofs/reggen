package com.github.ofofs.reggen;

/**
 * @author kangyonggan
 * @since 7/25/18
 */
public class ReggenTest {

    public static void main(String[] args) {
        String regex = "[a-d]+\\d_[369]{5,}(a|b)\\sxx";
        print5(regex);

        regex = "^[1-9]\\d{5}(18|19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$";
        print5(regex);

        regex = "^1[3458]\\d{9}$";
        print5(regex);
    }

    private static void print5(String regex) {
        System.out.println("============================================");
        System.out.println("Regex:" + regex + ":");
        for (String result : RegexGenerator.generate(regex, 5)) {
            System.out.println(result);
        }
        System.out.println("============================================\n\n");
    }

}
