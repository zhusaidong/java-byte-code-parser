package cn.zhusaidong.bytecode.parser.structure.data.constant;

import cn.zhusaidong.bytecode.parser.structure.data.ConstantPool;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ConstantPoolMethodTypeInfo extends ConstantPool {
    private String methodTypeInfo;

    public ConstantPoolMethodTypeInfo(Integer data) {
        setType("methodType_info");
        this.methodTypeInfo = "#" + data;
    }
}
