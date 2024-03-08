package cn.zhusaidong.bytecode.parser.structure.data.constant;

import cn.zhusaidong.bytecode.parser.structure.data.ConstantPool;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author zhusaidong
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ConstantPoolPackageInfo extends ConstantPool {
    private String packageInfo;

    public ConstantPoolPackageInfo(Integer data) {
        setType("package_info");
        this.packageInfo = "#" + data;
    }
}
