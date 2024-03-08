package cn.zhusaidong.bytecode.parser.interfaces;

import java.io.InputStream;

/**
 * 解析器接口
 *
 * @author zhusaidong
 * @since 2024/1/24
 */
public interface Parser<T> {

    /**
     * 解析器
     *
     * @param is   输入流
     * @param objs 其他数据
     * @return 解析出的数据
     */
    T parser(InputStream is, Object... objs);
}
