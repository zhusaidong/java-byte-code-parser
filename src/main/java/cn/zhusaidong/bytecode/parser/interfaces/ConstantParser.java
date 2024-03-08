package cn.zhusaidong.bytecode.parser.interfaces;

import cn.zhusaidong.bytecode.parser.structure.data.ConstantPool;

/**
 * 常量池解析器接口
 *
 * @author zhusaidong
 * @since 2024/1/24
 */
public interface ConstantParser<T extends ConstantPool> extends Parser<T> {
}
