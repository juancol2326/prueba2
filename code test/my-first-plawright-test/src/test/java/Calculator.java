import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Calculator {

    private int sum(int number1, int number2){
        return number1 + number2;
    }

    private int res(int number1, int number2){
        return number1 - number2;
    }

    private int mult(int number1, int number2){
        return number1 * number2;
    }

    private int division(int number1, int number2){
        return number1 / number2;
    }

    @Test
    void verifySum(){
        int number1 = 3;
        int number2 = 4;
        int result = 7;
        Assertions.assertEquals(sum(number1, number2), result, "la suma no es correcta");
    }
    @Test
    void verifyRes(){
        int number1 = 8;
        int number2 = 2;
        int result = 6;
        Assertions.assertEquals(res(number1, number2), result, "la resta no es correcta");
    }
    @Test
    void verifyMult(){
        int number1 = 4;
        int number2 = 9;
        int result = 36;
        Assertions.assertEquals(mult(number1, number2), result,"la multiplicacion no es correcta");
    }
    @Test
    void verifyDivision(){
        int number1 = 30;
        int number2 = 15;
        int result = 2;
        Assertions.assertEquals(division(number1, number2), result, "la division es incorrecta");
    }



}
