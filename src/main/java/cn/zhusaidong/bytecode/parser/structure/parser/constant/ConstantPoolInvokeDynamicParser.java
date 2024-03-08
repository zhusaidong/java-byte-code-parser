package cn.zhusaidong.bytecode.parser.structure.parser.constant;

import cn.zhusaidong.bytecode.parser.interfaces.ConstantParser;
import cn.zhusaidong.bytecode.parser.structure.data.ConstantPool;
import cn.zhusaidong.bytecode.parser.structure.data.constant.ConstantPoolInvokeDynamicInfo;
import cn.zhusaidong.bytecode.parser.util.ByteUtil;

import java.io.InputStream;

/**
 * @author zhusaidong
 * @since 2024/1/24
 */
public class ConstantPoolInvokeDynamicParser implements ConstantParser<ConstantPool> {
    @Override
    public ConstantPool parser(InputStream is, Object... objs) {
        return new ConstantPoolInvokeDynamicInfo(ByteUtil.toDigits(ByteUtil.getBytes(is, 2)), ByteUtil.toDigits(ByteUtil.getBytes(is, 2)));
    }
}
