package cn.zhusaidong.bytecode.parser.structure.data.constant;

import cn.zhusaidong.bytecode.parser.structure.data.ConstantPool;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author zhusaidong
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ConstantPoolMethodHandleInfo extends ConstantPool {
    /**
     * 值为1-9，方法句柄类型
     */
    private Integer refKind;
    /**
     * 常量池引用
     */
    private Integer refIndex;

    public ConstantPoolMethodHandleInfo(Integer refKind, Integer refIndex) {
        setType("methodHandle_info");
        this.refKind = refKind;
        this.refIndex = refIndex;
    }
}
