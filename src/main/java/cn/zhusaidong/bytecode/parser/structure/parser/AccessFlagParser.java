package cn.zhusaidong.bytecode.parser.structure.parser;

import cn.zhusaidong.bytecode.parser.enums.*;
import cn.zhusaidong.bytecode.parser.interfaces.AccessFlag;
import cn.zhusaidong.bytecode.parser.interfaces.Parser;
import cn.zhusaidong.bytecode.parser.util.ByteUtil;

import java.io.InputStream;
import java.util.List;

/**
 * 访问控制符解析器
 *
 * @author zhusaidong
 * @since 2024/1/24
 */
public class AccessFlagParser implements Parser<List<AccessFlag>> {
    @Override
    public List<AccessFlag> parser(InputStream is, Object... objs) {
        //访问控制符，2字节
        int code = ByteUtil.toDigits(ByteUtil.getBytes(is, 2));

        AccessFlagTypeEnum accessFlagTypeEnum = (AccessFlagTypeEnum) objs[0];
        switch (accessFlagTypeEnum) {
            case FIELD:
                return FieldAccessFlagEnum.findListByCode(code);
            case METHOD:
                return MethodAccessFlagEnum.findListByCode(code);
            case INNER_CLASS:
                return InnerClassAccessFlagEnum.findListByCode(code);
            case CLASS:
            default:
                return ClassAccessFlagEnum.findListByCode(code);
        }
    }
}
