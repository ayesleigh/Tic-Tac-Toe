package tictactoe;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static Character[][] grid = new Character[3][3];
    static boolean isGameOver = false;
    static boolean isDraw = false;
    static int currentMove = 1;
    static Character player;

    public static void main(String[] args) {
        initializeGrid();

        while (!isGameOver) {
            player = currentMove % 2 == 1 ? 'X' : 'O';
            System.out.printf("%s player's turn.\n", player);
            userMove();

            if (!isGameOver && currentMove == 10) {
                isDraw = true;
                break;
            }
        }

        if (isDraw) {
            System.out.println("Draw");
        } else {
            System.out.printf("%s wins!", player);
        }
    }

    static void initializeGrid() {
        System.out.println("Let's play a game of Tic Tac Toe!");
        for (int i = 0; i < 3; ++i) {
            Arrays.fill(grid[i], ' ');
        }
        printGrid();
    }

    static void printGrid() {
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }

    static void userMove() {
        System.out.println("Enter the coordinates of your move:");
        try {
            int x = scanner.nextInt();
            int y = scanner.nextInt();

            if (x < 1 || x > 3 || y < 1 || y > 3) {
                System.out.println("Coordinates should be from 1 to 3!");
                userMove();
            } else if (grid[x - 1][y - 1] != ' ') {
                System.out.println("This cell is occupied! Choose another one!");
                userMove();
            } else {
                grid[x - 1][y - 1] = player;
                currentMove++;
                printGrid();
                checkWinner(x - 1, y - 1);
            }
        } catch (Exception e) {
            scanner.nextLine();
            System.out.println("You should enter numbers!");
            userMove();
        }
    }

    static void checkWinner(int i, int j) {
        if (grid[0][j] != ' ' && grid[0][j] == grid[1][j] && grid[1][j] == grid[2][j]) {
            isGameOver = true;
        }
        if (grid[i][0] != ' ' && grid[i][0] == grid[i][1] && grid[i][1] == grid[i][2]) {
            isGameOver = true;
        }
        if (grid[0][0] != ' ' && grid[0][0] == grid[1][1] && grid[1][1] == grid[2][2]) {
            isGameOver = true;
        }
        if (grid[0][2] != ' ' && grid[0][2] == grid[1][1] && grid[1][1] == grid[2][0]) {
            isGameOver = true;
        }
    }
}
