package gr.aueb.cf.ChristmasProjects;

import java.util.InputMismatchException;
import java.util.Scanner;

public class TrillizaGame {

    static char[][] arr = new char[3][3];

    public static void main(String[] args) {

        boolean win = false;
        int player = 1;
        char ch;
        Scanner sc = new Scanner(System.in);

        initializeArray();
        displayArray();
        System.out.println();

        while (!win) {
            int row = -1, col = -1; // Initialize to invalid values
            boolean validInput = false;

            // Input loop to ensure correct input
            while (!validInput) {
                try {
                    System.out.println();
                    System.out.println("Player" + player + ", enter row and column (0-2): ");
                    row = sc.nextInt();
                    col = sc.nextInt();

                    // Check if input is within valid range
                    if (validMove(row, col)) {
                        validInput = true; // Exit the loop if input is valid
                    } else {
                        System.out.println("Invalid move. The cell is either occupied or out of range. Please try again.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter integers only.");
                    sc.next(); // Clear invalid input
                }
            }

            if (player == 1) {
                ch = 'X';
            } else {
                ch = 'O';
            }
            arr[row][col] = ch;

            displayArray();
            if (winTheGame(ch)) {
                System.out.println();
                System.out.println("Player " + player + " wins the game!");
                win = true;
            } else if (arrayIsFull()) {
                System.out.println();
                System.out.println("The game ends in a draw.");
                win = true;
            } else {
                player = (player == 1) ? 2 : 1; // Switch players
            }
        }
    }

    public static void displayArray() {
        System.out.println("_____________");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(arr[i][j] + " | ");
            }
            System.out.println();
        }
        System.out.print("-------------");
    }

    public static void initializeArray() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                arr[i][j] = '-';
            }
        }
    }

    public static boolean arrayRows(char ch) {
        for (int i = 0; i < 3; i++) {
            if (arr[i][0] == ch && arr[i][1] == ch && arr[i][2] == ch) {
                return true;
            }
        }
        return false;
    }

    public static boolean arrayColumn(char ch) {
        for (int j = 0; j < 3; j++) {
            if (arr[0][j] == ch && arr[1][j] == ch && arr[2][j] == ch) {
                return true;
            }
        }
        return false;
    }

    public static boolean arrayDiagonals(char ch) {
        return (arr[0][0] == ch && arr[1][1] == ch && arr[2][2] == ch ||
                arr[0][2] == ch && arr[1][1] == ch && arr[2][0] == ch);
    }

    public static boolean winTheGame(char ch) {
        return (arrayRows(ch) || arrayColumn(ch) || arrayDiagonals(ch));
    }

    public static boolean validMove(int row, int col) {
        return (row >= 0 && row < 3 && col >= 0 && col < 3 && arr[row][col] == '-');
    }

    public static boolean arrayIsFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (arr[i][j] == '-') {
                    return false;
                }
            }
        }
        return true;
    }
}
