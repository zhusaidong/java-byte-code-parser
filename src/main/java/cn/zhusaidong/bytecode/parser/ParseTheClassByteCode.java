package cn.zhusaidong.bytecode.parser;

import cn.zhusaidong.bytecode.parser.structure.data.ClassByteCode;
import cn.zhusaidong.bytecode.parser.util.ParserCacheUtil;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
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
        Class<?> byteCodeParserClass = ParseTheClassByteCode.class;
        File classFile = getByteCodeFileByClass(byteCodeParserClass);

        //解析class文件
        ClassByteCode classByteCode = getClassByteCodeByClassFile(classFile);
        System.out.println(classByteCode);

        //todo 反编译还原代码
        //String decompile = DecompileUtil.decompile(classByteCode);
        //System.out.println(decompile);
    }

    /**
     * 获取class文件对象
     */
    private static File getByteCodeFileByClass(Class<?> tClass) throws URISyntaxException {
        URL resource = tClass.getClassLoader().getResource(tClass.getName().replace(".", "/") + ".class");
        if (resource != null) {
            return new File(resource.toURI());
        }
        return null;
    }

    /**
     * 解析class文件
     */
    private static ClassByteCode getClassByteCodeByClassFile(File classFile) throws IOException {
        if (classFile == null) {
            return null;
        }
        try (InputStream inputStream = Files.newInputStream(classFile.toPath())) {
            return ParserCacheUtil.getParser(ByteCodeParser.class).parser(inputStream, true);
        }
    }
}
