/**
 * Class for Position
 * @author 24scurria
 * @version 5/18/23
 */
public class Position {
    private int col;
    private int row;

    /**
     * creates a Position object
     * @param row
     * @param col
     */
    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }

    /**
     * getter column
     * @return int column
     */
    public int getCol() {
        return col;
    }

    /**
     * getter row
     * @return int row
     */
    public int getRow() {
        return row;
    }

    /**
     * setter column
     * @param col
     */
    public void setCol(int col) {
        this.col = col;
    }

    /**
     * setter row
     * @param row
     */
    public void setRow(int row) {
        this.row = row;
    }

    /**
     * equals method for Position
     * @param other
     * @return whether the Positions are equal
     */
    public boolean equals(Object other) {
        if(other instanceof Position) {
            Position temp = (Position)other;
            return this.row == temp.row && this.col == temp.col;
        }
        return false;
    }
}
