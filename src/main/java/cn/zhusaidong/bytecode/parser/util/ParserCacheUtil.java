package cn.zhusaidong.bytecode.parser.util;

import cn.zhusaidong.bytecode.parser.interfaces.Parser;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 解析器缓存工具类
 *
 * @author zhusaidong
 * @since 2024/1/30
 */
public class ParserCacheUtil {
    public static final Map<Class<? extends Parser<?>>, Parser<?>> CACHE_MAP = new ConcurrentHashMap<>();

    public static <T extends Parser<?>> T getParser(Class<T> tClass) {
        @SuppressWarnings("unchecked")
        T parser = (T) CACHE_MAP.get(tClass);
        if (parser == null) {
            try {
                parser = tClass.newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
            CACHE_MAP.put(tClass, parser);
        }

        return parser;
    }
}
