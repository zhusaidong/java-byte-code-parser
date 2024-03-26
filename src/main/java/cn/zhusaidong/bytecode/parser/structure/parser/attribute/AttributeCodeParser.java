package cn.zhusaidong.bytecode.parser.structure.parser.attribute;

import cn.zhusaidong.bytecode.parser.enums.OpcodeEnum;
import cn.zhusaidong.bytecode.parser.interfaces.AttributeParser;
import cn.zhusaidong.bytecode.parser.structure.data.AttributeInfo;
import cn.zhusaidong.bytecode.parser.structure.data.attribute.AttributeCode;
import cn.zhusaidong.bytecode.parser.structure.parser.AttributesParser;
import cn.zhusaidong.bytecode.parser.util.ByteUtil;
import cn.zhusaidong.bytecode.parser.util.ParserCacheUtil;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhusaidong
 */
public class AttributeCodeParser implements AttributeParser<AttributeInfo> {
    @Override
    public AttributeInfo parser(InputStream is, Object... objs) {
        AttributeCode attributeCode = new AttributeCode();
        attributeCode.setMaxStack(ByteUtil.toDigits(ByteUtil.getBytes(is, 2)));
        attributeCode.setMaxLocals(ByteUtil.toDigits(ByteUtil.getBytes(is, 2)));
        attributeCode.setCodeLength(ByteUtil.toDigits(ByteUtil.getBytes(is, 4)));
        String[] hexArray = ByteUtil.toHexArray(ByteUtil.getBytes(is, attributeCode.getCodeLength()));
        attributeCode.setCode(hexArray);

        String[] opcodes = new String[hexArray.length];
        for (int i = 0; i < hexArray.length; i++) {
            opcodes[i] = OpcodeEnum.findOpcodeByCode(Integer.parseInt(hexArray[i], 16));
        }
        attributeCode.setOpcode(opcodes);

        attributeCode.setExceptionTableLength(ByteUtil.toDigits(ByteUtil.getBytes(is, 2)));
        List<AttributeCode.ExceptionTable> exceptionTableList = new ArrayList<>();
        for (int i = 0; i < attributeCode.getExceptionTableLength(); i++) {
            AttributeCode.ExceptionTable exceptionTable = new AttributeCode.ExceptionTable();
            exceptionTable.setStartPc(ByteUtil.toDigits(ByteUtil.getBytes(is, 2)));
            exceptionTable.setEndPc(ByteUtil.toDigits(ByteUtil.getBytes(is, 2)));
            exceptionTable.setCatchType(ByteUtil.toDigits(ByteUtil.getBytes(is, 2)));
            exceptionTable.setHandlerPc(ByteUtil.toDigits(ByteUtil.getBytes(is, 2)));
            exceptionTableList.add(exceptionTable);
        }
        attributeCode.setExceptionTable(exceptionTableList);

        attributeCode.setAttributes(ParserCacheUtil.getParser(AttributesParser.class).parser(is, objs[0]));

        return attributeCode;
    }
}
