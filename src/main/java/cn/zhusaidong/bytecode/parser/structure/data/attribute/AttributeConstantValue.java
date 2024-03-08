package cn.zhusaidong.bytecode.parser.structure.data.attribute;

import cn.zhusaidong.bytecode.parser.structure.data.AttributeInfo;
import cn.zhusaidong.bytecode.parser.structure.data.ConstantPool;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author zhusaidong
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AttributeConstantValue extends AttributeInfo {
    /**
     * 代表了常量池中一个字面量常量的引用，根据字段类型的不同，字面量可以是 CONSTANT_Long_info、CONSTANT_Float_info、CONSTANT_Double_info、CONSTANT_Integer_info 和CONSTANT_String_info 常量中的一种。
     */
    private Integer constantValueIndex;
    private ConstantPool constantValue;

    public AttributeConstantValue() {
        setAttributeType("ConstantValue");
    }
}
