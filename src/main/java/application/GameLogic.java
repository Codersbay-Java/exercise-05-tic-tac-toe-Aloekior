package application;

import java.util.Random;
import static application.MainWithClasses.*;

public class GameLogic {
    private static Random rand = new Random();

    public static void Logic() {
        System.out.println("---Welcome to TIC TAC TOE---" + "\n");
        int player2Selection = Player.selectPlayer2();
        board.printBoard();
        while (currentRound < (board.boardSize * board.boardSize)) {
            if (player2Selection == 0) {
                Player.playerEntry();
            } else { // AI Game
                if (currentPlayer == 1) {
                    Player.playerEntry();
                } else {
                    aiMove();
                }
            }
            board.printBoard();
            currentRound++;
            winCheck();
            if (winner != 0) { // winCheck will return BOARD_SIZE or BOARD_SIZE * 2 if there is a winner
                break;
            }
        }
        conclusion();
    }

    private static void aiMove() {
        int row;
        int col;

        System.out.println("AI move:");

        do {
            row = rand.nextInt(0, board.boardSize);
            col = rand.nextInt(0, board.boardSize);
        } while (board.board[row][col] != 0);
        board.board[row][col] = currentPlayer;
        currentPlayer = 1;
    }

    private static void winCheck() {
        winner = board.sumDiag(); // count diag sum
        if (winner == 0) { // if diag sum returns 0
            winner = board.sumRow(); // count row sum
            if (winner == 0) { // if row sum returns 0
                winner = board.sumCol(); // count col sum
            }
        }
    }

    private static void conclusion() {
        if (winner == 1) {
            System.out.println("Player 1 wins!");
        } else if (winner == -1) {
            System.out.println("Player 2 wins!");
        } else {
            System.out.println("The game tied.");
        }
    }
}