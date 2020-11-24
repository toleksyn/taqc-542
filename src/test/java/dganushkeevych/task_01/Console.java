package dganushkeevych.task_01;

/*
 *  Class to work with console
 */

import java.util.List;
import java.util.Scanner;

public class Console {
	private Scanner scanner = null;
	public Console() {
		scanner = new Scanner(System.in);
	}

	public int read(String message) {
		System.out.print(message);
		return scanner.nextInt();
	}
	
	public void fillList(List<Integer> list, int listSize) {
		for (int j = 0; j < listSize; j++) {
			int temporaryNumber = readNaturalNumbers("a =", 1);
			list.add(temporaryNumber);
		}
	}
	
	public int readNaturalNumbers(String msg, int limit) {
		int number = read(msg);
		while(number < limit)
		{
			number = read("Incorrect! " + msg);
		}
		return number;
	}

	public void write(String message) {
		System.out.print(message);
	}

	public void write(String message, int result) {
		System.out.print(message + result + "\n");
	}

	public void write(PythagoreanTriple t) {
		System.out.print(t);
	}

	public void close() {
		scanner.close();
	}
}
