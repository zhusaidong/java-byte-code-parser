package cn.zhusaidong.bytecode.parser.structure.data.constant;

import cn.zhusaidong.bytecode.parser.structure.data.ConstantPool;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.function.Function;

/**
 * @author zhusaidong
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ConstantPoolFieldRefInfo extends ConstantPool {
    private String classInfo;
    private Integer nameAndTypeInfoIndex;
    private ConstantPoolNameAndTypeInfo nameAndTypeInfo;

    public ConstantPoolFieldRefInfo(Integer classInfoIndex, Integer nameAndTypeInfoIndex) {
        setType("fieldRef_info");
        this.classInfo = "#" + classInfoIndex;
        this.nameAndTypeInfoIndex = nameAndTypeInfoIndex;
    }

    @Override
    public void fill(Function<Integer, ConstantPool> fun) {
        ConstantPoolClassInfo constantClassInfo = (ConstantPoolClassInfo) fun.apply(getConstantIndex(getClassInfo()));
        setClassInfo(constantClassInfo.getClassInfo());

        ConstantPoolNameAndTypeInfo constantPoolNameAndTypeInfo = (ConstantPoolNameAndTypeInfo) fun.apply(getNameAndTypeInfoIndex());
        setNameAndTypeInfo(constantPoolNameAndTypeInfo);
        setNameAndTypeInfoIndex(null);
    }
}
