package cn.zhusaidong.bytecode.parser.structure.parser;

import cn.zhusaidong.bytecode.parser.enums.JdkVersionEnum;
import cn.zhusaidong.bytecode.parser.interfaces.Parser;
import cn.zhusaidong.bytecode.parser.util.ByteUtil;

import java.io.InputStream;

/**
 * 版本解析器
 *
 * @author zhusaidong
 * @since 2024/1/24
 */
public class VersionParser implements Parser<JdkVersionEnum> {
    @Override
    public JdkVersionEnum parser(InputStream is, Object... objs) {
        //次版本：读取2个字节，转十进制数字（jdk6以前有次版本，jdk6以后都为0）
        int minorVersion = ByteUtil.toDigits(ByteUtil.getBytes(is, 2));
        //主版本：读取2个字节，转十进制数字
        int majorVersion = ByteUtil.toDigits(ByteUtil.getBytes(is, 2));

        return JdkVersionEnum.find(majorVersion, minorVersion);
    }
}
