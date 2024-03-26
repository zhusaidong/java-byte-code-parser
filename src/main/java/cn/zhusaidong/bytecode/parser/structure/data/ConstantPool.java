package cn.zhusaidong.bytecode.parser.structure.data;

import lombok.Data;

import java.util.function.Function;

/**
 * 常量池基础对象
 *
 * @author zhusaidong
 * @since 2024/1/24
 */
@Data
public class ConstantPool {
    private String type;

    public static int getConstantIndex(String index) {
        return Integer.parseInt(index.substring(1));
    }

    /**
     * 填充模式下填充数据
     *
     * @param fun 常量池数据回调
     */
    public void fill(Function<Integer, ConstantPool> fun) {

    }
}
