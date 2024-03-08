package cn.zhusaidong.bytecode.parser.structure.data.attribute;

import cn.zhusaidong.bytecode.parser.structure.data.AttributeInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author zhusaidong
 * @since 2024/1/25
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AttributeDeprecated extends AttributeInfo {
    public AttributeDeprecated() {
        setAttributeType("Deprecated");
    }
}
