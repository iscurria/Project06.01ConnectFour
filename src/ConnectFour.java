/**
 * public class for ConnectFour
 * @author 24scurria
 * @version 5/18/23
 * worked with Ryan, Sarah, and Riley
 */
public class ConnectFour implements BoardGame{
    private int[][] board;
    private int currentPlayer;
    private Position[] winningPosition;

    /**
     * Creates a ConnectFour game project
     */
    public ConnectFour() {
        newGame();
    }

    /**
     * Creates a game and sets the current player
     */
    @Override
    public void newGame() {
        board = new int[6][7];
        currentPlayer = 1 + (int)(Math.random()*2);
        winningPosition = new Position[4];
    }

    private int horizWin() {
        int count = 0;
        int player = 0;
        for(int r = 5; r >= 0; r--) {
            count = 0;
            for(int c = 0; c < 7; c++) {
                if(board[r][c] == 0) {
                    count = 0;
                    player = 0;
                }
                if(board[r][c] == 1) {
                    if(player == 1) {
                        count++;
                    }
                    else{
                        player = 1;
                        count = 1;
                    }
                }
                if(board[r][c]==2) {
                    if(player == 2) {
                        count++;
                    }
                    else{
                        player = 2;
                        count = 1;
                    }
                }
                if(count == 4) {
                    setWinningPositions(r, c, 1);
                    return player;
                }
            }
        }
        return 0;
    }

    private int vertWin() {
        int count = 0;
        int player = 0;
        for(int c = 0; c < 7; c++) {
            count = 0;
            for(int r = 5; r >= 0; r--) {
                if(board[r][c] == 0) {
                    count = 0;
                    player = 0;
                }
                if(board[r][c] == 1) {
                    if(player == 1) {
                        count++;
                    }
                    else{
                        player = 1;
                        count = 1;
                    }
                }
                if(board[r][c]==2) {
                    if(player == 2) {
                        count++;
                    }
                    else{
                        player = 2;
                        count = 1;
                    }
                }
                if(count == 4) {
                    setWinningPositions(r, c, 0);
                    return player;
                }
            }
        }
        return 0;
    }

    private void setWinningPositions(int r, int c, int method) {
        switch(method) {
            case 0 -> {
                winningPosition[0] = new Position(r, c);
                winningPosition[1] = new Position(r+1, c);
                winningPosition[2] = new Position(r+2, c);
                winningPosition[3] = new Position(r+3, c);
            }
            case 1 -> {
                winningPosition[0] = new Position(r, c);
                winningPosition[1] = new Position(r, c-1);
                winningPosition[2] = new Position(r, c-2);
                winningPosition[3] = new Position(r, c-3);
            }
            case 2 -> {
                winningPosition[0] = new Position(r, c);
                winningPosition[1] = new Position(r+1, c+1);
                winningPosition[2] = new Position(r+2, c+2);
                winningPosition[3] = new Position(r+3, c+3);
            }
            case 3 -> {
                winningPosition[0] = new Position(r, c);
                winningPosition[1] = new Position(r-1, c-1);
                winningPosition[2] = new Position(r-2, c-2);
                winningPosition[3] = new Position(r-3, c-3);
            }
            case 4 -> {
                winningPosition[0] = new Position(r, c);
                winningPosition[1] = new Position(r+1, c-1);
                winningPosition[2] = new Position(r+2, c-2);
                winningPosition[3] = new Position(r+3, c-3);
            }
            case 5 -> {
                winningPosition[0] = new Position(r, c);
                winningPosition[1] = new Position(r-1, c+1);
                winningPosition[2] = new Position(r-2, c+2);
                winningPosition[3] = new Position(r-3, c+3);
            }
        }
    }

    private int diagWin(){
        for(int r = 5; r >= 0; r--) {
            for (int c = 0; c < 7; c++) {
                if(r >= 3 && c <= 3){
                    if(board[r][c] == board[r-1][c+1] && board[r][c] == board[r-2][c+2] && board[r][c] == board[r-3][c+3] && board[r][c] != 0) {
                        setWinningPositions(r, c, 5);
                        return board[r][c];
                    }
                }
                if(r >= 3 && c >= 3){
                    if(board[r][c] == board[r-1][c-1] && board[r][c] == board[r-2][c-2] && board[r][c] == board[r-3][c-3] && board[r][c] != 0) {
                        setWinningPositions(r, c, 3);
                        return board[r][c];
                    }
                }
                if(r <= 2 && c <= 3){
                    if(board[r][c] == board[r+1][c+1] && board[r][c] == board[r+2][c+2] && board[r][c] == board[r+3][c+3] && board[r][c] != 0) {
                        setWinningPositions(r, c, 2);
                        return board[r][c];
                    }
                }
                if(r <= 2 && c >= 3){
                    if(board[r][c] == board[r+1][c-1] && board[r][c] == board[r+2][c-2] && board[r][c] == board[r+3][c-3] && board[r][c] != 0) {
                        setWinningPositions(r, c, 4);
                        return board[r][c];
                    }
                }
            }
        }
        return 0;
    }

    /**
     * determines if the game is over
     * @return boolean true if game over, false if not
     */
    @Override
    public boolean gameOver() {
        boolean go = true;
        for(int i = 0; i < 7; i++) {
            if(board[0][i] == 0) {
                go = false;
            }
        }
        return getWinner() !=0 || go;
    }

    /**
     * determines the winner of the game
     * @return int player who won
     */
    @Override
    public int getWinner() {
        return horizWin() > 0 ? horizWin() : vertWin() > 0 ? vertWin() : Math.max(diagWin(), 0);
    }

    /**
     * fills Positions with where the dots that created the winning row were
     * @return Position[]
     */
    @Override
    public Position[] getWinningPositions() {
        return winningPosition != null ? winningPosition : new Position[0];
    }

    /**
     * determines if a column is full
     * @param column the column number
     * @return whether the column is full
     */
    @Override
    public boolean columnFull(int column) {
        return board[0][column] != 0;
    }

    /**
     * plays the game
     * @param column the column number
     */
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

    /**
     * determines the current player
     * @return the int of the current player
     */
    public int getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * creates the connect four board
     * @return a 2d array list of ints board
     */
    @Override
    public int[][] getBoard() {
        return board;
    }
}
