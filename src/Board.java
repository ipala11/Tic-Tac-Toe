import java.util.ArrayList;
import java.util.Scanner;

public class Board {
    private final Scanner scan = new Scanner(System.in);
    private char[][] board;
    private final int columns;
    private final int rows;
    private static int moves = 1;
    private static int xMoves = 0;
    private static int oMoves = 0;

    public Board (int columns, int rows) {
        this.columns = columns;
        this.rows = rows;
        this.board = makeBoard();
    }

    public Board() {
        this(3, 3);
    }

    public void addMove() {
        moveLoop:
        while (true) {
            System.out.println("Enter the coordinates: ");
            String input = scan.nextLine();
            if (input.length() != 3) {
                System.out.println("Enter 2 numbers!");
                continue;
            }

            int[] coord = new int[2];
            Scanner coordSc = new Scanner(input);
            int i = 0;
            while (coordSc.hasNext()) {
                if (!(coordSc.hasNextInt())) {
                    System.out.println("You should enter numbers!");
                    continue moveLoop;
                }
                coord[i] = coordSc.nextInt();
                i++;
            }
            int column = coord[0];
            int row = coord[1];

            if (column > this.board.length || column < 1 ||
                    row > this.board[0].length || row < 1) {
                System.out.println("Coordinates should be from 1 to 3!");
                continue;
            }

            if (!(this.board[this.board[0].length - row][column - 1] == '_' || this.board[this.board[0].length - row][column - 1] == ' ')) {
                System.out.println("This cell is occupied! Choose another one!");
                continue;
            }
            if (moves % 2 == 0) {
                board[this.board.length - row][column - 1] = 'O';
                oMoves++;
            } else {
                board[this.board.length - row][column - 1] = 'X';
                xMoves++;
            }
            moves++;
            break;
        }
    }

    public String gameResult () {
        if (!isPossible()) {
            return ("Impossible");
        }

        //Makes a list of all possible winners
        ArrayList<Character> winners = getWinners();

        if (winners.isEmpty()) {
            if (getEmpty() == 0) {
                return ("Draw");
            }
            return ("Game not finished");

            //Checks if all winner possibilities match
        } else if (winners.size() > 1) {
            for (int i = 0; i < winners.size() - 1; i++) {
                if (winners.get(i) != winners.get(i + 1)) {
                    return ("Impossible");
                }
            }
        }
        return (winners.get(0) + " wins");
    }

    private ArrayList<Character> getWinners () {
        ArrayList<Character> winners = new ArrayList <> ();

        for (int i = 0; i < this.board.length; i++) {
            if (this.board[i][0] == this.board[i][1] && this.board[i][1] == this.board [i][2]) {
                //Checks rows
                winners.add(this.board[i][0]);
            } else if (this.board[0][i] == this.board[1][i] && this.board[1][i] == this.board [2][i]) {
                //Checks columns
                winners.add(this.board[0][i]);
            }
        }
        if (this.board[0][0] == this.board[1][1] && this.board[1][1] == this.board[2][2] ||
                this.board[0][2] == this.board[1][1] && this.board[1][1] == this.board[2][0]) {
            //Checks diagonal
            winners.add(this.board[1][1]);
        }
        while (winners.contains(' ')) {
            winners.remove((Character) ' ');
        }
        return winners;
    }

    private boolean isPossible() {
        //It is not possible for the difference to be more than 1
        return xMoves - oMoves <= 1 && oMoves - xMoves <= 1;
    }

    private int getEmpty() {
        int empty = 0;

        for (int i = 0; i < this.board.length; i++) {
            for (int j = 0; j < this.board[i].length; j++) {
                if (this.board[i][j] == ' ') {
                    empty++;
                }
            }
        }
        return empty;
    }

    private char[][] makeBoard() {
        char[] chars = new char[this.columns * this.rows];
        for (int i = 0; i < this.columns * this.rows; i++) {
            chars[i] = ' ';
        }

        char[][] board = new char[this.columns][this.rows];

        for (int i = 0; i < this.columns; i++) {
            for (int j = 0; j < this.rows; j++) {
                int index = (i * this.columns) + (j);
                board[i][j] = chars[index];
            }
        }
        return board;
    }

    public void printBoard() {
        System.out.println("---------");
        for (int i = 0; i < this.board.length; i++) {
            System.out.print("| ");
            for (int j = 0; j < this.board[i].length; j++) {
                System.out.print(this.board[i][j] + " ");
            }
            System.out.print("|" + '\n');
        }
        System.out.println("---------");
    }

    public void printCoordinates () {
        String[][] coordinates = {
                {"(1, 3)", "(2, 3)", "(3, 3)"},
                {"(1, 2)", "(2, 2)", "(3, 2)"},
                {"(1, 1)", "(2, 1)", "(3, 1)"}
        };
        System.out.println("-----------------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(coordinates[i][j] + " ");
            }
            System.out.print("|" + '\n');
        }
        System.out.println("-----------------------");
    }
}