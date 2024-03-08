package cn.zhusaidong.bytecode.parser.structure.parser;

import cn.zhusaidong.bytecode.parser.interfaces.Parser;
import cn.zhusaidong.bytecode.parser.util.ByteUtil;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 接口解析器
 *
 * @author zhusaidong
 * @since 2024/1/24
 */
public class InterfaceParser implements Parser<List<Integer>> {
    @Override
    public List<Integer> parser(InputStream is, Object... objs) {
        //接口数
        Integer interfaceCount = ByteUtil.toDigits(ByteUtil.getBytes(is, 2));
        List<Integer> interfaceList = new ArrayList<>(interfaceCount);
        //接口表索引
        for (int i = 0; i < interfaceCount; i++) {
            interfaceList.add(ByteUtil.toDigits(ByteUtil.getBytes(is, 2)));
        }
        return interfaceList;
    }
}
