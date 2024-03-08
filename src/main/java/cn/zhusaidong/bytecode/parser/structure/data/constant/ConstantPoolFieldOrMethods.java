package cn.zhusaidong.bytecode.parser.structure.data.constant;

import cn.zhusaidong.bytecode.parser.interfaces.AccessFlag;
import cn.zhusaidong.bytecode.parser.structure.data.AttributeInfo;
import lombok.Data;

import java.util.List;

/**
 * 常量池字段/方法
 *
 * @author zhusaidong
 */
@Data
public class ConstantPoolFieldOrMethods {
    /**
     * 字段/方法修饰符
     */
    private List<AccessFlag> accessFlags;
    /**
     * 字段/方法简单名称索引
     */
    private String name;
    /**
     * 字段和方法的描述符：描述符的作用是用来描述字段的数据类型、方法的参数列表（包括数量、类型以及顺序）和返回值
     */
    private String descriptor;
    /**
     * 额外属性
     */
    private List<AttributeInfo> attributes;
}
