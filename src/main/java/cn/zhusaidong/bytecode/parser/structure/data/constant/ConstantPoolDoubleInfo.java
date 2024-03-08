package cn.zhusaidong.bytecode.parser.structure.data.constant;

import cn.zhusaidong.bytecode.parser.structure.data.ConstantPool;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ConstantPoolDoubleInfo extends ConstantPool {
    private final double floatInfo;

    public ConstantPoolDoubleInfo(double data) {
        setType("double_info");
        this.floatInfo = data;
    }
}
