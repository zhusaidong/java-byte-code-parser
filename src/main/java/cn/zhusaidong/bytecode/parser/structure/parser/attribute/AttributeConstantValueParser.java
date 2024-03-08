package cn.zhusaidong.bytecode.parser.structure.parser.attribute;

import cn.zhusaidong.bytecode.parser.interfaces.AttributeParser;
import cn.zhusaidong.bytecode.parser.structure.data.AttributeInfo;
import cn.zhusaidong.bytecode.parser.structure.data.attribute.AttributeConstantValue;
import cn.zhusaidong.bytecode.parser.util.ByteUtil;

import java.io.InputStream;

/**
 * @author zhusaidong
 */
public class AttributeConstantValueParser implements AttributeParser<AttributeInfo> {
    @Override
    public AttributeInfo parser(InputStream is, Object... objs) {
        AttributeConstantValue attributeConstantValue = new AttributeConstantValue();
        attributeConstantValue.setConstantValueIndex(ByteUtil.toDigits(ByteUtil.getBytes(is, 2)));

        return attributeConstantValue;
    }
}
