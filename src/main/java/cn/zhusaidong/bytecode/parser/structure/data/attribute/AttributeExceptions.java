package cn.zhusaidong.bytecode.parser.structure.data.attribute;

import cn.zhusaidong.bytecode.parser.structure.data.AttributeInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * Exceptions 属性的作用是列举出方法中可能抛出的受查异常（Checked Excepitons），也就是方法描述时在 throws 关键字后面列举的异常
 *
 * @author zhusaidong
 * @since 2024/1/25
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AttributeExceptions extends AttributeInfo {
    /**
     * 方法可能抛出 number_of_exceptions 种受查异常
     */
    private Integer numberOfExceptions;
    /**
     * 一个指向常量池中 CONSTANT_Class_info 型常量的索引，代表了该受查异常的类型。
     */
    private List<Integer> exceptionIndexTables;

    public AttributeExceptions() {
        setAttributeType("Exceptions");
    }
}
