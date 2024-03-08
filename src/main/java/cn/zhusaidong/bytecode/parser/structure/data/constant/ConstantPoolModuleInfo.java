package cn.zhusaidong.bytecode.parser.structure.data.constant;

import cn.zhusaidong.bytecode.parser.structure.data.ConstantPool;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
}
