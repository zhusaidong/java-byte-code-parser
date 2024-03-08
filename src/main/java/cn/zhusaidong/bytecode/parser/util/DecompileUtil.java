package cn.zhusaidong.bytecode.parser.util;

import cn.zhusaidong.bytecode.parser.enums.ClassAccessFlagEnum;
import cn.zhusaidong.bytecode.parser.enums.InnerClassAccessFlagEnum;
import cn.zhusaidong.bytecode.parser.interfaces.AccessFlag;
import cn.zhusaidong.bytecode.parser.structure.data.ClassByteCode;
import lombok.experimental.UtilityClass;

import java.util.List;

/**
 * @author zhusaidong
 * @since 2024/1/30
 */
@UtilityClass
public class DecompileUtil {
    public String decompile(ClassByteCode classByteCode) {
        return accessFlagsToCode(classByteCode.getAccessFlags()) +
                classByteCode.getThisClass() +
                " {\n" +
                "\t" +
                "\n}";
    }

    private String accessFlagsToCode(List<AccessFlag> accessFlags) {
        StringBuilder accessFlag = new StringBuilder();
        if (accessFlags.contains(ClassAccessFlagEnum.ACC_PUBLIC)) {
            accessFlag.append("public ");
        }
        if (accessFlags.contains(InnerClassAccessFlagEnum.ACC_STATIC)) {
            accessFlag.append("static ");
        }
        if (accessFlags.contains(ClassAccessFlagEnum.ACC_FINAL)) {
            accessFlag.append("final ");
        }
        if (accessFlags.contains(ClassAccessFlagEnum.ACC_ABSTRACT)) {
            accessFlag.append("abstract ");
        }
        if (accessFlags.contains(ClassAccessFlagEnum.ACC_ENUM)) {
            accessFlag.append("enum ");
        } else if (accessFlags.contains(ClassAccessFlagEnum.ACC_INTERFACE)) {
            accessFlag.append("interface ");
        } else {
            accessFlag.append("class ");
        }
        return accessFlag.toString();
    }

    /*
    {注解}
    public {static} {final} {abstract} {类的类型：class|enum|interface} {className} {
        {+字段}
        {+方法}
    }
     */
}
