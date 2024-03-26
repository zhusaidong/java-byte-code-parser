package cn.zhusaidong.bytecode.parser.structure.parser.attribute;

import cn.zhusaidong.bytecode.parser.enums.AccessFlagTypeEnum;
import cn.zhusaidong.bytecode.parser.interfaces.AttributeParser;
import cn.zhusaidong.bytecode.parser.structure.data.AttributeInfo;
import cn.zhusaidong.bytecode.parser.structure.data.attribute.AttributeMethodParameters;
import cn.zhusaidong.bytecode.parser.structure.parser.AccessFlagParser;
import cn.zhusaidong.bytecode.parser.util.ByteUtil;
import cn.zhusaidong.bytecode.parser.util.ParserCacheUtil;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * @author zhusaidong
 */
public class AttributeMethodParametersParser implements AttributeParser<AttributeInfo> {
    @Override
    public AttributeInfo parser(InputStream is, Object... objs) {
        AttributeMethodParameters attributeMethodParameters = new AttributeMethodParameters();
        attributeMethodParameters.setParametersCount(ByteUtil.toDigits(ByteUtil.getBytes(is, 2)));

        ArrayList<AttributeMethodParameters.Parameter> parameters = new ArrayList<>();
        for (int i = 0; i < attributeMethodParameters.getParametersCount(); i++) {
            AttributeMethodParameters.Parameter parameter = new AttributeMethodParameters.Parameter();
            parameter.setName("#" + ByteUtil.toDigits(ByteUtil.getBytes(is, 2)));
            parameter.setAccessFlags(ParserCacheUtil.getParser(AccessFlagParser.class).parser(is, AccessFlagTypeEnum.FIELD));
            parameters.add(parameter);
        }
        attributeMethodParameters.setParameters(parameters);

        return attributeMethodParameters;
    }
}
