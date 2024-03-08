package cn.zhusaidong.bytecode.parser.enums;

import cn.zhusaidong.bytecode.parser.interfaces.AccessFlag;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 方法访问控制符枚举
 *
 * @author zhusaidong
 * @since 2024/1/22
 */
@Getter
@AllArgsConstructor
public enum MethodAccessFlagEnum implements AccessFlag {
    /**
     * 是否为 public 类型
     */
    ACC_PUBLIC(0x0001),
    ACC_PRIVATE(0x0002),
    ACC_PROTECTED(0x0004),
    ACC_STATIC(0x0008),
    /**
     * 是否被声明为 final，只有类可设置
     */
    ACC_FINAL(0x0010),
    ACC_SYNCHRONIZED(0x0020),
    ACC_BRIDGE(0x0040),
    ACC_VARARGS(0x0080),
    ACC_NATIVE(0x0100),
    ACC_ABSTRACT(0x0400),
    ACC_STRICT(0x0800),
    ;


    private final Integer code;

    public static List<AccessFlag> findListByCode(int code) {
        return Arrays.stream(values())
                .filter(flagEnum -> (flagEnum.code & code) == flagEnum.code)
                .collect(Collectors.toList());
    }
}
