package cn.zhusaidong.bytecode.parser.structure.parser.attribute;

import cn.zhusaidong.bytecode.parser.interfaces.AttributeParser;
import cn.zhusaidong.bytecode.parser.structure.data.AttributeInfo;
import cn.zhusaidong.bytecode.parser.structure.data.attribute.AttributeExceptions;
import cn.zhusaidong.bytecode.parser.util.ByteUtil;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * @author zhusaidong
 */
public class AttributeExceptionsParser implements AttributeParser<AttributeInfo> {
    @Override
    public AttributeInfo parser(InputStream is, Object... objs) {
        AttributeExceptions attributeExceptions = new AttributeExceptions();
        attributeExceptions.setNumberOfExceptions(ByteUtil.toDigits(ByteUtil.getBytes(is, 2)));
        ArrayList<Integer> exceptionIndexTables = new ArrayList<>();
        for (int i = 0; i < attributeExceptions.getNumberOfExceptions(); i++) {
            exceptionIndexTables.add(ByteUtil.toDigits(ByteUtil.getBytes(is, 2)));
        }
        attributeExceptions.setExceptionIndexTables(exceptionIndexTables);

        return attributeExceptions;
    }
}
