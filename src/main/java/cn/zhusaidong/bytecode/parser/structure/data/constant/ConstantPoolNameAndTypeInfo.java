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
public class ConstantPoolNameAndTypeInfo extends ConstantPool {
    /**
     * 方法名称常量索引
     */
    private String nameInfoIndex;
    /**
     * 描述符常量索引
     */
    private String typeInfoIndex;

    public ConstantPoolNameAndTypeInfo(Integer nameInfoIndex, Integer typeInfoIndex) {
        setType("nameAndType_info");
        this.nameInfoIndex = "#" + nameInfoIndex;
        this.typeInfoIndex = "#" + typeInfoIndex;
    }

    @Override
    public void fill(Function<Integer, ConstantPool> fun) {
        ConstantPoolUtf8Info constantUtf8Info = (ConstantPoolUtf8Info) fun.apply(getConstantIndex(getNameInfoIndex()));
        setNameInfoIndex(constantUtf8Info.getUtf8Info());
        constantUtf8Info = (ConstantPoolUtf8Info) fun.apply(getConstantIndex(getTypeInfoIndex()));
        setTypeInfoIndex(constantUtf8Info.getUtf8Info());
    }
}
