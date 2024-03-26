package cn.zhusaidong.bytecode.parser.structure.data;

import lombok.Data;

import java.util.function.Function;

/**
 * 属性基础对象
 *
 * @author zhusaidong
 */
@Data
public class AttributeInfo {
    /**
     * 属性名称索引，从常量池中引用一个 CONSTANT_Utf8_info 类型的常量
     */
    private String attributeName;
    /**
     * 属性长度
     */
    private Integer attributeLength;
    /**
     * 属性
     */
    private byte[] info;
    /**
     * 属性类型
     */
    private String attributeType;

    public int getConstantIndex(String index) {
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
