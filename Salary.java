import java.util.Scanner;

public class Salary {
	public static void main (String[] args)
	{
		Scanner keyboard = new Scanner(System.in);

		System.out.print("Hello, what is your name? ");
		String yourname = keyboard.next();

		System.out.print("Hi " +yourname+ "! How old are you?");
		int yourage = keyboard.nextInt();

		System.out.println("So your'e " +yourage+ ", eh? That's not old at all!");
		System.out.print("How much do you make, " +yourname+ "?");
		float yoursalary = keyboard.nextFloat();

		System.out.println( yoursalary + "! I hope that's per hour and not per year! LOL!");
	}
}
