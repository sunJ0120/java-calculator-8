package calculator.domain.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * 클래스 이름: BasicFormulaTest
 * <p>
 * 버전 정보: 1.0
 * <p>
 * 날짜: 2025-10-17
 * <p>
 * 저작권 주의: Copyright (c) 2025 sspur
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Test
@Tag("basic")
public @interface BasicFormulaTest {
}
