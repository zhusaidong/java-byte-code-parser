package cn.zhusaidong.bytecode.parser.structure.data.constant;

import cn.zhusaidong.bytecode.parser.structure.data.ConstantPool;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.function.Function;

/**
 * 常量池-字符串常量
 *
 * @author zhusaidong
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ConstantPoolStringInfo extends ConstantPool {
    private String stringInfo;

    public ConstantPoolStringInfo(Integer data) {
        setType("string_info");
        this.stringInfo = "#" + data;
    }
    @Override
    public void fill(Function<Integer,ConstantPool> fun){
        ConstantPoolUtf8Info constantUtf8Info = (ConstantPoolUtf8Info)fun.apply(getConstantIndex(getStringInfo()));
        setStringInfo(constantUtf8Info.getUtf8Info());
    }
}
