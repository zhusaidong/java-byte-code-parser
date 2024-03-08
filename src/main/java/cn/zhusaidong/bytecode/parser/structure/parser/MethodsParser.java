package cn.zhusaidong.bytecode.parser.structure.parser;

import cn.zhusaidong.bytecode.parser.enums.AccessFlagTypeEnum;
import cn.zhusaidong.bytecode.parser.interfaces.Parser;
import cn.zhusaidong.bytecode.parser.structure.data.constant.ConstantPoolFieldOrMethods;
import cn.zhusaidong.bytecode.parser.util.ByteUtil;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 方法解析器
 *
 * @author zhusaidong
 * @since 2024/1/24
 */
public class MethodsParser implements Parser<List<ConstantPoolFieldOrMethods>> {
    @Override
    public List<ConstantPoolFieldOrMethods> parser(InputStream is, Object... objs) {
        //方法数，2字节
        Integer methodsCount = ByteUtil.toDigits(ByteUtil.getBytes(is, 2));
        //方法表
        List<ConstantPoolFieldOrMethods> methodList = new ArrayList<>(methodsCount);
        for (int i = 0; i < methodsCount; i++) {
            ConstantPoolFieldOrMethods methods = new ConstantPoolFieldOrMethods();

            methods.setAccessFlags(new AccessFlagParser().parser(is, AccessFlagTypeEnum.METHOD));
            methods.setName("#" + ByteUtil.toDigits(ByteUtil.getBytes(is, 2)));
            methods.setDescriptor("#" + ByteUtil.toDigits(ByteUtil.getBytes(is, 2)));
            methods.setAttributes(new AttributesParser().parser(is, objs[0]));

            methodList.add(methods);
        }
        return methodList;
    }
}
