package cn.zhusaidong.bytecode.parser.structure.data.constant;

import cn.zhusaidong.bytecode.parser.structure.data.ConstantPool;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 常量池utf8_info类型
 *
 * @author zhusaidong
 * @since 2024/1/24
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ConstantPoolUtf8Info extends ConstantPool {
    private String utf8Info;

    public ConstantPoolUtf8Info(String data) {
        setType("utf8_info");
        this.utf8Info = data;
    }
}
