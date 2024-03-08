package cn.zhusaidong.bytecode.parser.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 访问控制符类型枚举
 *
 * @author zhusaidong
 * @since 2024/1/22
 */
@Getter
@AllArgsConstructor
public enum AccessFlagTypeEnum {
    /**
     * 类
     */
    CLASS,
    /**
     * 内部类
     */
    INNER_CLASS,
    /**
     * 字段
     */
    FIELD,
    /**
     * 方法
     */
    METHOD
}
