package calculator.domain;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 클래스 이름: Formula
 * <p>
 * 버전 정보: 1.2
 * <p>
 * 날짜: 2025-10-19
 */
public class Formula {
    private static final String CUSTOM_PREFIX_REGEX = "^//(.+?|\\\\n)\\\\n";    // 개행이 구분자인 경우 추가
    private static final String BASIC_SEPARATOR = ":|,";
    private static final String BASIC_REGEX = "-?\\d+([,:]-?\\d+)*$";
    private static final String BLANK_FORMULA_VALUE = "";
    private static final String BLANK_SEPARATOR = "";
    private static final Pattern CUSTOM_PATTERN = Pattern.compile(CUSTOM_PREFIX_REGEX);
    private static final Pattern BASIC_PATTERN = Pattern.compile(BASIC_REGEX);

    private final String separator;    // 구분자
    private final String formula;    //수식

    public Formula(String formula) {
        Matcher customMatcher = CUSTOM_PATTERN.matcher(formula);
        boolean isCustom = customMatcher.find();

        validation(formula, isCustom);    // 입력받은 수식 검증
        this.separator = extractSeparator(formula, isCustom, customMatcher);
        this.formula = extractFormula(formula, isCustom, customMatcher);

        if (isCustom) {
            validateCustomFormulaDetail(this.formula, this.separator);
        }
    }

    private void validation(String formula, boolean isCustom) {
        if (formula.isBlank()) {    // 빈 칸의 경우는 통과
            return;
        }
        if (isCustom || BASIC_PATTERN.matcher(formula).matches()) {
            return;
        }
        throw new IllegalArgumentException("입력 형식이 잘못되었습니다.");
    }

    private String extractSeparator(String formula, boolean isCustom, Matcher customMatcher) {
        if (formula.isBlank()) {
            return BLANK_SEPARATOR;
        }
        if (isCustom) {
            return customMatcher.group(1);
        }
        return BASIC_SEPARATOR;
    }

    private String extractFormula(String formula, boolean isCustom, Matcher customMatcher) {
        if (formula.isBlank()) {
            return BLANK_FORMULA_VALUE;
        }
        if (isCustom) {
            return customMatcher.replaceFirst("");
        }
        return formula;
    }

    private void validateCustomFormulaDetail(String formula, String separator) {    // 커스텀 수식 검증
        String regExp = "-?\\d+(" + Pattern.quote(separator) + "-?\\d+)*$";
        if (!Pattern.matches(regExp, formula)) {
            throw new IllegalArgumentException("입력 형식이 잘못되었습니다.");
        }
    }

    public String[] toNumbers() {
        if (BLANK_FORMULA_VALUE.equals(formula)) {    // 빈 입력 체크 추가
            return new String[0];
        }
        if (BASIC_SEPARATOR.equals(separator)) {    // 구분자가 , or : 인 일반 수식일 경우
            return formula.split(separator);
        }
        return formula.split(Pattern.quote(separator));
    }
}
