public class ConnectFour implements BoardGame{
    private int[][] board;
    private int currentPlayer;
    private Position[] winningPosition;

    @Override
    public void newGame() {
        board = new int[6][7];
        currentPlayer = 1 + (int)(Math.random()*2);
        winningPosition = new Position[4];
    }

    @Override
    public boolean gameOver() {
        return false;
    }

    @Override
    public int getWinner() {
        return 0;
    }

    @Override
    public Position[] getWinningPositions() {
        return new Position[0];
    }

    @Override
    public boolean columnFull(int column) {
        return board[0][column] != 0;
    }

    @Override
    public void play(int column) {
        if(!columnFull(column)) {
            // go to bottom row, work up to find open spot
            for(int r = 5; r >= 0; r--) {
                System.out.println("HELLO " + r);
                if(board[r][column]==0) {
                    board[r][column] = currentPlayer; // place a piece
                    currentPlayer = (currentPlayer == 1) ? 2 : 1;
                    return;
                }
            }
        }
    }

    @Override
    public int[][] getBoard() {
        return board;
    }
}
