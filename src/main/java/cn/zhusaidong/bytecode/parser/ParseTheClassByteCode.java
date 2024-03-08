package cn.zhusaidong.bytecode.parser;

import cn.zhusaidong.bytecode.parser.domain.ParserCache;
import cn.zhusaidong.bytecode.parser.structure.data.ClassByteCode;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.file.Files;

/**
 * 解析字节码
 *
 * @author zhusaidong
 * @since 2024/1/28
 */
public class ParseTheClassByteCode {
    public static void main(String[] args) throws URISyntaxException, IOException {
        //获取class文件：为了测试使用了当前项目的class文件
        Class<?> byteCodeParserClass = TestClass.class;
        File classFile = getByteCodeFileByClass(byteCodeParserClass);

        //解析class文件
        ClassByteCode classByteCode = ParserCache.getParser(ByteCodeParser.class).parser(getByteArrayByFile(classFile), true);
        System.out.println(classByteCode);

        //todo 反编译还原代码
        //String decompile = DecompileUtil.decompile(classByteCode);
        //System.out.println(decompile);
    }

    /**
     * 获取class文件对象
     */
    private static File getByteCodeFileByClass(Class<?> tClass) throws URISyntaxException {
        return new File(tClass.getClassLoader().getResource(tClass.getName().replace(".", "/") + ".class").toURI());
    }

    /**
     * 获取class文件的字节流
     */
    private static InputStream getByteArrayByFile(File classFile) throws IOException {
        return Files.newInputStream(classFile.toPath());
    }
}
