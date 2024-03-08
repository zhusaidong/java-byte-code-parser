package cn.zhusaidong.bytecode.parser.structure.data.constant;

import cn.zhusaidong.bytecode.parser.structure.data.ConstantPool;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author zhusaidong
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ConstantPoolInterfaceMethodRefInfo extends ConstantPool {
    private String classInfo;
    private Integer nameAndTypeInfoIndex;
    private ConstantPoolNameAndTypeInfo nameAndTypeInfo;

    public ConstantPoolInterfaceMethodRefInfo(Integer classInfoIndex, Integer nameAndTypeInfoIndex) {
        setType("interfaceMethodRef_info");
        this.classInfo = "#" + classInfoIndex;
        this.nameAndTypeInfoIndex = nameAndTypeInfoIndex;
    }
}
