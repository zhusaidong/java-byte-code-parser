package cn.zhusaidong.bytecode.parser.structure.data.attribute;

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
public class AttributeLineNumberTable extends AttributeInfo {
    private Integer lineNumberTableLength;
    private List<LineNumberInfo> lineNumberInfos;

    public AttributeLineNumberTable() {
        setAttributeType("LineNumberTable");
    }

    @Data
    public static class LineNumberInfo {
        /**
         * 字节码行号
         */
        private Integer startPc;
        /**
         * Java 源码行号
         */
        private Integer lineNumber;
    }
}
