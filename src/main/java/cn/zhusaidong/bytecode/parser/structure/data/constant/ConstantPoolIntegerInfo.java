package cn.zhusaidong.bytecode.parser.structure.data.constant;

import cn.zhusaidong.bytecode.parser.structure.data.ConstantPool;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author zhusaidong
 * @since 2024/1/24
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ConstantPoolIntegerInfo extends ConstantPool {
    private final Integer integerInfo;

    public ConstantPoolIntegerInfo(Integer data) {
        setType("integer_info");
        this.integerInfo = data;
    }
}
