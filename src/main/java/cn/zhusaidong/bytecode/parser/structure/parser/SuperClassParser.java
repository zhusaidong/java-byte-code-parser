package cn.zhusaidong.bytecode.parser.structure.parser;

import cn.zhusaidong.bytecode.parser.interfaces.Parser;
import cn.zhusaidong.bytecode.parser.util.ByteUtil;

import java.io.InputStream;

/**
 * 超类解析器
 *
 * @author zhusaidong
 * @since 2024/1/24
 */
public class SuperClassParser implements Parser<Integer> {
    @Override
    public Integer parser(InputStream is, Object... objs) {
        return ByteUtil.toDigits(ByteUtil.getBytes(is, 2));
    }
}
