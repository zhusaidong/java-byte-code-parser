package cn.zhusaidong.bytecode.parser.structure.data.attribute;

import cn.zhusaidong.bytecode.parser.structure.data.AttributeInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author zhusaidong
 * @since 2024/1/26
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AttributeBootstrapMethods extends AttributeInfo {
    /**
     * 引导方法限定符的数量
     */
    private Integer numBootstrapMethods;
    /**
     * 包含了一个指向常量池 CONSTANT_MethodHandle 结构的索引值
     */
    private List<BootstrapMethod> bootstrapMethods;

    public AttributeBootstrapMethods() {
        setAttributeType("BootstrapMethods");
    }

    @Data
    public static class BootstrapMethod {
        /**
         * 必须是一个对常量池的有效索引。常量池在该索引处的值必须是一个 CONSTANT_MethodHandle_info 结构。
         */
        private Integer bootstrapMethodRef;
        /**
         * 给出了 bootstrap_arguments[]数组成员的数量
         */
        private Integer numBootstrapArguments;
        private List<Integer> bootstrapArguments;
    }
}
