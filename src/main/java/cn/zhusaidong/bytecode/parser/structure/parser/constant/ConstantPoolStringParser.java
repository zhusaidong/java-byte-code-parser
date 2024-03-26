package cn.zhusaidong.bytecode.parser.structure.parser.constant;

import cn.zhusaidong.bytecode.parser.interfaces.ConstantParser;
import cn.zhusaidong.bytecode.parser.structure.data.ConstantPool;
import cn.zhusaidong.bytecode.parser.structure.data.constant.ConstantPoolStringInfo;
import cn.zhusaidong.bytecode.parser.util.ByteUtil;

import java.io.InputStream;

/**
 * @author zhusaidong
 */
public class ConstantPoolStringParser implements ConstantParser<ConstantPool> {
    @Override
    public ConstantPool parser(InputStream is, Object... objs) {
        return new ConstantPoolStringInfo(ByteUtil.toDigits(ByteUtil.getBytes(is, 2)));
    }
}
