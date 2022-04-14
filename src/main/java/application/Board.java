package application;

public class Board {
    public static int boardSize;
    public static int[][] board;


    public Board() {
        boardSize = 3;
        createBoard();
    }

    public Board(int boardSize) {
        this.boardSize = boardSize;
        createBoard();
    }

    private void createBoard() {
        board = new int[boardSize][boardSize];
    }

    public void printBoard() {
        for (int i = 0; i < boardSize; i++) {
            System.out.print("|"); // first | in a row
            for (int j = 0; j < boardSize; j++) {
                switch (board[i][j]) {
                    case 0: // cell is free
                        System.out.print("   |");
                        break;
                    case 1: // cell occupied by player 1
                        System.out.print(" X |");
                        break;
                    case -1: // cell occupied by player 2
                        System.out.print(" O |");
                        break;
                }
            }
            System.out.println(); // new row
        }
        System.out.println();
    }

    public static int sumDiag() {
        int left = 0, right = 0, count = board.length - 1;
        for (int i = 0; i < board.length; i++) {
            left = left + board[i][i];
            right = right + board[i][count];
            count--;
        }
        if (left == boardSize || right == boardSize) { // if diag sum = 1 * board.length, Player 1 wins
            return 1;
        } else if (left == boardSize * -1 || right == boardSize * -1) { // if diag sum = 2 * board.length, Player 2 wins
            return -1;
        }
        return 0;
    }

    public static int sumRow() {
        int sum;
        for (int i = 0; i < board.length; i++) {
            sum = 0;
            for (int j = 0; j < board.length; j++) {
                sum = sum + board[i][j];
            }
            if (sum == boardSize) { // if row sum = 3, Player 1 wins
                return 1;
            } else if (sum == boardSize * -1) { // if row sum = 6, Player 2 wins
                return -1;
            }
        }
        return 0;
    }

    public static int sumCol() {
        int sum;
        for (int j = 0; j < board.length; j++) {
            sum = 0;
            for (int i = 0; i < board.length; i++) {
                sum = sum + board[i][j];
            }
            if (sum == boardSize) { // if col sum = 3, Player 1 wins
                return 1;
            } else if (sum == boardSize * -1) { // if col sum = 6, Player 2 wins
                return -1;
            }
        }
        return 0;
    }
}