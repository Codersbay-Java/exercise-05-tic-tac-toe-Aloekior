package application;

import java.util.Scanner;

public class MainWithClasses {
    public static Scanner scanner = new Scanner(System.in);
    public static Board board = new Board();
    public static int currentPlayer = 1;
    public static int currentRound = 0;
    public static int winner = 0;

    public static void main(String[] args) {

        System.out.println("---Welcome to TIC TAC TOE---" + "\n");

        GameLogic.Logic();
    }
}