package cn.zhusaidong.bytecode.parser.structure.parser.attribute;

import cn.zhusaidong.bytecode.parser.enums.AccessFlagTypeEnum;
import cn.zhusaidong.bytecode.parser.interfaces.AttributeParser;
import cn.zhusaidong.bytecode.parser.structure.data.AttributeInfo;
import cn.zhusaidong.bytecode.parser.structure.data.attribute.AttributeInnerClasses;
import cn.zhusaidong.bytecode.parser.structure.parser.AccessFlagParser;
import cn.zhusaidong.bytecode.parser.util.ByteUtil;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * @author zhusaidong
 */
public class AttributeInnerClassesParser implements AttributeParser<AttributeInfo> {
    @Override
    public AttributeInfo parser(InputStream is, Object... objs) {
        AttributeInnerClasses attributeInnerClasses = new AttributeInnerClasses();
        attributeInnerClasses.setNumberOfClasses(ByteUtil.toDigits(ByteUtil.getBytes(is, 2)));
        ArrayList<AttributeInnerClasses.InnerClassesInfo> innerClassesInfos = new ArrayList<>();
        for (int i = 0; i < attributeInnerClasses.getNumberOfClasses(); i++) {
            AttributeInnerClasses.InnerClassesInfo innerClassesInfo = new AttributeInnerClasses.InnerClassesInfo();

            innerClassesInfo.setInnerClassInfo("#" + ByteUtil.toDigits(ByteUtil.getBytes(is, 2)));
            innerClassesInfo.setOuterClassInfo("#" + ByteUtil.toDigits(ByteUtil.getBytes(is, 2)));
            innerClassesInfo.setInnerName("#" + ByteUtil.toDigits(ByteUtil.getBytes(is, 2)));
            innerClassesInfo.setInnerClassAccessFlags(new AccessFlagParser().parser(is, AccessFlagTypeEnum.INNER_CLASS));

            innerClassesInfos.add(innerClassesInfo);
        }
        attributeInnerClasses.setInnerClassesInfos(innerClassesInfos);

        return attributeInnerClasses;
    }
}
