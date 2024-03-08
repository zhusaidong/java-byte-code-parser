package cn.zhusaidong.bytecode.parser.structure.parser.attribute;

import cn.zhusaidong.bytecode.parser.interfaces.AttributeParser;
import cn.zhusaidong.bytecode.parser.structure.data.AttributeInfo;
import cn.zhusaidong.bytecode.parser.structure.data.attribute.AttributeLocalVariableTypeTable;
import cn.zhusaidong.bytecode.parser.util.ByteUtil;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * @author zhusaidong
 */
public class AttributeLocalVariableTypeTableParser implements AttributeParser<AttributeInfo> {
    @Override
    public AttributeInfo parser(InputStream is, Object... objs) {
        AttributeLocalVariableTypeTable localVariableTypeTable = new AttributeLocalVariableTypeTable();
        localVariableTypeTable.setLocalVariableTableLength(ByteUtil.toDigits(ByteUtil.getBytes(is, 2)));

        ArrayList<AttributeLocalVariableTypeTable.LocalVariableTypeInfo> localVariableInfos = new ArrayList<>();
        for (int i = 0; i < localVariableTypeTable.getLocalVariableTableLength(); i++) {
            AttributeLocalVariableTypeTable.LocalVariableTypeInfo localVariableTypeInfo = new AttributeLocalVariableTypeTable.LocalVariableTypeInfo();
            localVariableTypeInfo.setStartPc(ByteUtil.toDigits(ByteUtil.getBytes(is, 2)));
            localVariableTypeInfo.setLength(ByteUtil.toDigits(ByteUtil.getBytes(is, 2)));
            localVariableTypeInfo.setName("#" + ByteUtil.toDigits(ByteUtil.getBytes(is, 2)));
            localVariableTypeInfo.setDescriptor("#" + ByteUtil.toDigits(ByteUtil.getBytes(is, 2)));
            localVariableTypeInfo.setIndex(ByteUtil.toDigits(ByteUtil.getBytes(is, 2)));

            localVariableInfos.add(localVariableTypeInfo);
        }
        localVariableTypeTable.setLocalVariableTypes(localVariableInfos);

        return localVariableTypeTable;
    }
}
