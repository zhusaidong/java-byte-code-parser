package cn.zhusaidong.bytecode.parser.structure.parser.attribute;

import cn.zhusaidong.bytecode.parser.interfaces.AttributeParser;
import cn.zhusaidong.bytecode.parser.structure.data.AttributeInfo;
import cn.zhusaidong.bytecode.parser.structure.data.attribute.AttributeSourceFile;
import cn.zhusaidong.bytecode.parser.util.ByteUtil;

import java.io.InputStream;

/**
 * @author zhusaidong
 */
public class AttributeSourceFileParser implements AttributeParser<AttributeInfo> {
    @Override
    public AttributeInfo parser(InputStream is, Object... objs) {
        AttributeSourceFile attributeSourceFile = new AttributeSourceFile();
        attributeSourceFile.setSourceFile("#" + ByteUtil.toDigits(ByteUtil.getBytes(is, 2)));

        return attributeSourceFile;
    }
}
