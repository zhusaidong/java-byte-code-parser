package cn.zhusaidong.bytecode.parser.structure.parser;

import cn.zhusaidong.bytecode.parser.domain.ParserCache;
import cn.zhusaidong.bytecode.parser.enums.AccessFlagTypeEnum;
import cn.zhusaidong.bytecode.parser.interfaces.Parser;
import cn.zhusaidong.bytecode.parser.structure.data.constant.ConstantPoolFieldOrMethods;
import cn.zhusaidong.bytecode.parser.util.ByteUtil;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 字段解析器
 *
 * @author zhusaidong
 * @since 2024/1/24
 */
public class FieldsParser implements Parser<List<ConstantPoolFieldOrMethods>> {
    @Override
    public List<ConstantPoolFieldOrMethods> parser(InputStream is, Object... objs) {
        //字段数，2字节
        Integer fieldsCount = ByteUtil.toDigits(ByteUtil.getBytes(is, 2));
        //字段表
        List<ConstantPoolFieldOrMethods> fieldList = new ArrayList<>(fieldsCount);
        for (int i = 0; i < fieldsCount; i++) {
            ConstantPoolFieldOrMethods field = new ConstantPoolFieldOrMethods();

            field.setAccessFlags(new AccessFlagParser().parser(is, AccessFlagTypeEnum.FIELD));
            field.setName("#" + ByteUtil.toDigits(ByteUtil.getBytes(is, 2)));
            field.setDescriptor("#" + ByteUtil.toDigits(ByteUtil.getBytes(is, 2)));
            field.setAttributes(ParserCache.getParser(AttributesParser.class).parser(is, objs[0]));

            fieldList.add(field);
        }
        return fieldList;
    }
}
