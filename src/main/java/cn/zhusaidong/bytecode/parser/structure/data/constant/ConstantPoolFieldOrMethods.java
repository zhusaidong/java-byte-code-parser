package cn.zhusaidong.bytecode.parser.structure.data.constant;

import cn.zhusaidong.bytecode.parser.interfaces.AccessFlag;
import cn.zhusaidong.bytecode.parser.structure.data.AttributeInfo;
import cn.zhusaidong.bytecode.parser.structure.data.ConstantPool;
import lombok.Data;

import java.util.List;
import java.util.function.Function;

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

    public int getConstantIndex(String index){
        return Integer.parseInt(index.substring(1));
    }

    public void fill(Function<Integer, ConstantPool> fun) {
        ConstantPoolUtf8Info utf8Info = (ConstantPoolUtf8Info) fun.apply(getConstantIndex(getName()));
        setName(utf8Info.getUtf8Info());

        utf8Info = (ConstantPoolUtf8Info) fun.apply(getConstantIndex(getDescriptor()));
        setDescriptor(utf8Info.getUtf8Info());
    }
}
