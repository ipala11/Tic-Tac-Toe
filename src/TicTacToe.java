import java.util.ArrayList;
import java.util.Scanner;

public class TicTacToe extends Board {
    private final Scanner scan = new Scanner(System.in);
    private static int moves = 1;
    private static int xMoves = 0;
    private static int oMoves = 0;
    private char[][] board;

    public TicTacToe() {
        super(3, 3);
        this.board = getBoard();
    }

    public void addMove() {
        moveLoop:
        while (true) {
            char player;
            if (moves % 2 != 0) {
                player = 'X';
            } else {
                player = 'O';
            }
            System.out.println("Enter the coordinates for player " + player + " : ");
            String input = scan.nextLine();
            if (input.length() != 3) {
                System.out.println("Enter 2 numbers from 1 to 3! \n" + "Example: 1 1 \n");
                continue;
            }

            int[] coord = new int[2];
            Scanner coordSc = new Scanner(input);
            int i = 0;
            while (coordSc.hasNext()) {
                if (!(coordSc.hasNextInt())) {
                    System.out.println("You should enter numbers! \n" + "Example: 1 1 \n");
                    continue moveLoop;
                }
                coord[i] = coordSc.nextInt();
                i++;
            }
            int row = coord[0];
            int column = coord[1];

            if (column > 3 || column < 1 ||
                    row > 3 || row < 1) {
                System.out.println("Coordinates should be from 1 to 3!");
                continue;
            }

            if (!(this.board[3 - column][row - 1] == '_' || this.board[3 - column][row - 1] == ' ')) {
                System.out.println("This cell is occupied! Choose another one!");
                continue;
            }

            if (player == 'O') {
                oMoves++;
            } else {
                xMoves++;
            }
            this.board[3 - column][row - 1] = player;
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