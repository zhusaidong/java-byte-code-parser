package cn.zhusaidong.bytecode.parser.enums;

import cn.zhusaidong.bytecode.parser.interfaces.AccessFlag;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 内部类访问控制符枚举
 *
 * @author zhusaidong
 * @since 2024/1/22
 */
@Getter
@AllArgsConstructor
public enum InnerClassAccessFlagEnum implements AccessFlag {
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
    /**
     * 标识这是一个接口
     */
    ACC_INTERFACE(0x0020),
    /**
     * 是否为abstract 类型，对于接口或者抽象类来说，此标志值为真，其他类型值为假
     */
    ACC_ABSTRACT(0x0400),
    /**
     * 标识这个类并非由用户代码产生的
     */
    ACC_SYNTHETIC(0x1000),
    /**
     * 标识这是一个注解
     */
    ACC_ANNOTATION(0x2000),
    /**
     * 标识这是一个枚举
     */
    ACC_ENUM(0x4000),
    ;

    private final Integer code;

    public static List<AccessFlag> findListByCode(int code) {
        return Arrays.stream(values())
                .filter(flagEnum -> (flagEnum.code & code) == flagEnum.code)
                .collect(Collectors.toList());
    }
}
