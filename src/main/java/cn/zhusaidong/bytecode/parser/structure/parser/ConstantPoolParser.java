package cn.zhusaidong.bytecode.parser.structure.parser;

import cn.zhusaidong.bytecode.parser.domain.ParserCache;
import cn.zhusaidong.bytecode.parser.interfaces.Parser;
import cn.zhusaidong.bytecode.parser.structure.data.ConstantPool;
import cn.zhusaidong.bytecode.parser.util.ByteUtil;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static cn.zhusaidong.bytecode.parser.domain.ParserConstants.CONSTANT_POOL_PARSER_MAP;

/**
 * 常量池解析器
 *
 * @author zhusaidong
 * @since 2024/1/24
 */
public class ConstantPoolParser implements Parser<List<ConstantPool>> {
    @Override
    public List<ConstantPool> parser(InputStream is, Object... objs) {
        //常量池大小：2个字节
        //这个容量计数是从 1 而不是 0 开始的，如常量池容量为十六进制数 0x0016，即十进制的 22，这就代表常量池中有 21 项常量，索引值范围为 1～21。
        Integer poolCount = ByteUtil.toDigits(ByteUtil.getBytes(is, 2));
        List<ConstantPool> constantPools = new ArrayList<>(poolCount);
        //常量池索引从1开始，故0位置填充上`null`
        constantPools.add(null);
        //这里`i`为什么从1开始，见上面`poolCount`变量上的注释
        for (int i = 1; i < poolCount; i++) {
            //常量池类型：1个字节
            //具体类型见`CONSTANT_POOL_PARSER_MAP`的定义或者见文档：https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.4
            Integer tag = ByteUtil.toDigits(ByteUtil.getBytes(is, 1));
            constantPools.add(ParserCache.getParser(CONSTANT_POOL_PARSER_MAP.get(tag)).parser(is));
        }
        return constantPools;
    }
}
