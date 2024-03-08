package cn.zhusaidong.bytecode.parser.structure.data.constant;

import cn.zhusaidong.bytecode.parser.structure.data.ConstantPool;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author zhusaidong
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ConstantPoolInvokeDynamicInfo extends ConstantPool {
    /**
     * 当前class中引导方法表的bootstrap_methods数组的索引
     */
    private Integer methodAttrIndex;
    /**
     * 方法名和方法描述符
     */
    private Integer nameAndTypeInfoIndex;
    private ConstantPoolNameAndTypeInfo nameAndTypeInfo;

    public ConstantPoolInvokeDynamicInfo(Integer methodAttrIndex, Integer nameAndTypeInfoIndex) {
        setType("invoke_dynamic_info");
        this.methodAttrIndex = methodAttrIndex;
        this.nameAndTypeInfoIndex = nameAndTypeInfoIndex;
    }
}
