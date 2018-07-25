# Regex Generator
正则表达式生成器。可以根据指定的正则表达式随机生成符合正则表达式的字符串。目前有部分特殊字符无法生成，比如：`\r`、`\n`等。

## 用法
依赖：  
```
<dependency>
    <groupId>com.github.ofofs</groupId>
    <artifactId>reggen</artifactId>
    <version>${reggen.version}</version>
</dependency>
```

使用：  
```
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
```

输出：  
```
============================================
Regex:[a-d]+\d_[369]{5,}(a|b)\sxx:
c8_669936939b  xx
b9_3333663999a  xx
adb0_69336639693a  xx
bc9_3636996b	 xx
cbdc2_936666933a	 xx
============================================


============================================
Regex:^[1-9]\d{5}(18|19|([23]\d))\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$:
27406730860301937x
177685208906314796
310062185111065021
543143184910079967
39380825300909652x
============================================


============================================
Regex:^1[3458]\d{9}$:
18921893040
18296230018
15013302377
14872682845
18109083914
============================================
```



 




 