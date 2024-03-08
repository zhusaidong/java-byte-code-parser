package cn.zhusaidong.bytecode.parser.structure.data;

import cn.zhusaidong.bytecode.parser.enums.JdkVersionEnum;
import cn.zhusaidong.bytecode.parser.interfaces.AccessFlag;
import cn.zhusaidong.bytecode.parser.structure.data.constant.ConstantPoolFieldOrMethods;
import lombok.Data;

import java.util.List;

/**
 * 字节码对象
 *
 * @author zhusaidong
 * @since 2024/1/19
 */
@Data
public class ClassByteCode {
    /**
     * 魔数(4字节)
     */
    private String magic;
    /**
     * 主版本(2字节).次版本(2字节)
     */
    private JdkVersionEnum jdkVersion;
    /**
     * 常量池表
     */
    private List<ConstantPool> constantPools;
    /**
     * 访问控制符，2字节（16进制）
     */
    private List<AccessFlag> accessFlags;
    /**
     * 当前类索引，2字节，指向CONSTANT_Class_info
     */
    private String thisClass;
    /**
     * 父类索引，2字节，指向CONSTANT_Class_info
     */
    private String superClass;
    /**
     * 接口索引表
     */
    private List<Integer> interfaces;
    /**
     * 字段表
     */
    private List<ConstantPoolFieldOrMethods> fields;
    /**
     * 方法表
     */
    private List<ConstantPoolFieldOrMethods> methods;
    /**
     * 属性表
     */
    private List<AttributeInfo> attributes;
}
