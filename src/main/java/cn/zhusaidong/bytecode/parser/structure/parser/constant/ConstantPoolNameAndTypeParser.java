package cn.zhusaidong.bytecode.parser.structure.parser.constant;

import cn.zhusaidong.bytecode.parser.interfaces.ConstantParser;
import cn.zhusaidong.bytecode.parser.structure.data.ConstantPool;
import cn.zhusaidong.bytecode.parser.structure.data.constant.ConstantPoolNameAndTypeInfo;
import cn.zhusaidong.bytecode.parser.util.ByteUtil;

import java.io.InputStream;

/**
 * @author zhusaidong
 * @since 2024/1/24
 */
public class ConstantPoolNameAndTypeParser implements ConstantParser<ConstantPool> {
    @Override
    public ConstantPool parser(InputStream is, Object... objs) {
        return new ConstantPoolNameAndTypeInfo(ByteUtil.toDigits(ByteUtil.getBytes(is, 2)), ByteUtil.toDigits(ByteUtil.getBytes(is, 2)));
    }
}
