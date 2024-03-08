package cn.zhusaidong.bytecode.parser.structure.parser;

import cn.zhusaidong.bytecode.parser.domain.ParserCache;
import cn.zhusaidong.bytecode.parser.interfaces.AttributeParser;
import cn.zhusaidong.bytecode.parser.interfaces.Parser;
import cn.zhusaidong.bytecode.parser.structure.data.AttributeInfo;
import cn.zhusaidong.bytecode.parser.structure.data.ConstantPool;
import cn.zhusaidong.bytecode.parser.structure.data.constant.ConstantPoolUtf8Info;
import cn.zhusaidong.bytecode.parser.util.ByteUtil;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static cn.zhusaidong.bytecode.parser.domain.ParserConstants.ATTRIBUTE_PARSER_MAP;

/**
 * 属性解析器
 *
 * @author zhusaidong
 * @since 2024/1/24
 */
public class AttributesParser implements Parser<List<AttributeInfo>> {
    @Override
    public List<AttributeInfo> parser(InputStream is, Object... objs) {
        @SuppressWarnings("unchecked")
        List<ConstantPool> constantPools = (List<ConstantPool>) objs[0];
        //属性数，2字节
        Integer attributesCount = ByteUtil.toDigits(ByteUtil.getBytes(is, 2));
        //属性表
        List<AttributeInfo> attributeList = new ArrayList<>(attributesCount);
        for (int j = 0; j < attributesCount; j++) {
            //属性表
            Integer attributeNameIndex = ByteUtil.toDigits(ByteUtil.getBytes(is, 2));
            Integer attributeLength = ByteUtil.toDigits(ByteUtil.getBytes(is, 4));

            //每个属性都有自己的数据结构，需要根据属性名解析成对应数据
            ConstantPoolUtf8Info constant = (ConstantPoolUtf8Info) constantPools.get(attributeNameIndex);
            String attrKey = constant.getUtf8Info();

            AttributeInfo attributeInfo;
            Class<? extends AttributeParser<AttributeInfo>> aClass = ATTRIBUTE_PARSER_MAP.getOrDefault(attrKey, null);
            if (aClass == null) {
                attributeInfo = new AttributeInfo();
                attributeInfo.setAttributeName(attrKey);
                attributeInfo.setAttributeLength(attributeLength);
                attributeInfo.setInfo(ByteUtil.getBytes(is, attributeInfo.getAttributeLength()));
            } else {
                attributeInfo = ParserCache.getParser(aClass).parser(is, objs[0]);
            }
            attributeList.add(attributeInfo);
        }
        return attributeList;
    }
}
