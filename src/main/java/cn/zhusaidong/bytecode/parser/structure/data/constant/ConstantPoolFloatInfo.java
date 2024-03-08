package cn.zhusaidong.bytecode.parser.structure.data.constant;

import cn.zhusaidong.bytecode.parser.structure.data.ConstantPool;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ConstantPoolFloatInfo extends ConstantPool {
    private final Float floatInfo;

    public ConstantPoolFloatInfo(Float data) {
        setType("float_info");
        this.floatInfo = data;
    }
}
