package application;

import java.util.Scanner;

public class Main {
    public static final int BOARD_SIZE = 3;
    public static int[][] board = new int[BOARD_SIZE][BOARD_SIZE];
    public static int currentPlayer = 1;
    public static int currentRound = 0;
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int winner = 0;

        System.out.println("---Welcome to TIC TAC TOE---" + "\n");
        printBoard();
        while (currentRound < 5) { // there can be no winner before 5 cells have been filled
            playerEntry();
            printBoard();
        }
        while (currentRound < 9) { // check for winner on every round, quit after 9. round
            winner = winCheck();
            if (winner > 0) { // winCheck will return 3 or 6 if there is a winner
                break;
            }
            playerEntry();
            printBoard();
        }
        winner = winCheck();
        conclusion(winner);
    }

    public static void printBoard() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            System.out.print("|"); // first | in a row
            for (int j = 0; j < BOARD_SIZE; j++) {
                switch (board[i][j]) {
                    case 0: // cell is free
                        System.out.print("   |");
                        break;
                    case 1: // cell occupied by player 1
                        System.out.print(" X |");
                        break;
                    case 2: // cell occupied by player 2
                        System.out.print(" O |");
                        break;
                }
            }
            System.out.println(""); // new row
        }
        System.out.println("");
    }

    public static void playerEntry() {
        int row = 0, col = 0;
        System.out.println("Player " + currentPlayer + " choose a row and column.");

        while (row < 1 || row > BOARD_SIZE) { // repeat until Player enters valid row number
            System.out.print("Please enter a row (between 1 and " + BOARD_SIZE + "): ");
            row = scanner.nextInt();
        }
        while (col < 1 || col > BOARD_SIZE) { // repeat until Player enters valid col number
            System.out.print("Please enter a coloumn (between 1 and " + BOARD_SIZE + "): ");
            col = scanner.nextInt();
        }
        if (board[row - 1][col - 1] == 0) { // check if selected cell is free
            board[row - 1][col - 1] = currentPlayer; // write player to cell
            if (currentPlayer == 1) { // if current player was 1
                currentPlayer = 2; // set next player to 2
            } else {
                currentPlayer = 1; // set next player to 1
            }
            currentRound++;
        } else {
            System.out.println("Cell already occupied by player " + board[col - 1][row - 1]);
        }
        System.out.println();
    }

    public static int winCheck() {
        int winner = 0;
        winner = sumDiag(); // count diag sum
        if (winner == 0) { // if diag sum returns 0
            winner = sumRow(); // count row sum
            if (winner == 0) { // if row sum returns 0
                winner = sumCol(); // count col sum
            }
        }
        return winner; // return 0, 3 or 6
    }

    public static int sumDiag() {
        int left = 0, right = 0, count = board.length - 1;
        for (int i = 0; i < board.length; i++) {
            if (board[i][i] == 0) { // if cell is 0, set left diag sum to -10 to avoid 1 + 2 = 3
                left = -10;
            }
            if (board[i][count] == 0) { // if cell is 0, set right diag sum to -10 to avoid 1 + 2 = 3
                right = -10;
            }
            left = left + board[i][i];
            right = right + board[i][count];
            count--;
        }
        if (left == 3 || right == 3) { // if diag sum = 3, Player 1 wins
            return 3;
        } else if (right == 6 || right == 6) { // if diag sum = 6, Player 2 wins
            return 6;
        }
        return 0;
    }

    public static int sumRow() {
        int sum;
        for (int i = 0; i < board.length; i++) {
            sum = 0;
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] == 0) { // if cell is 0, go to next row to avoid 1 + 2 = 3
                    sum = 0;
                    break;
                }
                sum = sum + board[i][j];
            }
            if (sum == 3) { // if row sum = 3, Player 1 wins
                return 3;
            } else if (sum == 6) { // if row sum = 6, Player 2 wins
                return 6;
            }
        }
        return 0;
    }

    public static int sumCol() {
        int sum;
        for (int j = 0; j < board.length; j++) {
            sum = 0;
            for (int i = 0; i < board.length; i++) {
                if (board[i][j] == 0) { // if cell is 0, go to next col to avoid 1 + 2 = 3
                    sum = 0;
                    break;
                }
                sum = sum + board[i][j];
            }
            if (sum == 3) { // if col sum = 3, Player 1 wins
                return 3;
            } else if (sum == 6) { // if col sum = 6, Player 2 wins
                return 6;
            }
        }
        return 0;
    }

    public static void conclusion(int winner) {
        if (winner == 3) {
            System.out.println("Player 1 wins!");
        } else if (winner == 6) {
            System.out.println("Player 2 wins!");
        } else {
            System.out.println("The game tied.");
        }
    }
}