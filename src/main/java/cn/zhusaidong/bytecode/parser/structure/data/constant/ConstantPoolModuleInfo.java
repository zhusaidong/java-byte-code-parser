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
public class ConstantPoolModuleInfo extends ConstantPool {
    private String moduleInfo;

    public ConstantPoolModuleInfo(Integer data) {
        setType("module_info");
        this.moduleInfo = "#" + data;
    }

    @Override
    public void fill(Function<Integer, ConstantPool> fun) {
        ConstantPoolUtf8Info constantUtf8Info = (ConstantPoolUtf8Info) fun.apply(getConstantIndex(getModuleInfo()));
        setModuleInfo(constantUtf8Info.getUtf8Info());
    }
}
