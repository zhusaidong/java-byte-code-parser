package cn.zhusaidong.bytecode.parser.structure.parser.attribute;

import cn.zhusaidong.bytecode.parser.interfaces.AttributeParser;
import cn.zhusaidong.bytecode.parser.structure.data.AttributeInfo;
import cn.zhusaidong.bytecode.parser.structure.data.attribute.AttributeBootstrapMethods;
import cn.zhusaidong.bytecode.parser.structure.data.attribute.AttributeBootstrapMethods.BootstrapMethod;
import cn.zhusaidong.bytecode.parser.util.ByteUtil;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhusaidong
 */
public class AttributeBootstrapMethodsParser implements AttributeParser<AttributeInfo> {
    @Override
    public AttributeInfo parser(InputStream is, Object... objs) {
        AttributeBootstrapMethods attributeBootstrapMethods = new AttributeBootstrapMethods();
        attributeBootstrapMethods.setNumBootstrapMethods(ByteUtil.toDigits(ByteUtil.getBytes(is, 2)));

        ArrayList<BootstrapMethod> bootstrapMethods = new ArrayList<>();
        for (int i = 0; i < attributeBootstrapMethods.getNumBootstrapMethods(); i++) {
            BootstrapMethod bootstrapMethod = new BootstrapMethod();
            bootstrapMethod.setBootstrapMethodRef(ByteUtil.toDigits(ByteUtil.getBytes(is, 2)));
            bootstrapMethod.setNumBootstrapArguments(ByteUtil.toDigits(ByteUtil.getBytes(is, 2)));
            List<Integer> args = new ArrayList<>();
            for (int j = 0; j < bootstrapMethod.getNumBootstrapArguments(); j++) {
                args.add(ByteUtil.toDigits(ByteUtil.getBytes(is, 2)));
            }
            bootstrapMethod.setBootstrapArguments(args);
            bootstrapMethods.add(bootstrapMethod);
        }
        attributeBootstrapMethods.setBootstrapMethods(bootstrapMethods);

        return attributeBootstrapMethods;
    }
}
