package cn.zhusaidong.bytecode.parser.enums;

import cn.zhusaidong.bytecode.parser.interfaces.AccessFlag;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 字段访问控制符枚举
 *
 * @author zhusaidong
 * @since 2024/1/22
 */
@Getter
@AllArgsConstructor
public enum FieldAccessFlagEnum implements AccessFlag {
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
    ACC_VOLATILE(0x0040),
    ACC_TRANSIENT(0x0080),
    /**
     * 标识这个类并非由用户代码产生的
     */
    ACC_SYNTHETIC(0x1000),
    /**
     * 标识这是一个枚举
     */
    ACC_ENUM(0x4000),
    /**
     * 该参数是在源文件中隐式定义的。Java 语言中的典型场景是 this 关键字。
     */
    ACC_MANDATED(0x8000),
    ;

    private final Integer code;

    public static List<AccessFlag> findListByCode(int code) {
        return Arrays.stream(values())
                .filter(flagEnum -> (flagEnum.code & code) == flagEnum.code)
                .collect(Collectors.toList());
    }
}
