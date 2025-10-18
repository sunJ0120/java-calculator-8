package calculator.view;

import camp.nextstep.edu.missionutils.Console;

/**
 * 클래스 이름: InputView
 * <p>
 * 버전 정보: 1.1
 * <p>
 * 날짜: 2025-10-17
 */
public class InputView {
    private static final String INPUT_MESSAGE = "덧셈할 문자열을 입력해 주세요.";

    public String readFormula() {
        System.out.println(INPUT_MESSAGE);
        // 공식 가이드 라인에 따라 설정
        String inputFormula = Console.readLine();
        validateInputFormula(inputFormula);
        return inputFormula;
    }

    // 입력 예외 처리
    private void validateInputFormula(String inputFormula) {
        if (inputFormula == null) {
            throw new IllegalArgumentException("입력 형식이 잘못되었습니다.");
        }
    }
}
