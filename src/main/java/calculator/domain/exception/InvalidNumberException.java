package calculator.domain.exception;

/**
 * 클래스 이름: InvalidNumberException
 * <p>
 * 버전 정보: 1.0
 * <p>
 * 날짜: 2025-10-17
 */
public class InvalidNumberException extends IllegalArgumentException {
    public InvalidNumberException(String number) {
        super("유효하지 않은 숫자입니다: " + number);
    }

    public InvalidNumberException(String number, Throwable cause) {
        super("유효하지 않은 숫자입니다: " + number, cause);
    }
}
