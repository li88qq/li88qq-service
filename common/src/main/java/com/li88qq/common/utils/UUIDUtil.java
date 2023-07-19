package com.li88qq.common.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * UUID工具类
 *
 * @author li88qq
 * @version 1.0 2022/1/21 23:27
 */
public class UUIDUtil {

    private final static char[] digits = {'0', '1', '2', '3', '4', '5', '6', '7', '8',
            '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l',
            'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y',
            'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L',
            'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y',
            'Z'};

    private final static Map<Character, Integer> digitMap = new HashMap<>();

    static {
        for (int i = 0; i < digits.length; i++) {
            digitMap.put(digits[i], i);
        }
    }

    /**
     * 支持的最大进制数
     */
    private static final int MAX_RADIX = digits.length;

    /**
     * 支持的最小进制数
     */
    private static final int MIN_RADIX = 2;

    /**
     * 将长整型数值转换为指定的进制数（最大支持62进制，字母数字已经用尽）
     *
     * @param i
     * @param radix 进制
     * @return
     */
    private static String toString(long i, int radix) {
        if (radix < MIN_RADIX || radix > MAX_RADIX)
            radix = 10;
        if (radix == 10)
            return Long.toString(i);

        final int size = 65;
        int charPos = 64;

        char[] buf = new char[size];
        boolean negative = (i < 0);

        if (!negative) {
            i = -i;
        }

        while (i <= -radix) {
            buf[charPos--] = digits[(int) (-(i % radix))];
            i = i / radix;
        }
        buf[charPos] = digits[(int) (-i)];

        if (negative) {
            buf[--charPos] = '-';
        }

        return new String(buf, charPos, (size - charPos));
    }

    private static String digits(long val, int digits) {
        long hi = 1L << (digits * 4);
        return toString(hi | (val & (hi - 1)), UUIDUtil.MAX_RADIX)
                .substring(1);
    }

    /**
     * 8位uuid,32位uuid,4位一组,每组取62模
     *
     * @return 8位
     */
    public static String uuid8() {
        String uuid = uuid32();
        StringBuilder sb = new StringBuilder();
        String code = null;
        int value = 0;
        int radix = digits.length;
        for (int i = 0; i < 8; i++) {
            code = uuid.substring(i * 4, i * 4 + 4);
            value = Integer.parseInt(code, 16);
            sb.append(digits[value % radix]);
        }
        return sb.toString();
    }

    /**
     * 以62进制（字母加数字）生成19位UUID，最短的UUID
     *
     * @return 19位
     */
    public static String uuid19() {
        UUID uuid = UUID.randomUUID();
        StringBuilder sb = new StringBuilder();
        sb.append(digits(uuid.getMostSignificantBits() >> 32, 8));
        sb.append(digits(uuid.getMostSignificantBits() >> 16, 4));
        sb.append(digits(uuid.getMostSignificantBits(), 4));
        sb.append(digits(uuid.getLeastSignificantBits() >> 48, 4));
        sb.append(digits(uuid.getLeastSignificantBits(), 12));
        return sb.toString();
    }

    /**
     * 获取去除分隔符后的32位uuid
     *
     * @return 32位
     */
    public static String uuid32() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

}
