package calculator.domain.exception;

/**
 * 클래스 이름: MinusNumberException
 * <p>
 * 버전 정보: 1.0
 * <p>
 * 날짜: 2025-10-19
 */
public class MinusNumberException extends IllegalArgumentException {
    public MinusNumberException(String number) {
        super("음수는 계산할 수 없습니다: " + number);
    }

    public MinusNumberException(String number, Throwable cause) {
        super("음수는 계산할 수 없습니다: " + number, cause);
    }
}
