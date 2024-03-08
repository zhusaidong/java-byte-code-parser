package cn.zhusaidong.bytecode.parser.structure.data.attribute;

import cn.zhusaidong.bytecode.parser.interfaces.AccessFlag;
import cn.zhusaidong.bytecode.parser.structure.data.AttributeInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author zhusaidong
 * @since 2024/1/26
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AttributeInnerClasses extends AttributeInfo {
    /**
     * 需要记录多少个内部类信息，每一个内部类的信息都由一个 inner_classes_info 表进行描述。
     */
    private Integer numberOfClasses;
    private List<InnerClassesInfo> innerClassesInfos;

    public AttributeInnerClasses() {
        setAttributeType("InnerClasses");
    }

    @Data
    public static class InnerClassesInfo {
        /**
         * 指向常量池中CONSTANT_Class_info 型常量的索引,代表了内部类的符号引用
         */
        private String innerClassInfo;
        /**
         * 指向常量池中CONSTANT_Class_info 型常量的索引,代表了宿主类的符号引用
         */
        private String outerClassInfo;
        /**
         * 指向常量池中 CONSTANT_Utf8_info 型常量的索引，代表这个内部类的名称，如果是匿名内部类，这项值为 0
         */
        private String innerName;
        /**
         * 内部类的访问标志，类似于类的 access_flags
         */
        private List<AccessFlag> innerClassAccessFlags;
    }
}
