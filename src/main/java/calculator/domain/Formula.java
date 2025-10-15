package calculator.domain;

import java.util.regex.Pattern;

public class Formula {
    private final String separator; //구분자
    private final String formula; //수식

    public Formula(String formula) {
        validation(formula); //입력받은 수식 검증
        this.separator = extractSeparator(formula);
        this.formula = extractFormula(formula);

        if (formula.startsWith("//")) {
            validCustomFormulaDetail(this.formula, separator);
        }
    }

    public void validation(String formula) {
        if(formula == null){
            throw new IllegalArgumentException("입력 형식이 잘못되었습니다.");
        }
        if (formula.startsWith("//")) {
            validCustomFormula(formula); // 커스텀 형식 검증
        } else {
            validBasicFormula(formula); // 기본 형식 검증
        }
    }

    private void validCustomFormula(String formula) {
        // 구분자가 있는지, 개행 문자가 있는지
        int endInd = formula.indexOf("\\n");
        if (endInd == -1 || endInd == 2) { //\n이 없거나 정의한 구분자가 없을 때
            System.out.println("입력 형식이 잘못되었습니다.");
            throw new IllegalArgumentException("입력 형식이 잘못되었습니다.");
        }
    }

    private void validBasicFormula(String formula) {
        String basicRegExp = "(\\d+([,:]\\d+)*$)?"; //TODO : 우선 빈칸도 포함
        if (!Pattern.matches(basicRegExp, formula)) {
            throw new IllegalArgumentException("입력 형식이 잘못되었습니다.");
        }
    }

    private String extractSeparator(String formula) {
        if (formula.isBlank()) {
            return "";
        }

        if (formula.startsWith("//")) { // 커스텀 형식일 경우
            int numberIdx = formula.indexOf("\\n");
            return formula.substring(2, numberIdx);
        }
        return ":|,";
    }

    private String extractFormula(String formula) {
        if (formula.isBlank()) {
            return "0";
        }

        if (formula.startsWith("//")) { // 커스텀 형식일 경우
            int numberIdx = formula.indexOf("\\n");
            return formula.substring(numberIdx + 2);
        }
        return formula;
    }

    //커스텀 수식 검증
    private void validCustomFormulaDetail(String formula, String separator) {
        String regExp = "\\d+(" + Pattern.quote(separator) + "\\d+)*$";
        if (!Pattern.matches(regExp, formula)) {
            throw new IllegalArgumentException("입력 형식이 잘못되었습니다.");
        }
    }

    public String[] toNumbers() {
        if (":|,".equals(separator)) {
            return formula.split(separator);
        }
        return formula.split(Pattern.quote(separator));
    }
}
