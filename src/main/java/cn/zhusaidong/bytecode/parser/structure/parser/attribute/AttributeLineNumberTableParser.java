package cn.zhusaidong.bytecode.parser.structure.parser.attribute;

import cn.zhusaidong.bytecode.parser.interfaces.AttributeParser;
import cn.zhusaidong.bytecode.parser.structure.data.AttributeInfo;
import cn.zhusaidong.bytecode.parser.structure.data.attribute.AttributeLineNumberTable;
import cn.zhusaidong.bytecode.parser.structure.data.attribute.AttributeLineNumberTable.LineNumberInfo;
import cn.zhusaidong.bytecode.parser.util.ByteUtil;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * @author zhusaidong
 */
public class AttributeLineNumberTableParser implements AttributeParser<AttributeInfo> {
    @Override
    public AttributeInfo parser(InputStream is, Object... objs) {
        AttributeLineNumberTable lineNumberTable = new AttributeLineNumberTable();
        lineNumberTable.setLineNumberTableLength(ByteUtil.toDigits(ByteUtil.getBytes(is, 2)));
        ArrayList<LineNumberInfo> lineNumberInfos = new ArrayList<>();
        for (int i = 0; i < lineNumberTable.getLineNumberTableLength(); i++) {
            LineNumberInfo lineNumberInfo = new LineNumberInfo();
            lineNumberInfo.setStartPc(ByteUtil.toDigits(ByteUtil.getBytes(is, 2)));
            lineNumberInfo.setLineNumber(ByteUtil.toDigits(ByteUtil.getBytes(is, 2)));
            lineNumberInfos.add(lineNumberInfo);
        }
        lineNumberTable.setLineNumberInfos(lineNumberInfos);

        return lineNumberTable;
    }
}
