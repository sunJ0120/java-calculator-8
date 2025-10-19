package calculator.domain;

import calculator.domain.exception.InvalidNumberException;
import calculator.domain.exception.MinusNumberException;

/**
 * 클래스 이름: StringCalculator
 * <p>
 * 버전 정보: 1.2
 * <p>
 * 날짜: 2025-10-19
 */
public class StringCalculator {
    private final String[] numbers;    // 받은 숫자들

    public StringCalculator(String[] numbers) {
        if (numbers == null) {    // null 안정성
            throw new IllegalArgumentException("숫자 배열은 null일 수 없습니다");
        }
        this.numbers = numbers;
    }

    private static int validation(String number) {
        try {
            int num = Integer.parseInt(number);
            if (num < 0) {    // 음수 검증
                throw new MinusNumberException(number);
            }
            return num;
        } catch (NumberFormatException e) {    // 숫자가 아닌 값이 들어올 경우
            throw new InvalidNumberException(number, e);
        }
    }

    public int sum() {
        int result = 0;
        for (String number : numbers) {
            result += validation(number);
        }
        return result;
    }
}
