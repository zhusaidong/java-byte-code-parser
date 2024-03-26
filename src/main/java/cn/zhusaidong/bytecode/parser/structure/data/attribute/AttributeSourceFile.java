package cn.zhusaidong.bytecode.parser.structure.data.attribute;

import cn.zhusaidong.bytecode.parser.structure.data.AttributeInfo;
import cn.zhusaidong.bytecode.parser.structure.data.ConstantPool;
import cn.zhusaidong.bytecode.parser.structure.data.constant.ConstantPoolUtf8Info;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.function.Function;

/**
 * @author zhusaidong
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AttributeSourceFile extends AttributeInfo {
    /**
     * 数据项是指向常量池中 CONSTANT_Utf8_info 型常量的索引，常量值是源码文件的文件名。
     */
    private String sourceFile;

    public AttributeSourceFile() {
        setAttributeType("SourceFile");
    }

    @Override
    public void fill(Function<Integer, ConstantPool> fun) {
        ConstantPoolUtf8Info utf8Info1 = (ConstantPoolUtf8Info) fun.apply(getConstantIndex(getSourceFile()));
        setSourceFile(utf8Info1.getUtf8Info());
    }
}
