package cn.zhusaidong.bytecode.parser.structure.data.attribute;

import cn.zhusaidong.bytecode.parser.structure.data.AttributeInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author zhusaidong
 * @since 2024/1/25
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AttributeCode extends AttributeInfo {
    /**
     * 操作数栈（Operand Stack）深度的最大值
     */
    private Integer maxStack;
    /**
     * 局部变量表所需的存储空间
     */
    private Integer maxLocals;
    /**
     * 字节码长度
     */
    private Integer codeLength;
    /**
     * 存储字节码指令的一系列字节流,需要转换成16进制
     */
    private String[] code;
    private String[] opcode;
    /**
     * 显式异常处理表
     */
    private Integer exceptionTableLength;
    private List<ExceptionTable> exceptionTable;
    private List<AttributeInfo> attributes;

    public AttributeCode() {
        setAttributeType("Code");
    }

    /**
     * 如果存在异常表，那它的格式应如表 6-16 所示，包含四个字段，这些字段的含义
     * 为：如果当字节码从第 start_pc 行[1]到第 end_pc 行之间（不含第 end_pc 行）出现了类型
     * 为 catch_type 或者其子类的异常（），则转到第 handler_pc 行继续处理。当 catch_type 的值为 0 时，代表任意异常情
     * 况都需要转到 handler_pc 处进行处理。
     *
     * @author zhusaidong
     * @since 2024/1/25
     */
    @Data
    public static class ExceptionTable {
        private Integer startPc;
        private Integer endPc;
        /**
         * catch_type 为指向一个 CONSTANT_Class_info 型常量的索引
         */
        private Integer catchType;
        private Integer handlerPc;
    }
}
