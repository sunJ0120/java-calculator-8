package calculator.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class FormulaTest {
    @Test
    void 일반_문자열_구분자_틀렸을때() {
        //given
        String test = "10;11:12";

        //when & then
        assertThrows(IllegalArgumentException.class, () -> {
            new Formula(test);
        });
    }

    @Test
    void 수식_틀렸을때() {
        //given
        String test = "가나다라:마바사,아자차카";

        //when & then
        assertThrows(IllegalArgumentException.class, () -> {
            new Formula(test);
        });
    }

    @Test
    void 일반_문자열_계산() {
        //given
        String test = "10:20,30:40,50";

        //when
        Formula formula = new Formula(test);

        //then
        assertThat(formula.toNumbers()).containsExactly("10", "20", "30", "40", "50");
    }

    @Test
    void 커스텀_문자열_형식이_틀렸을_경우() {
        //given
        String test = "//;\\v10;11;12";

        //when & then
        assertThrows(IllegalArgumentException.class, () -> {
            new Formula(test);
        });
    }

    @Test
    void 커스텀_문자열_구분자_다를때() {
        //given
        String test = "//;\n10:11+12";

        //when & then
        assertThrows(IllegalArgumentException.class, () -> {
            new Formula(test);
        });
    }

    @Test
    void 커스텀_문자열에_개행문자_없을때() {
        //given
        String test = "//;10;11;12";

        //when & then
        assertThrows(IllegalArgumentException.class, () -> {
            new Formula(test);
        });
    }

    @Test
    void 커스텀_문자열에_구분자_여러개_일때() {
        //given
        String test = "//:+:\n10:+:11:+:12";

        //when
        Formula formula = new Formula(test);

        //then
        assertThat(formula.toNumbers()).containsExactly("10", "11", "12"); //순서까지 맞추기 위함
    }

    @Test
    void 커스텀_문자열_시작_틀렸을때() {
        //given
        String test = ":+:\n10:+:11:+:12";

        //when & then
        assertThrows(IllegalArgumentException.class, () -> {
            new Formula(test);
        });
    }

    @Test
    void 구분자로_시작() {
        //given
        String test = ",1,2,3,4,5";

        //when & then
        assertThrows(IllegalArgumentException.class, () -> {
            new Formula(test);
        });
    }

    @Test
    void 구분자로_끝() {
        //given
        String test = "1,2,3,4,5,";

        //when & then
        assertThrows(IllegalArgumentException.class, () -> {
            new Formula(test);
        });
    }

    @Test
    void 구분자_연속() {
        //given
        String test = "1,,,,,2,3,4,5";

        //when & then
        assertThrows(IllegalArgumentException.class, () -> {
            new Formula(test);
        });
    }

    @Test
    void 숫자_하나만() {
        //given
        String test = "5";

        //when
        Formula formula = new Formula(test);

        //then
        assertThat(formula.toNumbers()).contains("5");
    }

    @Test
    void 커스텀_구분자_연속() {
        //given
        String test = "//.\n5......6.7.8";

        //when & then
        assertThrows(IllegalArgumentException.class, () -> {
            new Formula(test);
        });
    }

    @Test
    void 입력이_없을_경우() {
        //given
        String test = "";

        //when
        Formula formula = new Formula(test);

        //then
        assertThat(formula.toNumbers()).contains("0");
    }

    @Test
    void 공백_여러개_입력() {
        //given
        String test = "       ";

        //when & then
        assertThrows(IllegalArgumentException.class, () -> {
            new Formula(test);
        });
    }

    @Test
    void null_입력() {
        //given
        String test = null;

        //when & then
        assertThrows(IllegalArgumentException.class, () -> {
            new Formula(test);
        });
    }
}