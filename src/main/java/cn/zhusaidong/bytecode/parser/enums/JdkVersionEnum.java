package cn.zhusaidong.bytecode.parser.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * jdk版本枚举
 *
 * @author zhusaidong
 * @since 2024/1/19
 */
@AllArgsConstructor
@Getter
public enum JdkVersionEnum {
    /**
     * jdk6
     */
    JDK6(50, 0),
    JDK7(51, 0),
    JDK8(52, 0),
    JDK9(53, 0),
    JDK10(54, 0),
    JDK11(55, 0),
    JDK12(56, 0),
    JDK13(57, 0),
    JDK14(58, 0),
    JDK15(59, 0),
    JDK16(60, 0),
    JDK17(61, 0),
    JDK18(62, 0),
    JDK19(63, 0),
    JDK20(64, 0),
    JDK21(65, 0),
    ;

    /**
     * 主版本，2字节
     */
    private final Integer majorVersion;
    /**
     * 次版本，2字节
     */
    private final Integer minorVersion;

    public static JdkVersionEnum find(int majorVersion, int minorVersion) {
        if (majorVersion > JDK21.majorVersion || majorVersion < JDK6.majorVersion) {
            System.out.println("jdk version match need in [50,65].");
        }

        return Arrays.stream(values())
                .filter(versionEnum -> versionEnum.majorVersion == majorVersion && versionEnum.minorVersion == minorVersion)
                .findAny()
                .orElse(null);
    }
}
