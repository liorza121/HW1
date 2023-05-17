
import java.util.Arrays;

public class Board {

    private Tile tiles[][];
    private final int size;

    public Board(String tileList){
        this.size = getSize(tileList);
        tiles = new Tile[size][size];
        String value;
        for(int i = 0; i < (size*size)*2; i += 2){
            value = tileList.substring(i, i+1);
            if (value.equals("_")){
                tiles[i / (size*2)][(i/2)%size] = null;
            }
            else {
                tiles[i / (size*2)][(i / 2) % size] = new Tile(Integer.parseInt(value));
            }
        }
    }
    public Board(Tile tilesParam[][]){
        size = tilesParam.length * tilesParam[0].length;
        tiles = tilesParam;
    }
    @Override
    public String toString(){
        String ret = "";
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                if(tiles[i][j] == null)
                    ret += "_|";
                else
                    ret += tiles[i][j] + "|";
            }
            ret += "\n";
        }
        return ret;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Board)) {
            return false;
        }
        Board board = (Board) other;
        return Arrays.deepEquals(tiles, board.tiles);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(tiles);
    }

    private int getSize(String tileList){
        int count = 0;
        for(int i = 0; i < tileList.length(); i++){
            if (tileList.charAt(i) == '|'){
                count++;
            }
        }
        return count + 1;
    }
    public int getCorrectTiles(){
        int count = 0;
        for(int i = 0; i < tiles.length; i++){
            for(int j = 0; j < tiles[0].length; j++){
                int expectedValue = i * tiles.length + j;
                if(tiles[i][j].getValue() == expectedValue)
                    count++;
            }
        }
        return count;
    }
    public boolean tileInPlace(int row, int col){
        int expectedValue = row * tiles[0].length + col;
        if (!(row == tiles.length && col == tiles[0].length)) {
            if (tiles[row][col] == null) {
                return false;
            }
            else{
                return tiles[row][col].getValue() == expectedValue;
            }
        }
        else{
            return tiles[row][col] == null;
        }
    }

    private int getTileRow(Tile tile) {
        for(int row = 0;row < tiles.length; row++){
            for(int col = 0; col < tiles[0].length; col++){
                if(tile == null){
                    if(tiles[row][col] == null)
                        return row;
                }
                else{
                    if (tiles[row][col] != null && tiles[row][col].equals(tile))
                        return row;
                }
            }
        }
        return 0;
    }
    private int getTileCol(Tile tile) {
        for(int row = 0;row < tiles.length; row++){
            for(int col = 0; col < tiles[0].length; col++){
                if(tile == null){
                    if(tiles[row][col] == null)
                        return col;
                }
                else{
                    if (tiles[row][col] != null && tiles[row][col].equals(tile))
                        return col;
                }
            }
        }
        return 0;
    }
    /**
     * @param dir - the direction to move
     * @return - The tile to be moved, null if illegal.
     */
    public Tile isDirectionLegal(Direction dir){
        int emptyRow = getTileRow(null); int emptyCol = getTileCol(null);
        return isMoveLegal(dir, emptyRow, emptyCol);
    }
    private boolean boundsCheck(int row, int col){
        return row >= 0 && row < tiles.length && col >= 0 && col < tiles[0].length;
    }

    /**
     * @param dir - direction to move
     * @param emptyRow - the row of the empty tile
     * @param emptyCol - the column of the empty tile
     * @return - return the source tile to move
     */
    private Tile isMoveLegal(Direction dir, int emptyRow, int emptyCol){
        int sourceRow = emptyRow; int sourceCol = emptyCol;
        switch (dir){
            case UP:
                sourceRow++;
                break;
            case DOWN:
                sourceRow--;
                break;
            case RIGHT:
                sourceCol--;
                break;
            case LEFT:
                sourceCol++;
                break;
        }
        if(boundsCheck(sourceRow, sourceCol))
            return tiles[sourceRow][sourceCol]; //return the moved tile
        return null; //if move is illegal
    }

    /**
     *
     * @param action - the action to be performed
     * @return - the board that would be received after the action
     */
    public Board result(Action action){
        int emptyRow = getTileRow(null); int emptyCol = getTileCol(null);
        int tileRow = getTileRow(action.getTile()); int tileCol = getTileCol(action.getTile());
        Tile tilesTemp[][] = new Tile[tiles.length][tiles[0].length];
        for(int i = 0; i < tiles.length; i++){
            for(int j = 0; j < tiles[0].length; j++){
                tilesTemp[i][j] = tiles[i][j];
            }
        }
        tilesTemp[emptyRow][emptyCol] = action.getTile();
        tilesTemp[tileRow][tileCol] = null;
        return new Board(tilesTemp);
    }
    public int getRowCount(){
        return tiles.length;
    }
    public int getColCount(){
        return tiles[0].length;
    }
}
