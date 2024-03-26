package cn.zhusaidong.bytecode.parser.structure.data.constant;

import cn.zhusaidong.bytecode.parser.structure.data.ConstantPool;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author zhusaidong
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ConstantPoolLongInfo extends ConstantPool {
    private final Long longInfo;

    public ConstantPoolLongInfo(Long data) {
        setType("long_info");
        this.longInfo = data;
    }
}
