
import java.util.Arrays;

public class Board {

    private Tile tiles[][];
    private final int length;
    private final int width;
    private int min (int a, int b){
        if (a < b)
            return a;
        return b;
    }
    private int nextDelimiter(String str){
        int indexOfSpace = str.indexOf(" ");
        int indexOfEndl = str.indexOf("|");
        if (indexOfSpace < 0)
            return indexOfEndl;
        if (indexOfEndl < 0)
            return  indexOfSpace;
        return min(indexOfSpace, indexOfEndl);
    }
    private int[][] createMatfromStr(String str){
        int mat[][] = new int[length][width];
        int counter = 0;
        while(!str.equals("")){
            int num;
            int nextDelim = nextDelimiter(str);
            String symbol = "";
            if(nextDelim != -1)
                symbol = str.substring(0,nextDelim);
            else
                symbol = str.substring(0);
            if (!symbol.equals("_"))
                num = Integer.parseInt(symbol);
            else
                num = 0; //0 to be defined as '_'
            mat[counter / width][counter % width] = num;
            if (nextDelim == -1)
                str = "";
            else
                str = str.substring(nextDelimiter(str)+1);
            counter++;
        }
        return mat;
    }
    public Board(String tileList){
        int value;
        this.length = getLength(tileList);
        this.width = getWidth(tileList);
        tiles = new Tile[length][width];
        int values[][] = createMatfromStr(tileList);
        for(int i = 0; i < length; i++){
            for(int j = 0; j < width; j++) {
                if (values[i][j] == 0) {
                    tiles[i][j] = null;
                } else {
                    value = values[i][j];
                    tiles[i][j] = new Tile(value, getExpectedRow(value), getExpectedCol(value));
                }
            }
        }
    }
    public Board(Tile tilesParam[][]){
        length = tilesParam.length;
        width = tilesParam[0].length;
        tiles = tilesParam;
    }
    @Override
    public String toString(){
        String ret = "";
        for(int i = 0; i < length; i++){
            for(int j = 0; j < width; j++){
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

    private int getLength(String tileList){
        int count = 1;
        for(int i = 0; i < tileList.length(); i++){
            if (tileList.charAt(i) == '|'){
                count++;
            }
        }
        return count;
    }
    private int getWidth(String tileList) {
        int count = 1;
        for(int i = 0; i < tileList.length() && tileList.charAt(i) != '|'; i++) {
            if(tileList.charAt(i) == ' ')
                count++;
        }
        return count;
    }
    private int getExpectedValue(int i, int j){
        return i * width + j + 1;
    }
    public int getCorrectTiles(){
        int count = 0;
        for(int i = 0; i < length; i++){
            for(int j = 0; j < width; j++){
                int expectedValue = getExpectedValue(i, j);
                if(tiles[i][j] == null){
                    if(expectedValue == length * width)
                        count++;
                }
                else if(tiles[i][j].getValue() == expectedValue)
                    count++;
            }
        }
        return count;
    }
    public boolean tileInPlace(int row, int col){
        int expectedValue = row * tiles[0].length + col + 1;
        if (!(row == tiles.length - 1  && col == tiles[0].length - 1)) {
            if (tiles[row][col] == null) {
                return false;
            } else {
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

    public int getLength() {
        return length;
    }

    public int getWidth(){
        return width;
    }

    /**
     *
     * @param row - tile's row
     * @param col - tile's col
     * @return - distance of given tile from destination (see implementation for calculation)
     */

    public int getDistance(int row, int col){
        int value = 0;
        Tile tile = tiles[row][col];
        if (tile != null)
            value  = tile.getValue();
        if (tile == null)
            return ((length - 1) - row) + ((width - 1) - col);
        else
            return absolute(tile.getWanted_row() - row) + absolute(tile.getWanted_col() - col);
    }

    private int absolute(int number){
        if (number >= 0)
            return number;
        return -number;
    }

    private int getExpectedRow(int value){
        return (value - 1) / width;
    }
    private int getExpectedCol(int value){
        return (value -1) % width;
    }

    public int getRelated(){
        int count = 1;
        for(int i=0; i < length - 1; i++){
            for(int j=0; j < width - 1; j++){
                Tile t1 = tiles[i][j];
                Tile t2 = tiles[i + 1][j];
                Tile t3 = tiles[i][j + 1];
                if (t1 != null) {
                    if (t2 != null && absolute(t1.getValue() - t2.getValue()) == 1)
                        count++;
                    if (t3 != null && absolute(t1.getValue() - t3.getValue()) == 1)
                        count++;
                }
            }
        }
        return count;
    }
}