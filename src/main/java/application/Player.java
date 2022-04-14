package application;

import java.util.Scanner;
import static application.GameLogic.*;


public class Player {
    public static Scanner scanner = new Scanner(System.in);

    public static int selectPlayer2() {
        System.out.println("Do you want to play against another player? (yes / no)");
        if (scanner.nextLine().equalsIgnoreCase("yes")) {
            return 0;
        } else if (scanner.nextLine().equalsIgnoreCase("no")) {
            return 1;
        }
        return selectPlayer2();
    }

    public static void playerEntry() {
        int row = 0, col = 0, output;
        if (currentPlayer == 1) {
            System.out.println("Player 1 choose a row and column.");
        } else if (currentPlayer == -1) {
            System.out.println("Player 2 choose a row and column.");
        }


        while (row < 1 || row > board.boardSize) {
            System.out.print("Please enter a row (between 1 and " + board.boardSize + "): ");
            row = scanner.nextInt();
        }
        while (col < 1 || col > board.boardSize) {
            System.out.print("Please enter a column (between 1 and " + board.boardSize + "): ");
            col = scanner.nextInt();
        }
        if (board.board[row - 1][col - 1] == 0) {
            board.board[row - 1][col - 1] = currentPlayer;
            if (currentPlayer == 1) {
                currentPlayer = -1;
            } else {
                currentPlayer = 1;
            }
        } else {
            if (board.board[col - 1][row - 1] == -1) {
                output = 2;
            } else {
                output = 1;
            }
            System.out.println("Cell already occupied by player " + output);
        }
        System.out.println();
    }
}