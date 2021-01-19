class main {
    public static void main(String[] args) {

        Board game = new Board();

//        System.out.println("The coordinates for this game: ");
//        game.printCoordinates();

//        System.out.println("Your game board:");
        game.printBoard();

        while (game.gameResult().equals("Game not finished")) {
            game.addMove();
            game.printBoard();
        }
        System.out.println(game.gameResult());
    }
}