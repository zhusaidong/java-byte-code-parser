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
public class AttributeLocalVariableTypeTable extends AttributeInfo {
    private Integer localVariableTableLength;
    private List<LocalVariableTypeInfo> localVariableTypes;

    public AttributeLocalVariableTypeTable() {
        setAttributeType("LocalVariableTypeTable");
    }

    @Data
    public static class LocalVariableTypeInfo {
        /**
         * 这个局部变量的生命周期开始的字节码偏移量
         */
        private Integer startPc;
        /**
         * 其作用范围覆盖的长度
         */
        private Integer length;
        /**
         * 指向常量池中 CONSTANT_Utf8_info 型常量的索引,代表了这个局部变量的名称
         */
        private String name;
        /**
         * 指向常量池中 CONSTANT_Utf8_info 型常量的索引,代表了字段的特征签名（Signature）。
         */
        private String descriptor;
        /**
         * 这个局部变量在栈帧的局部变量表中变量槽的位置。当这个变量数据类型是 64 位类型时（double 和 long），它占用的变量槽为 index 和 index+1 两个。
         */
        private Integer index;
    }
}
