package cn.zhusaidong.bytecode.parser.structure.data.attribute;

import cn.zhusaidong.bytecode.parser.interfaces.AccessFlag;
import cn.zhusaidong.bytecode.parser.structure.data.AttributeInfo;
import cn.zhusaidong.bytecode.parser.structure.data.ConstantPool;
import cn.zhusaidong.bytecode.parser.structure.data.constant.ConstantPoolUtf8Info;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.function.Function;

/**
 * @author zhusaidong
 * @since 2024/1/26
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AttributeMethodParameters extends AttributeInfo {
    private Integer parametersCount;
    private List<Parameter> parameters;

    public AttributeMethodParameters() {
        setAttributeType("MethodParameters");
    }

    @Override
    public void fill(Function<Integer, ConstantPool> fun) {
        getParameters().forEach(parameter -> {
            ConstantPoolUtf8Info utf8Info = (ConstantPoolUtf8Info) fun.apply(getConstantIndex(parameter.getName()));
            parameter.setName(utf8Info.getUtf8Info());
        });
    }

    @Data
    public static class Parameter {
        /**
         * 指向常量池 CONSTANT_Utf8_info 常量的索引值，代表了该参数的名称。
         */
        private String name;
        /**
         * 参数的状态指示器，它可以包含以下三种状态中的一种或多种：
         * ·0x0010（ACC_FINAL）：表示该参数被 final 修饰。
         * ·0x1000（ACC_SYNTHETIC）：表示该参数并未出现在源文件中，是编译器自动生成的。
         * ·0x8000（ACC_MANDATED）：表示该参数是在源文件中隐式定义的。Java 语言中的典型场景是 this 关键字。
         */
        private List<AccessFlag> accessFlags;
    }
}
