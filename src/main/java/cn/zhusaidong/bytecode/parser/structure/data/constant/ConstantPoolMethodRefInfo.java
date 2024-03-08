package cn.zhusaidong.bytecode.parser.structure.data.constant;

import cn.zhusaidong.bytecode.parser.structure.data.ConstantPool;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author zhusaidong
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ConstantPoolMethodRefInfo extends ConstantPool {
    private String classInfo;
    private Integer nameAndTypeInfoIndex;
    private ConstantPoolNameAndTypeInfo nameAndTypeInfo;

    public ConstantPoolMethodRefInfo(Integer classInfoIndex, Integer nameAndTypeInfoIndex) {
        setType("methodRef_info");
        this.classInfo = "#" + classInfoIndex;
        this.nameAndTypeInfoIndex = nameAndTypeInfoIndex;
    }
}
