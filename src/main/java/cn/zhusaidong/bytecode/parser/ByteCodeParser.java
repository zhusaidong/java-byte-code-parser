package cn.zhusaidong.bytecode.parser;

import cn.zhusaidong.bytecode.parser.enums.AccessFlagTypeEnum;
import cn.zhusaidong.bytecode.parser.interfaces.Parser;
import cn.zhusaidong.bytecode.parser.structure.data.AttributeInfo;
import cn.zhusaidong.bytecode.parser.structure.data.ClassByteCode;
import cn.zhusaidong.bytecode.parser.structure.data.ConstantPool;
import cn.zhusaidong.bytecode.parser.structure.data.attribute.AttributeCode;
import cn.zhusaidong.bytecode.parser.structure.data.constant.ConstantPoolClassInfo;
import cn.zhusaidong.bytecode.parser.structure.data.constant.ConstantPoolFieldOrMethods;
import cn.zhusaidong.bytecode.parser.structure.parser.*;
import cn.zhusaidong.bytecode.parser.util.ParserCacheUtil;

import java.io.InputStream;
import java.util.List;
import java.util.Objects;

/**
 * 字节码解析器
 *
 * @author zhusaidong
 * @see <a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html">Java Virtual Machine Specification</a>
 * @since 2024/1/19
 */
public class ByteCodeParser implements Parser<ClassByteCode> {
    private static void fillConstantPool(List<ConstantPool> constantPools) {
        constantPools.stream().filter(Objects::nonNull).forEach(constant -> {
            switch (constant.getType()) {
                case "class_info":
                case "string_info":
                case "methodType_info":
                case "module_info":
                case "package_info":
                case "nameAndType_info":
                    constant.fill(constantPools::get);
                    break;
                default:
                    break;
            }
        });
        constantPools.stream().filter(Objects::nonNull).forEach(constant -> {
            switch (constant.getType()) {
                case "methodRef_info":
                case "interfaceMethodRef_info":
                case "invoke_dynamic_info":
                case "fieldRef_info":
                    constant.fill(constantPools::get);
                    break;
                default:
                    break;
            }
        });
    }

    private static void fillClassInfo(ClassByteCode classByteCode) {
        //填充当前类
        int index = ConstantPool.getConstantIndex(classByteCode.getThisClass());
        ConstantPoolClassInfo constantClassInfo = (ConstantPoolClassInfo) classByteCode.getConstantPools().get(index);
        classByteCode.setThisClass(constantClassInfo.getClassInfo());
        //填充父类
        index = ConstantPool.getConstantIndex(classByteCode.getSuperClass());
        constantClassInfo = (ConstantPoolClassInfo) classByteCode.getConstantPools().get(index);
        classByteCode.setSuperClass(constantClassInfo.getClassInfo());
    }

    private static void fillFieldsOrMethods(List<ConstantPoolFieldOrMethods> fields, List<ConstantPool> constantPools) {
        fields.forEach(field -> {
            field.fill(constantPools::get);
            fillAttributes(field.getAttributes(), constantPools);
        });
    }

    private static void fillAttributes(List<AttributeInfo> attributes, List<ConstantPool> constantPools) {
        attributes.stream()
                .filter(Objects::nonNull)
                .filter(attributeInfo -> attributeInfo.getAttributeType() != null)
                .forEach(attributeInfo -> {
                    switch (attributeInfo.getAttributeType()) {
                        case "ConstantValue":
                        case "InnerClasses":
                        case "LocalVariableTable":
                        case "LocalVariableTypeTable":
                        case "MethodParameters":
                        case "Signature":
                        case "SourceFile":
                            attributeInfo.fill(constantPools::get);
                            break;
                        case "Code":
                            fillAttributes(((AttributeCode) attributeInfo).getAttributes(), constantPools);
                            break;
                        default:
                            break;
                    }
                });
    }

    private static <T extends Parser<?>> T getParser(Class<T> tClass) {
        return ParserCacheUtil.getParser(tClass);
    }

    /**
     * 解析class文件的字节对象
     */
    @Override
    public ClassByteCode parser(InputStream is, Object... objs) {
        ClassByteCode classByteCode = new ClassByteCode();

        //魔数
        classByteCode.setMagic(getParser(MagicParser.class).parser(is));
        //版本
        classByteCode.setJdkVersion(getParser(VersionParser.class).parser(is));
        //常量池
        classByteCode.setConstantPools(getParser(ConstantPoolParser.class).parser(is));
        //访问控制符
        classByteCode.setAccessFlags(getParser(AccessFlagParser.class).parser(is, AccessFlagTypeEnum.CLASS));
        //当前类
        classByteCode.setThisClass("#" + getParser(ThisClassParser.class).parser(is));
        //父类
        classByteCode.setSuperClass("#" + getParser(SuperClassParser.class).parser(is));
        //接口表索引
        classByteCode.setInterfaces(getParser(InterfaceParser.class).parser(is));
        //字段表
        classByteCode.setFields(getParser(FieldsParser.class).parser(is, classByteCode.getConstantPools()));
        //方法表
        classByteCode.setMethods(getParser(MethodsParser.class).parser(is, classByteCode.getConstantPools()));
        //属性表
        classByteCode.setAttributes(getParser(AttributesParser.class).parser(is, classByteCode.getConstantPools()));

        //将`索引值`填充为`真实值`
        boolean isFill = objs.length > 0 && (boolean) objs[0];
        if (isFill) {
            //填充常量池
            fillConstantPool(classByteCode.getConstantPools());
            //填充类
            fillClassInfo(classByteCode);
            //填充字段表
            fillFieldsOrMethods(classByteCode.getFields(), classByteCode.getConstantPools());
            //填充方法表
            fillFieldsOrMethods(classByteCode.getMethods(), classByteCode.getConstantPools());
            //填充属性表
            fillAttributes(classByteCode.getAttributes(), classByteCode.getConstantPools());
        }

        return classByteCode;
    }
}
