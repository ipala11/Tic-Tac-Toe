public class Board {
    private char[][] board;
    private final int rows;
    private final int columns;

    public Board (int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.board = makeBoard();
    }

    private char[][] makeBoard() {
        char[][] board = new char[this.rows][this.columns];

        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                board[i][j] = ' ';
            }
        }
        return board;
    }

    public void printBoard() {
        for (int i = 0; i < this.columns * 3 - (this.columns - 3); i++) {
            System.out.print("-");
        }
        System.out.println();

        for (int i = 0; i < this.rows; i++) {
            System.out.print("| ");
            for (int j = 0; j < this.columns; j++) {
                System.out.print(this.board[i][j] + " ");
            }
            System.out.print("|" + '\n');
        }

        for (int i = 0; i < this.columns * 3 - (this.columns - 3); i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    public char[][] getBoard () {
        return this.board;
    }

}