package com.kee.common.util;

/**
 * @author mingjin_ding@163.com
 */
public class ByteTools {

    // 16进制的字符集
    private static char[] hexChars = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd',
        'e', 'f'};

    /**
     * 把字节数组转换为16进制的字符串。
     * @param printBytes 要转换的字节数组。
     * @param position 数据偏移地址。
     * @param bytesLength 数据长度。
     * @param constantNumber 每行几个字符。
     * @return 转换后的字符串。
     * @author
     */
    public static String bytesToHexString(byte[] printBytes, int position,
            int bytesLength, int constantNumber) {
        if (null == printBytes) {
            return null;
        }
        if (position < 0) {
            position = 0;
        }
        if (position + bytesLength > printBytes.length) {
            bytesLength = printBytes.length - position;
        }
        StringBuffer tmpSb = new StringBuffer(bytesLength * 4);
        for (int i = 0; i < bytesLength; i++) {
            char firstChar = hexChars[(printBytes[position + i] >> 4) & 0x0f];
            char secondChar = hexChars[printBytes[position + i] & 0x0f];
            tmpSb.append(firstChar).append(secondChar).append(' ');

            if (((i % constantNumber) == 0) && (i != 0)) {
                tmpSb.append('\n');
            }
        }
        return tmpSb.toString();
    }

    /**
     * 把字节数组转换为16进制的字符串，每行16个字符。
     * @param printBytes 要转换的字节数组。
     * @param position 数据偏移地址。
     * @param bytesLength 数据长度。
     * @return 转换后的字符串。
     * @author
     */
    public static String bytesToHexString(byte[] printBytes, int position,
            int bytesLength) {
        return bytesToHexString(printBytes, position, bytesLength, 16);
    }

    /**
     * 把字节数组转换为16进制的字符串，每行16个字符。
     * @param printBytes 要转换的字节数组。
     * @return 转换后的字符串
     * @author
     */
    public static String bytesToHexString(byte[] printBytes) {
        return bytesToHexString(printBytes, 0, printBytes.length);
    }

    /**
     * 比较两个字节数组是否相等。
     * @param abyte0 源字节数组
     * @param abyte1 目标字节数组
     * @return true：相等 false：不相等
     * @author
     */
    public static boolean cmp(byte[] abyte0, byte[] abyte1) {
        if (null == abyte1) {
            return abyte0 == null;
        }
        if (null == abyte0) {
            return false;
        }
        int j = abyte0.length;
        if (j != abyte1.length) {
            return false;
        }
        for (int i = 0; i < j; i++) {
            if (abyte0[i] != abyte1[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * 把2个字节的数组转换为short类型
     * @param abyte 待转换的字节数组
     * @return 转换后的short值
     * @author
     */
    public static short byte2ToShort(byte[] abyte, int offset) {
        return (short) (((abyte[offset + 0] & 0xff) << 8) | (abyte[offset + 1] & 0xff));
    }

    /**
     * 把2个字节的数组转换为short类型*
     * @param abyte 待转换的字节数组
     * @return 转换后的short值
     * @author
     */
    public static short byte2ToShort(byte[] abyte) {
        return byte2ToShort(abyte, 0);
    }

    /**
     * 把2个字节的数组转换为unsigned short类型，在java中用int型表示
     * @param abyte 待转换的字节数组
     * @return 转换后的int值
     * @author
     */
    public static int byte2ToUnsignedShort(byte[] abyte, int offset) {
        return byte2ToShort(abyte, offset) & 0xFFFF;
    }

    /**
     * 把2个字节的数组转换为unsigned short类型，在java中用int型表示
     * @param abyte 待转换的字节数组
     * @return 转换后的int值
     * @author
     */
    public static int byte2TogetUnsignedShort(byte[] abyte) {
        return byte2ToUnsignedShort(abyte, 0);
    }

    /**
     * 把4个字节的数组转换为int类型
     * @param abyte 待转换的字节数组
     * @return 转换后的int值
     * @author
     */
    public static int byte4ToInt(byte[] abyte) {
        return byte4ToInt(abyte, 0);
    }

    /**
     * 把4个字节的数组转换为int类型
     * @param abyte 待转换的字节数组
     * @return 转换后的int值
     * @author
     */
    public static int byte4ToInt(byte[] abyte, int offset) {
        return (abyte[offset + 0] << 24) | ((abyte[offset + 1] & 0xff) << 16)
                | ((abyte[offset + 2] & 0xff) << 8)
                | (abyte[offset + 3] & 0xff);
    }

    /**
     * 把4个字节的数组转换为unsigned int类型，在java中用long型表示
     * @param abyte 待转换的字节数组
     * @return 转换后的long值
     * @author
     */
    public static long byte4ToUnsignedInt(byte[] abyte) {
        return byte4ToUnsignedInt(abyte, 0);
    }

    /**
     * 把4个字节的数组转换为unsigned int类型，在java中用long型表示
     * @param abyte 待转换的字节数组
     * @return 转换后的long值
     * @author
     */
    public static long byte4ToUnsignedInt(byte[] abyte, int offset) {
        return byte4ToInt(abyte, offset) & 0xFFFFFFFFL;
    }

    /**
     * 把8个字节的数组转换为long类型 5
     * @param abyte 待转换的字节数组
     * @return 转换后的long值
     * @author
     */
    public static long byte8ToLong(byte[] abyte, int offset) {
        // 注：一定要将abyte[0]-abyte[3]转化成long，否则系统默认将它们转换成int，这样会计算错误
        return (((long) (abyte[offset] & 0xff) << 56)
                | ((long) (abyte[offset + 1] & 0xff) << 48)
                | ((long) (abyte[offset + 2] & 0xff) << 40)
                | ((long) (abyte[offset + 3] & 0xff) << 32)
                | ((long) (abyte[offset + 4] & 0xff) << 24)
                | ((long) (abyte[offset + 5] & 0xff) << 16)
                | ((long) (abyte[offset + 6] & 0xff) << 8) | (abyte[offset + 7] & 0xff));
    }

    /**
     * 把5个字节的数组转换为long类型 5
     * @param abyte 待转换的字节数组
     * @return 转换后的long值
     * @author
     */
    public static long byte5ToLong(byte[] abyte, int offset) {
        // 注：一定要将abyte[0]-abyte[3]转化成long，否则系统默认将它们转换成int，这样会计算错误
        return (((long) (abyte[offset] & 0xff) << 32)
                | ((long) (abyte[offset + 1] & 0xff) << 24)
                | ((long) (abyte[offset + 2] & 0xff) << 16)
                | ((long) (abyte[offset + 3] & 0xff) << 8) | (abyte[offset + 4] & 0xff));
    }

    /**
     * 把4个字节的数组转换为String类型的IP
     * @param abyte 待转换的字节数组
     * @return 转换后的IP
     * @author
     */
    public static String byte4ToIP(byte[] abyte) {
        String s = "";
        for (int i = 0; i < 4; i++) {
            s = s + (abyte[i] & 0xff) + ".";
        }
        s = s.substring(0, s.length() - 1);
        return s;
    }

    /**
     * 把short类型转换为2个字节的数组
     * @param shortValue 待转换的short值
     * @return 转换后的字节数组
     * @author
     */
    public static byte[] shortToByte2(short shortValue) {
        byte[] abyte = new byte[2];
        abyte[0] = (byte) (shortValue >>> 8);
        abyte[1] = (byte) shortValue;

        return abyte;
    }

    /**
     * 把int类型转换为4个字节的数组
     * @param intValue 待转换的int值
     * @return 转换后的字节数组
     * @author
     */
    public static byte[] intToByte4(int intValue) {
        byte[] abyte = new byte[4];
        abyte[0] = (byte) (intValue >>> 24);
        abyte[1] = (byte) (intValue >>> 16);
        abyte[2] = (byte) (intValue >>> 8);
        abyte[3] = (byte) intValue;
        return abyte;
    }

    /**
     * 把long类型转换为8个字节的数组
     * @param longValue 待转换的long值
     * @return 转换后的字节数组
     * @author
     */
    public static byte[] longTobyte8(long longValue) {
        byte[] abyte = new byte[8];
        abyte[0] = (byte) (longValue >>> 56);
        abyte[1] = (byte) (longValue >>> 48);
        abyte[2] = (byte) (longValue >>> 40);
        abyte[3] = (byte) (longValue >>> 32);
        abyte[4] = (byte) (longValue >>> 24);
        abyte[5] = (byte) (longValue >>> 16);
        abyte[6] = (byte) (longValue >>> 8);
        abyte[7] = (byte) longValue;
        return abyte;
    }

    /**
     * 把long类型转换为8个字节的数组
     * @param longValue 待转换的long值
     * @return 转换后的字节数组
     * @author
     */
    public static byte[] longTobyte5(long longValue) {
        byte[] abyte = new byte[5];
        abyte[0] = (byte) (longValue >>> 32);
        abyte[1] = (byte) (longValue >>> 24);
        abyte[2] = (byte) (longValue >>> 16);
        abyte[3] = (byte) (longValue >>> 8);
        abyte[4] = (byte) longValue;
        return abyte;
    }

    /**
     * 把字符串型的IP转换为4字节的数组
     * @param ip 待转换的IP
     * @return 转换后的字节数组
     * @author
     */
    public static byte[] IPToByte4(String ip) {
        String[] s = ip.split("[.]");
        byte[] abyte = new byte[4];
        for (int i = 0; i < 4; i++) {
            abyte[i] = (byte) Integer.parseInt(s[i]);
        }
        return abyte;
    }

    /**
     * 把byte数组转成字符串
     * @param bytes
     * @return
     */
    public static String dumpBytes(byte[] bytes) {
        if (null == bytes) {
            return "";
        }
        int i;
        StringBuffer sb = new StringBuffer();
        for (i = 0; i < bytes.length; i++) {
            if (((i % 16) == 0) && (i != 0)) {
                sb.append("  ");
            }
            if (((i % 32) == 0) && (i != 0)) {
                sb.append("\n");
            }
            String s = Integer.toHexString(bytes[i]);
            if (s.length() < 2) {
                s = "0" + s;
            }
            if (s.length() > 2) {
                s = s.substring(s.length() - 2);
            }
            sb.append(s);
        }
        return sb.toString();
    }
}
