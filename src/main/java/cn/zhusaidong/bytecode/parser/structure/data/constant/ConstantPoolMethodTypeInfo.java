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
public class ConstantPoolMethodTypeInfo extends ConstantPool {
    private String methodTypeInfo;

    public ConstantPoolMethodTypeInfo(Integer data) {
        setType("methodType_info");
        this.methodTypeInfo = "#" + data;
    }

    @Override
    public void fill(Function<Integer, ConstantPool> fun) {
        ConstantPoolUtf8Info constantUtf8Info = (ConstantPoolUtf8Info) fun.apply(getConstantIndex(getMethodTypeInfo()));
        setMethodTypeInfo(constantUtf8Info.getUtf8Info());
    }
}
