package cn.zhusaidong.bytecode.parser.structure.data.attribute;

import cn.zhusaidong.bytecode.parser.structure.data.AttributeInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
}
