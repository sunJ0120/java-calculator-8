package calculator.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import calculator.domain.annotation.BasicFormulaTest;
import calculator.domain.annotation.CustomFormulaTest;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;

/**
 * 클래스 이름: FormulaTest
 * <p>
 * 버전 정보: 1.1
 * <p>
 * 날짜: 2025-10-17
 * <p>
 * 저작권 주의: Copyright (c) 2025 sspur
 */
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class FormulaTest {
    @BasicFormulaTest
    void 잘못된_구분자() {
        //given
        String test = "10;11:12";

        //when & then
        assertThrows(IllegalArgumentException.class, () -> {
            new Formula(test);
        });
    }

    @BasicFormulaTest
    void 문자_포함() {
        //given
        String test = "가나다라:마바사,아자차카";

        //when & then
        assertThrows(IllegalArgumentException.class, () -> {
            new Formula(test);
        });
    }

    @BasicFormulaTest
    void 정상_파싱() {
        //given
        String test = "10:20,30:40,50";

        //when
        Formula formula = new Formula(test);

        //then
        assertThat(formula.toNumbers()).containsExactly("10", "20", "30", "40", "50");
    }

    @CustomFormulaTest
    void 경계_다를때() {
        //given
        String test = "//;\\v10;11;12";

        //when & then
        assertThrows(IllegalArgumentException.class, () -> {
            new Formula(test);
        });
    }

    @CustomFormulaTest
    void 구분자_다를때() {
        //given
        String test = "//;\\n10:11+12";

        //when & then
        assertThrows(IllegalArgumentException.class, () -> {
            new Formula(test);
        });
    }

    @CustomFormulaTest
    void 개행문자_없을때() {
        //given
        String test = "//;10;11;12";

        //when & then
        assertThrows(IllegalArgumentException.class, () -> {
            new Formula(test);
        });
    }

    @CustomFormulaTest
    void 구분자_여러개_일때() {
        //given
        String test = "//:+:\\n10:+:11:+:12";

        //when
        Formula formula = new Formula(test);

        //then
        assertThat(formula.toNumbers()).containsExactly("10", "11", "12"); //순서까지 맞추기 위함
    }

    @CustomFormulaTest
    void 수식에_구분자_없을때() {
        //given
        String test = "//;\\n1";

        //when
        Formula formula = new Formula(test);

        //then
        assertThat(formula.toNumbers()).containsExactly("1");
    }

    @CustomFormulaTest
    void 수식_접두사_없을때() {
        //given
        String test = ":+:\\n10:+:11:+:12";

        //when & then
        assertThrows(IllegalArgumentException.class, () -> {
            new Formula(test);
        });
    }

    @BasicFormulaTest
    void 구분자로_시작할때() {
        //given
        String test = ",1,2,3,4,5";

        //when & then
        assertThrows(IllegalArgumentException.class, () -> {
            new Formula(test);
        });
    }

    @BasicFormulaTest
    void 구분자로_끝날때() {
        //given
        String test = "1,2,3,4,5,";

        //when & then
        assertThrows(IllegalArgumentException.class, () -> {
            new Formula(test);
        });
    }

    @BasicFormulaTest
    void 구분자_연속될때() {
        //given
        String test = "1,,,,,2,3,4,5";

        //when & then
        assertThrows(IllegalArgumentException.class, () -> {
            new Formula(test);
        });
    }

    @BasicFormulaTest
    void 숫자_하나일때() {
        //given
        String test = "5";

        //when
        Formula formula = new Formula(test);

        //then
        assertThat(formula.toNumbers()).contains("5");
    }

    @CustomFormulaTest
    void 커스텀_구분자_연속될때() {
        //given
        String test = "//.\n5......6.7.8";

        //when & then
        assertThrows(IllegalArgumentException.class, () -> {
            new Formula(test);
        });
    }

    @BasicFormulaTest
    void 입력이_없을때() {
        //given
        String test = "";

        //when
        Formula formula = new Formula(test);

        //then
        assertThat(formula.toNumbers()).containsExactly("0");
    }
}