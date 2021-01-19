class main {
    public static void main(String[] args) {

        System.out.println("The board with 2 rows and 8 columns");
        Board testBoard = new Board(2, 8);
        testBoard.printBoard();
        System.out.println("The board with 8 rows and 2 columns");
        Board testBoard1 = new Board(8,2);
        testBoard1.printBoard();

        TicTacToe game = new TicTacToe();
        System.out.println("Tic-Tac-Toe game started");
        System.out.println("The coordinates for this game: ");
        game.printCoordinates();

        System.out.println("Your game board:");
        game.printBoard();

        while (game.gameResult().equals("Game not finished")) {
            game.addMove();
            game.printBoard();
        }
        System.out.println(game.gameResult());
    }
}