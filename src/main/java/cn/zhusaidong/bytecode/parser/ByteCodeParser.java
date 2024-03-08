package cn.zhusaidong.bytecode.parser;

import cn.zhusaidong.bytecode.parser.domain.ParserCache;
import cn.zhusaidong.bytecode.parser.enums.AccessFlagTypeEnum;
import cn.zhusaidong.bytecode.parser.interfaces.Parser;
import cn.zhusaidong.bytecode.parser.structure.data.AttributeInfo;
import cn.zhusaidong.bytecode.parser.structure.data.ClassByteCode;
import cn.zhusaidong.bytecode.parser.structure.data.ConstantPool;
import cn.zhusaidong.bytecode.parser.structure.data.attribute.*;
import cn.zhusaidong.bytecode.parser.structure.data.constant.*;
import cn.zhusaidong.bytecode.parser.structure.parser.*;

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
            ConstantPoolUtf8Info constantUtf8Info;
            switch (constant.getType()) {
                case "class_info":
                    ConstantPoolClassInfo classInfo = (ConstantPoolClassInfo) constant;
                    constantUtf8Info = (ConstantPoolUtf8Info) constantPools.get(Integer.parseInt(classInfo.getClassInfo().substring(1)));
                    classInfo.setClassInfo(constantUtf8Info.getUtf8Info());
                    break;
                case "string_info":
                    ConstantPoolStringInfo stringInfo = (ConstantPoolStringInfo) constant;
                    constantUtf8Info = (ConstantPoolUtf8Info) constantPools.get(Integer.parseInt(stringInfo.getStringInfo().substring(1)));
                    stringInfo.setStringInfo(constantUtf8Info.getUtf8Info());
                    break;
                case "methodType_info":
                    ConstantPoolMethodTypeInfo methodTypeInfo = (ConstantPoolMethodTypeInfo) constant;
                    constantUtf8Info = (ConstantPoolUtf8Info) constantPools.get(Integer.parseInt(methodTypeInfo.getMethodTypeInfo().substring(1)));
                    methodTypeInfo.setMethodTypeInfo(constantUtf8Info.getUtf8Info());
                    break;
                case "module_info":
                    ConstantPoolModuleInfo moduleInfo = (ConstantPoolModuleInfo) constant;
                    constantUtf8Info = (ConstantPoolUtf8Info) constantPools.get(Integer.parseInt(moduleInfo.getModuleInfo().substring(1)));
                    moduleInfo.setModuleInfo(constantUtf8Info.getUtf8Info());
                    break;
                case "package_info":
                    ConstantPoolPackageInfo packageInfo = (ConstantPoolPackageInfo) constant;
                    constantUtf8Info = (ConstantPoolUtf8Info) constantPools.get(Integer.parseInt(packageInfo.getPackageInfo().substring(1)));
                    packageInfo.setPackageInfo(constantUtf8Info.getUtf8Info());
                    break;
                case "nameAndType_info":
                    ConstantPoolNameAndTypeInfo nameAndTypeInfo = (ConstantPoolNameAndTypeInfo) constant;
                    constantUtf8Info = (ConstantPoolUtf8Info) constantPools.get(Integer.parseInt(nameAndTypeInfo.getNameInfoIndex().substring(1)));
                    nameAndTypeInfo.setNameInfoIndex(constantUtf8Info.getUtf8Info());
                    constantUtf8Info = (ConstantPoolUtf8Info) constantPools.get(Integer.parseInt(nameAndTypeInfo.getTypeInfoIndex().substring(1)));
                    nameAndTypeInfo.setTypeInfoIndex(constantUtf8Info.getUtf8Info());
                    break;
                default:
                    break;
            }
        });
        constantPools.stream().filter(Objects::nonNull).forEach(constant -> {
            switch (constant.getType()) {
                case "methodRef_info":
                    ConstantPoolMethodRefInfo methodRefInfo = (ConstantPoolMethodRefInfo) constant;
                    ConstantPoolClassInfo constantClassInfo = (ConstantPoolClassInfo) constantPools.get(Integer.parseInt(methodRefInfo.getClassInfo().substring(1)));
                    methodRefInfo.setClassInfo(constantClassInfo.getClassInfo());

                    ConstantPoolNameAndTypeInfo constantPoolNameAndTypeInfo = (ConstantPoolNameAndTypeInfo) constantPools.get(methodRefInfo.getNameAndTypeInfoIndex());
                    methodRefInfo.setNameAndTypeInfo(constantPoolNameAndTypeInfo);
                    methodRefInfo.setNameAndTypeInfoIndex(null);
                    break;
                case "interfaceMethodRef_info":
                    ConstantPoolInterfaceMethodRefInfo interfaceMethodRefInfo = (ConstantPoolInterfaceMethodRefInfo) constant;
                    ConstantPoolClassInfo constantClassInfo1 = (ConstantPoolClassInfo) constantPools.get(Integer.parseInt(interfaceMethodRefInfo.getClassInfo().substring(1)));
                    interfaceMethodRefInfo.setClassInfo(constantClassInfo1.getClassInfo());

                    interfaceMethodRefInfo.setNameAndTypeInfo((ConstantPoolNameAndTypeInfo) constantPools.get(interfaceMethodRefInfo.getNameAndTypeInfoIndex()));
                    interfaceMethodRefInfo.setNameAndTypeInfoIndex(null);
                    break;
                case "invoke_dynamic_info":
                    ConstantPoolInvokeDynamicInfo invokeDynamicInfo = (ConstantPoolInvokeDynamicInfo) constant;
                    invokeDynamicInfo.setNameAndTypeInfo((ConstantPoolNameAndTypeInfo) constantPools.get(invokeDynamicInfo.getNameAndTypeInfoIndex()));
                    invokeDynamicInfo.setNameAndTypeInfoIndex(null);
                    break;
                case "fieldRef_info":
                    ConstantPoolFieldRefInfo fieldRefInfo = (ConstantPoolFieldRefInfo) constant;
                    ConstantPoolClassInfo constantClassInfo2 = (ConstantPoolClassInfo) constantPools.get(Integer.parseInt(fieldRefInfo.getClassInfo().substring(1)));
                    fieldRefInfo.setClassInfo(constantClassInfo2.getClassInfo());

                    fieldRefInfo.setNameAndTypeInfo((ConstantPoolNameAndTypeInfo) constantPools.get(fieldRefInfo.getNameAndTypeInfoIndex()));
                    fieldRefInfo.setNameAndTypeInfoIndex(null);
                    break;
                default:
                    break;
            }
        });
    }

    private static void fillFieldsOrMethods(List<ConstantPoolFieldOrMethods> fields, List<ConstantPool> constantPools) {
        fields.forEach(field -> {
            int index = Integer.parseInt(field.getName().substring(1));
            ConstantPoolUtf8Info utf8Info = (ConstantPoolUtf8Info) constantPools.get(index);
            field.setName(utf8Info.getUtf8Info());

            index = Integer.parseInt(field.getDescriptor().substring(1));
            utf8Info = (ConstantPoolUtf8Info) constantPools.get(index);
            field.setDescriptor(utf8Info.getUtf8Info());

            fillAttributes(field.getAttributes(), constantPools);
        });
    }

    private static void fillAttributes(List<AttributeInfo> attributes, List<ConstantPool> constantPools) {
        attributes.forEach(attributeInfo -> {
            switch (attributeInfo.getAttributeType()) {
                case "ConstantValue":
                    AttributeConstantValue constantValue = (AttributeConstantValue) attributeInfo;
                    constantValue.setConstantValue(constantPools.get(constantValue.getConstantValueIndex()));
                    break;
                case "InnerClasses":
                    AttributeInnerClasses innerClasses = (AttributeInnerClasses) attributeInfo;
                    innerClasses.getInnerClassesInfos().forEach(innerClassesInfo -> {
                        int index = Integer.parseInt(innerClassesInfo.getInnerClassInfo().substring(1));
                        ConstantPoolClassInfo classInfo = (ConstantPoolClassInfo) constantPools.get(index);
                        innerClassesInfo.setInnerClassInfo(classInfo.getClassInfo());

                        index = Integer.parseInt(innerClassesInfo.getOuterClassInfo().substring(1));
                        classInfo = (ConstantPoolClassInfo) constantPools.get(index);
                        innerClassesInfo.setOuterClassInfo(classInfo.getClassInfo());

                        index = Integer.parseInt(innerClassesInfo.getInnerName().substring(1));
                        ConstantPoolUtf8Info utf8Info = (ConstantPoolUtf8Info) constantPools.get(index);
                        innerClassesInfo.setInnerName(utf8Info.getUtf8Info());
                    });
                    break;
                case "Code":
                    fillAttributes(((AttributeCode) attributeInfo).getAttributes(), constantPools);
                    break;
                case "LocalVariableTable":
                    AttributeLocalVariableTable localVariableTable = (AttributeLocalVariableTable) attributeInfo;
                    localVariableTable.getLocalVariableTables().forEach(localVariableInfo -> {
                        int index = Integer.parseInt(localVariableInfo.getName().substring(1));
                        ConstantPoolUtf8Info utf8Info = (ConstantPoolUtf8Info) constantPools.get(index);
                        localVariableInfo.setName(utf8Info.getUtf8Info());

                        index = Integer.parseInt(localVariableInfo.getDescriptor().substring(1));
                        utf8Info = (ConstantPoolUtf8Info) constantPools.get(index);
                        localVariableInfo.setDescriptor(utf8Info.getUtf8Info());
                    });
                    break;
                case "LocalVariableTypeTable":
                    AttributeLocalVariableTypeTable localVariableTypeTable = (AttributeLocalVariableTypeTable) attributeInfo;
                    localVariableTypeTable.getLocalVariableTypes().forEach(localVariableTypeInfo -> {
                        int index = Integer.parseInt(localVariableTypeInfo.getName().substring(1));
                        ConstantPoolUtf8Info utf8Info = (ConstantPoolUtf8Info) constantPools.get(index);
                        localVariableTypeInfo.setName(utf8Info.getUtf8Info());

                        index = Integer.parseInt(localVariableTypeInfo.getDescriptor().substring(1));
                        utf8Info = (ConstantPoolUtf8Info) constantPools.get(index);
                        localVariableTypeInfo.setDescriptor(utf8Info.getUtf8Info());
                    });
                    break;
                case "MethodParameters":
                    AttributeMethodParameters methodParameters = (AttributeMethodParameters) attributeInfo;
                    methodParameters.getParameters().forEach(parameter -> {
                        int index = Integer.parseInt(parameter.getName().substring(1));
                        ConstantPoolUtf8Info utf8Info = (ConstantPoolUtf8Info) constantPools.get(index);
                        parameter.setName(utf8Info.getUtf8Info());
                    });
                    break;
                case "Signature":
                    AttributeSignature signature = (AttributeSignature) attributeInfo;
                    int index = Integer.parseInt(signature.getSignature().substring(1));
                    ConstantPoolUtf8Info utf8Info = (ConstantPoolUtf8Info) constantPools.get(index);
                    signature.setSignature(utf8Info.getUtf8Info());
                    break;
                case "SourceFile":
                    AttributeSourceFile sourceFile = (AttributeSourceFile) attributeInfo;
                    int index1 = Integer.parseInt(sourceFile.getSourceFile().substring(1));
                    ConstantPoolUtf8Info utf8Info1 = (ConstantPoolUtf8Info) constantPools.get(index1);
                    sourceFile.setSourceFile(utf8Info1.getUtf8Info());
                    break;
                default:
                    break;
            }
        });
    }

    private static <T extends Parser<?>> T getParser(Class<T> tClass) {
        return ParserCache.getParser(tClass);
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
            //填充当前类
            int index = Integer.parseInt(classByteCode.getThisClass().substring(1));
            ConstantPoolClassInfo constantClassInfo = (ConstantPoolClassInfo) classByteCode.getConstantPools().get(index);
            classByteCode.setThisClass(constantClassInfo.getClassInfo());
            //填充父类
            index = Integer.parseInt(classByteCode.getSuperClass().substring(1));
            constantClassInfo = (ConstantPoolClassInfo) classByteCode.getConstantPools().get(index);
            classByteCode.setSuperClass(constantClassInfo.getClassInfo());
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
