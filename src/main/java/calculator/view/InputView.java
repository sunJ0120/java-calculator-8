package calculator.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    public String readFormula(){
        System.out.println("덧셈할 문자열을 입력해 주세요.");
        return Console.readLine(); //공식 가이드 라인에 따라 설정
    }
}
