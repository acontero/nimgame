import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * CS 780 [Artificial Intelligence] - Final Project: Nim
 * 
 * @author Jonathan Wong
 * @author Angelica Contero
 * @author Steven Jiminez
 * 
 */
public class Nim {

	static int stacks = 3;
	static int stacksRemaining;
	static int stackSize = 5;
	static int[] muffins;
	static String winner;
	static String[][] board;
	static Scanner scan;

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		buildGame();
		printIntro();
		runGame();

	} // main

	public static void runGame() {

		while (true) {

			playerOne();
			if (victory("Player 1"))
				break;
			playerTwo();
			if (victory("Player 2"))
				break;
		}

		System.out.println(winner + " has won the game!");
	} // runGame

	public static void playerOne() {
		int stackChoice = 0;
		int numMuffins = 0;
		System.out.println("Player 1's turn:");
		printBoard();
		while (true) {
			try {
				System.out.println("Select a stack: ");
				stackChoice = scan.nextInt();
				System.out.println("Choose your muffins: ");
				numMuffins = scan.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("Choice must be an integer.");
				scan.next();
				continue;
			}
			System.out.println(stackChoice);
		}
	} // playerOne

	public static void playerTwo() {
		System.out.println("Player 2's turn:");
		printBoard();
	} // playerTwo

	public static boolean victory(String name) {
		boolean b = true;
		for (int i = 0; i < muffins.length; i++)
			if (muffins[i] > 0)
				b = false;
		if (b) {
			winner = name;
		}
		return b;
	} // victoryCheck

	public static void printBoard() {
		for (int i = 0; i < stacks; i++)
			System.out.print(muffins[i] + " ");
		System.out.println();
	} // printBoard

	public static void printIntro() {
		System.out.println("Nim, version 1.0");
		System.out
				.println(" Written by Jonathan Wong, Angelica Contero, and STEVE!\n");
	} // printIntro

	public static void buildGame() {

		scan = new Scanner(System.in);
		stacksRemaining = stacks;
		muffins = new int[stacks];
		board = new String[stackSize][stacks];
		for (int i = 0; i < muffins.length; i++)
			muffins[i] = stackSize;

		for (int i = 0; i < stacks; i++)
			for (int j = stackSize - 1; j >= 0; j--) {
				board[j][i] = "[o] ";
			}

	} // buildGame

} // Nim
