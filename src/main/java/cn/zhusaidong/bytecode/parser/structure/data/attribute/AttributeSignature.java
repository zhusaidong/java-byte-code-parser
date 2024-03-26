package cn.zhusaidong.bytecode.parser.structure.data.attribute;

import cn.zhusaidong.bytecode.parser.structure.data.AttributeInfo;
import cn.zhusaidong.bytecode.parser.structure.data.ConstantPool;
import cn.zhusaidong.bytecode.parser.structure.data.constant.ConstantPoolUtf8Info;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.function.Function;

/**
 * @author zhusaidong
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AttributeSignature extends AttributeInfo {
    /**
     * 必须是一个对常量池的有效索引。常量池在该索引处的项必须是 CONSTANT_Utf8_info 结构，表示类签名或方法类型签名或字段类型签名。
     */
    private String signature;

    public AttributeSignature() {
        setAttributeType("Signature");
    }

    @Override
    public void fill(Function<Integer, ConstantPool> fun) {
        ConstantPoolUtf8Info utf8Info = (ConstantPoolUtf8Info) fun.apply(getConstantIndex(getSignature()));
        setSignature(utf8Info.getUtf8Info());
    }
}
