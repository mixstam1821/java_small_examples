import java.util.Scanner;

public class BMICalc {
	public static void main (String[] args)
	{
		Scanner keyboard = new Scanner(System.in);

		System.out.print("Enter your height in m: ");
		double height = keyboard.nextDouble();

		System.out.print("Enter your weight in kg: ");
		double weight = keyboard.nextDouble();

		double result = weight / Math.pow(height, 2.0);

		System.out.println("Your BMI is "+result+".");
	}
}
