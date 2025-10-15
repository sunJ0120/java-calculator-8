package calculator;

import calculator.domain.Formula;
import calculator.domain.StringCalculator;
import calculator.view.InputView;
import calculator.view.OutputView;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        Formula formula = new Formula(inputView.readFormula());
        StringCalculator stringCalculator = new StringCalculator(formula.toNumbers());
        outputView.printResult(stringCalculator.sum());
    }
}
