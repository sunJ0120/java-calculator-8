/**
 * 클래스 이름: Formula
 * <p>
 * 버전 정보: 1.1
 * <p>
 * 날짜: 2025-10-16
 * <p>
 * 저작권 주의: Copyright (c) 2025 sspur
 */
package calculator.domain;

import java.util.regex.Pattern;

public class Formula {
    private static final String CUSTOM_PREFIX = "//";
    private static final String CUSTOM_DELIMITER = "\\n";
    private static final String BASIC_SEPARATOR = ":|,";
    private static final String BASIC_REGEX = "\\d+([,:]\\d+)*$";
    private static final String BLANK_FORMULA_VALUE = "0";
    private static final String BLANK_SEPARATOR = "";
    private static final int CUSTOM_PREFIX_LENGTH = CUSTOM_PREFIX.length();
    private static final int CUSTOM_DELIMITER_LENGTH = CUSTOM_DELIMITER.length();

    private final String separator;    // 구분자
    private final String formula;    //수식

    public Formula(String formula) {
        boolean isCustom = formula.startsWith(CUSTOM_PREFIX);
        validation(formula, isCustom);    // 입력받은 수식 검증
        this.separator = extractSeparator(formula, isCustom);
        this.formula = extractFormula(formula, isCustom);

        if (isCustom) {
            validateCustomFormulaDetail(this.formula, this.separator);
        }
    }

    private void validation(String formula, boolean isCustom) {
        if (formula.isBlank()) {    // 빈 칸의 경우는 통과
            return;
        }
        if (isCustom) {
            validateCustomFormula(formula);    // 커스텀 형식 검증
        } else {
            validateBasicFormula(formula);    // 기본 형식 검증
        }
    }

    private void validateCustomFormula(String formula) {
        // 구분자가 있는지, 개행 문자가 있는지
        int endInd = formula.indexOf(CUSTOM_DELIMITER);
        if (endInd == -1 || endInd == CUSTOM_PREFIX_LENGTH) {    // \n이 없거나, 정의한 구분자가 없을 때
            throw new IllegalArgumentException("입력 형식이 잘못되었습니다.");
        }
    }

    private void validateBasicFormula(String formula) {
        if (!Pattern.matches(BASIC_REGEX, formula)) {
            throw new IllegalArgumentException("입력 형식이 잘못되었습니다.");
        }
    }

    private String extractSeparator(String formula, boolean isCustom) {
        if (formula.isBlank()) {
            return BLANK_SEPARATOR;
        }

        if (isCustom) {    // 커스텀 형식일 경우
            int numberIdx = formula.indexOf(CUSTOM_DELIMITER);
            return formula.substring(CUSTOM_PREFIX_LENGTH, numberIdx);
        }
        return BASIC_SEPARATOR;
    }

    private String extractFormula(String formula, boolean isCustom) {
        if (formula.isBlank()) {
            return BLANK_FORMULA_VALUE;
        }

        if (isCustom) {    // 커스텀 형식일 경우
            int numberIdx = formula.indexOf(CUSTOM_DELIMITER);
            return formula.substring(numberIdx + CUSTOM_DELIMITER_LENGTH);
        }
        return formula;
    }

    private void validateCustomFormulaDetail(String formula, String separator) {    // 커스텀 수식 검증
        String regExp = "\\d+(" + Pattern.quote(separator) + "\\d+)*$";
        if (!Pattern.matches(regExp, formula)) {
            throw new IllegalArgumentException("입력 형식이 잘못되었습니다.");
        }
    }

    public String[] toNumbers() {
        if (BASIC_SEPARATOR.equals(separator)) {    // 구분자가 , or : 인 일반 수식일 경우
            return formula.split(separator);
        }
        return formula.split(Pattern.quote(separator));
    }
}
