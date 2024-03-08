package cn.zhusaidong.bytecode.parser.enums;

import cn.zhusaidong.bytecode.parser.interfaces.AccessFlag;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 类访问控制符枚举
 *
 * @author zhusaidong
 * @since 2024/1/22
 */
@Getter
@AllArgsConstructor
public enum ClassAccessFlagEnum implements AccessFlag {
    /**
     * 是否为 public 类型
     */
    ACC_PUBLIC(0x0001),
    /**
     * 是否被声明为 final，只有类可设置
     */
    ACC_FINAL(0x0010),
    /**
     * 是否允许使用 invokespecial 字节码指令的新语义，invokespecial 指令的语义在JDK 1.0.2发生过改变，
     * 为了区别这条指令使用哪种语义，JDK1.0.2之后编译出来的类的这个标志都必须为真
     */
    ACC_SUPER(0x0020),
    /**
     * 标识这是一个接口
     */
    ACC_INTERFACE(0x0200),
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
    /**
     * 标识这是一个模块
     */
    ACC_MODULE(0x8000),
    ;

    private final Integer code;

    public static List<AccessFlag> findListByCode(int code) {
        return Arrays.stream(values())
                .filter(flagEnum -> (flagEnum.code & code) == flagEnum.code)
                .collect(Collectors.toList());
    }
}
