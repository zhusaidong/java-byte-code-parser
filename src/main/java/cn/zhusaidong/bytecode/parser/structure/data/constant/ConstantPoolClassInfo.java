package cn.zhusaidong.bytecode.parser.structure.data.constant;

import cn.zhusaidong.bytecode.parser.structure.data.ConstantPool;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 常量池-class_info类型
 *
 * @author zhusaidong
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ConstantPoolClassInfo extends ConstantPool {
    private String classInfo;

    public ConstantPoolClassInfo(Integer data) {
        setType("class_info");
        this.classInfo = "#" + data;
    }
}
