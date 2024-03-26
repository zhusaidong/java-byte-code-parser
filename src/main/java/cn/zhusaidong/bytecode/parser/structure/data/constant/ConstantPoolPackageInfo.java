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
public class ConstantPoolPackageInfo extends ConstantPool {
    private String packageInfo;

    public ConstantPoolPackageInfo(Integer data) {
        setType("package_info");
        this.packageInfo = "#" + data;
    }

    @Override
    public void fill(Function<Integer, ConstantPool> fun) {
        ConstantPoolUtf8Info constantUtf8Info = (ConstantPoolUtf8Info) fun.apply(getConstantIndex(getPackageInfo()));
        setPackageInfo(constantUtf8Info.getUtf8Info());
    }
}
