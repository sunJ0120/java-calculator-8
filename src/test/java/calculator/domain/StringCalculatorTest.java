package calculator.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

/**
 * 클래스 이름: StringCalculatorTest
 * <p>
 * 버전 정보: 1.2
 * <p>
 * 날짜: 2025-10-19
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
        assertThrows(IllegalArgumentException.class, () -> {
            new StringCalculator(null);  // ← sum() 호출해야 함!
        });
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

    @Test
    void 음수_계산기_예외_발생() throws Exception {
        //given
        String[] numbers = {"-10", "20", "-30", "40"};
        StringCalculator calculator = new StringCalculator(numbers);

        //when & then
        assertThrows(IllegalArgumentException.class, () -> {
            calculator.sum();  // ← sum() 호출해야 함!
        });
    }
}