package calculator.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

/**
 * 클래스 이름: StringCalculatorTest
 * <p>
 * 버전 정보:
 * <p>
 * 날짜: 2025-10-17
 * <p>
 * 저작권 주의: Copyright (c) 2025 sspur
 */
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class StringCalculatorTest {
    @Test
    void 정상_계산() {
        //given
        String[] numbers = {"10", "20", "30"};
        StringCalculator calculator = new StringCalculator(numbers);    // 독립적으로 사용자 시나리오 대로 계산

        //when
        int result = calculator.sum();

        //then
        assertThat(result).isEqualTo(60);
    }

    @Test
    void null_배열_예외() {
        //given

        //when & then
        assertThatThrownBy(() -> new StringCalculator(null)).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("숫자 배열은 null일 수 없습니다");
    }

    @Test
    void 빈_배열_계산() {
        //given
        String[] numbers = {};
        StringCalculator calculator = new StringCalculator(numbers);

        //when
        int result = calculator.sum();

        //then
        assertThat(result).isEqualTo(0);
    }
}