package cn.zhusaidong.bytecode.parser.util;

import lombok.experimental.UtilityClass;

import java.io.IOException;
import java.io.InputStream;

/**
 * 字节处理工具
 *
 * @author zhusaidong
 * @since 2024/1/19
 */
@UtilityClass
public class ByteUtil {
    private final static String[] HEX_CHARS = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};

    /**
     * 字节数组转16进制字符串
     *
     * @param bytes 字节数组
     * @return 16进制字符串
     */
    public String toHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(HEX_CHARS[(b & 0xFF) >> 4]).append(HEX_CHARS[b & 0x0F]);
        }
        return sb.toString();
    }

    /**
     * 字节数组转16进制字符串数组
     *
     * @param bytes 字节数组
     * @return 16进制字符串数组
     */
    public String[] toHexArray(byte[] bytes) {
        String[] hexArr = new String[bytes.length];
        for (int i = 0; i < bytes.length; i++) {
            byte b = bytes[i];
            hexArr[i] = HEX_CHARS[(b & 0xFF) >> 4] + HEX_CHARS[b & 0x0F];
        }
        return hexArr;
    }

    /**
     * 字节数组转数字数组
     *
     * @param bytes 字节数组
     * @return 数字数组
     */
    public Integer toDigits(byte[] bytes) {
        return Integer.parseInt(toHex(bytes), 16);
    }

    /**
     * 从输入流读取相应长度的字节
     *
     * @param is  输入流
     * @param len 相应长度
     * @return 相应长度的字节
     */
    public byte[] getBytes(InputStream is, int len) {
        byte[] bytes = new byte[len];
        try {
            if (is.read(bytes) > 0) {
                return bytes;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return bytes;
    }
}
