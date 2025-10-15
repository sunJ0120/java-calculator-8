package calculator.domain;

public class StringCalculator {
    private final String[] numbers; //받은 숫자들

    public StringCalculator(String[] numbers) {
        this.numbers = numbers;
    }

    public int sum(){
        int result = 0;
        for(String number : numbers){
            result += Integer.parseInt(number);
        }
        return result;
    }
}
