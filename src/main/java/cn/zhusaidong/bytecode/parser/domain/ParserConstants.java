package cn.zhusaidong.bytecode.parser.domain;

import cn.zhusaidong.bytecode.parser.interfaces.AttributeParser;
import cn.zhusaidong.bytecode.parser.interfaces.ConstantParser;
import cn.zhusaidong.bytecode.parser.structure.data.AttributeInfo;
import cn.zhusaidong.bytecode.parser.structure.data.ConstantPool;
import cn.zhusaidong.bytecode.parser.structure.parser.attribute.*;
import cn.zhusaidong.bytecode.parser.structure.parser.constant.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 解析器常量组
 *
 * @author zhusaidong
 * @since 2024/1/30
 */
public class ParserConstants {
    /**
     * 常量池解析器map&lt;tag, parser>
     */
    public static final Map<Integer, Class<? extends ConstantParser<ConstantPool>>> CONSTANT_POOL_PARSER_MAP = new HashMap<Integer, Class<? extends ConstantParser<ConstantPool>>>() {{
        //CONSTANT_Utf8_info结构用于表示常量字符串值
        put(1, ConstantPoolUtf8Parser.class);
        //CONSTANT_Integer_info 结构表示 4 字节数字（int）常量
        put(3, ConstantPoolIntegerParser.class);
        //CONSTANT_Float_info 结构表示 4 字节数字（float）常量
        put(4, ConstantPoolFloatParser.class);
        //CONSTANT_Long_info 结构表示 8 字节数字（long）常量
        put(5, ConstantPoolLongParser.class);
        //CONSTANT_Double_info 结构表示 8 字节数字（double）常量
        put(6, ConstantPoolDoubleParser.class);
        //CONSTANT_Class_info结构用于表示类或接口
        put(7, ConstantPoolClassParser.class);
        //CONSTANT_String_info结构用于表示 String 类型的常量对象
        put(8, ConstantPoolStringParser.class);
        //CONSTANT_Fieldref_info结构用于表示字段引用
        put(9, ConstantPoolFieldRefParser.class);
        //CONSTANT_Methodref_info结构用于表示方法引用
        put(10, ConstantPoolMethodRefParser.class);
        //CONSTANT_InterfaceMethodref_info结构用于表示接口方法引用
        put(11, ConstantPoolInterfaceMethodRefParser.class);
        //CONSTANT_NameAndType_info结构用于表示字段或方法，而不指示它属于哪个类或接口类型
        put(12, ConstantPoolNameAndTypeParser.class);
        //CONSTANT_MethodHandle_info结构用于表示方法句柄
        put(15, ConstantPoolMethodHandleParser.class);
        //CONSTANT_MethodType_info结构用于表示方法类型
        put(16, ConstantPoolMethodTypeParser.class);
        //CONSTANT_Dynamic_info结构用于表示动态计算的常数，这是一个在ldc指令过程中通过调用引导方法产生的任意值。由结构指定的辅助类型约束动态计算的常量的类型。
        put(17, ConstantPoolDynamicParser.class);
        //CONSTANT_InvokeDynamic_info结构用于表示动态计算的调用站点，java.lang.invoke的实例。在invokedynamic指令过程中调用引导方法生成的CallSite。由结构指定的辅助类型约束动态计算的调用站点的方法类型。
        put(18, ConstantPoolInvokeDynamicParser.class);
        //CONSTANT_Package_info结构用于表示模块导出或打开的包
        put(19, ConstantPoolPackageParser.class);
        //CONSTANT_Module_info结构用于表示模块
        put(20, ConstantPoolModuleParser.class);
    }};

    /**
     * 属性解析器map&lt;属性关键词, parser>
     */
    public static final Map<String, Class<? extends AttributeParser<AttributeInfo>>> ATTRIBUTE_PARSER_MAP = new HashMap<String, Class<? extends AttributeParser<AttributeInfo>>>() {{
        //java代码编译成的字节码指令
        put("Code", AttributeCodeParser.class);
        //被声明为deprecated的方法和字段
        put("Deprecated", AttributeDeprecatedParser.class);
        //方法抛出的异常列表
        put("Exceptions", AttributeExceptionsParser.class);
        //java源码的行号与字节码指令的对应关系
        put("LineNumberTable", AttributeLineNumberTableParser.class);
        //方法的局部变量描述
        put("LocalVariableTable", AttributeLocalVariableTableParser.class);
        //用于支持泛型的情况下方法签名
        put("Signature", AttributeSignatureParser.class);
        //标识方法/字段为编译器自动生成的
        put("Synthetic", AttributeSyntheticParser.class);
        //使用特征签名代替描述符
        put("LocalVariableTypeTable", AttributeLocalVariableTypeTableParser.class);
        //记录源文件名称
        put("SourceFile", AttributeSourceFileParser.class);
        //内部类列表
        put("InnerClasses", AttributeInnerClassesParser.class);
        //由final关键字定义的常量值
        put("ConstantValue", AttributeConstantValueParser.class);
        put("BootstrapMethods", AttributeBootstrapMethodsParser.class);
        //记录方法的各个形参名称和信息
        put("MethodParameters", AttributeMethodParametersParser.class);

        //todo 以下未做解析

        //仅当这个类是局部类/匿名类是才拥有此属性，用于标识这个类所在的外围方法
        put("EnclosingMethod", null);
        put("StackMapTable", null);
        put("SourceDebugExtension", null);
        put("RuntimeVisibleAnnotations", null);
        put("RuntimeInvisibleAnnotations", null);
        put("RuntimeVisibleParameterAnnotations", null);
        put("RuntimeInvisibleParameterAnnotations", null);
        put("RuntimeVisibleTypeAnnotations", null);
        put("RuntimeInvisibleTypeAnnotations", null);
        put("AnnotationDefault", null);
    }};
}
