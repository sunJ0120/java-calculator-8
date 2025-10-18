package calculator.domain;

import calculator.domain.exception.InvalidNumberException;

/**
 * 클래스 이름: StringCalculator
 * <p>
 * 버전 정보: 1.1
 * <p>
 * 날짜: 2025-10-17
 */
public class StringCalculator {
    private final String[] numbers;    // 받은 숫자들

    public StringCalculator(String[] numbers) {
        if (numbers == null) {    // null 안정성
            throw new IllegalArgumentException("숫자 배열은 null일 수 없습니다");
        }
        this.numbers = numbers;
    }

    public int sum() {
        int result = 0;
        for (String number : numbers) {
            try {
                result += Integer.parseInt(number);
            } catch (NumberFormatException e) {    // 숫자가 아닌 값이 들어올 경우
                throw new InvalidNumberException(number, e);
            }
        }
        return result;
    }
}
