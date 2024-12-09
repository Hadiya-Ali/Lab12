package twitter;

public class SumDigits {
	public static int sumOfDigits(int num) {
        // in case there is a negative value, turn it to positive
        num = Math.abs(num);
        if (num == 0) {
        	//in case its 0 then it will return 0
            return 0;
        }

        return num % 10 + sumOfDigits(num / 10);
    }

}
