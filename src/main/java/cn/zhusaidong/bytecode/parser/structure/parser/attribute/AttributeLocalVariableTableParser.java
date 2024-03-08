package cn.zhusaidong.bytecode.parser.structure.parser.attribute;

import cn.zhusaidong.bytecode.parser.interfaces.AttributeParser;
import cn.zhusaidong.bytecode.parser.structure.data.AttributeInfo;
import cn.zhusaidong.bytecode.parser.structure.data.attribute.AttributeLocalVariableTable;
import cn.zhusaidong.bytecode.parser.util.ByteUtil;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * @author zhusaidong
 */
public class AttributeLocalVariableTableParser implements AttributeParser<AttributeInfo> {
    @Override
    public AttributeInfo parser(InputStream is, Object... objs) {
        AttributeLocalVariableTable localVariableTable = new AttributeLocalVariableTable();
        localVariableTable.setLocalVariableTableLength(ByteUtil.toDigits(ByteUtil.getBytes(is, 2)));

        ArrayList<AttributeLocalVariableTable.LocalVariableInfo> localVariableInfos = new ArrayList<>();
        for (int i = 0; i < localVariableTable.getLocalVariableTableLength(); i++) {
            AttributeLocalVariableTable.LocalVariableInfo localVariableInfo = new AttributeLocalVariableTable.LocalVariableInfo();
            localVariableInfo.setStartPc(ByteUtil.toDigits(ByteUtil.getBytes(is, 2)));
            localVariableInfo.setLength(ByteUtil.toDigits(ByteUtil.getBytes(is, 2)));
            localVariableInfo.setName("#" + ByteUtil.toDigits(ByteUtil.getBytes(is, 2)));
            localVariableInfo.setDescriptor("#" + ByteUtil.toDigits(ByteUtil.getBytes(is, 2)));
            localVariableInfo.setIndex(ByteUtil.toDigits(ByteUtil.getBytes(is, 2)));

            localVariableInfos.add(localVariableInfo);
        }
        localVariableTable.setLocalVariableTables(localVariableInfos);

        return localVariableTable;
    }
}
