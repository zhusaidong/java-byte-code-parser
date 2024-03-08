package cn.zhusaidong.bytecode.parser.structure.parser;

import cn.zhusaidong.bytecode.parser.interfaces.Parser;
import cn.zhusaidong.bytecode.parser.util.ByteUtil;

import java.io.InputStream;

/**
 * 魔数解析器
 *
 * @author zhusaidong
 * @since 2024/1/24
 */
public class MagicParser implements Parser<String> {
    @Override
    public String parser(InputStream is, Object... objs) {
        //魔数：读取4个字节，转16进制，内容为`cafebabe`
        return ByteUtil.toHex(ByteUtil.getBytes(is, 4));
    }
}
