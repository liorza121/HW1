import java.util.Arrays;

public class Board {

    private Tile tiles[][];
    private final int length;
    private final int width;


    public Board(String tileList){
        this.length = getLength(tileList);
        this.width = getWidth(tileList);
        tiles = new Tile[length][width];
        String value;
        for(int i = 0; i < length; i++){
            for(int j = 0; j < width; j++) {
                value = tileList.substring((i * (width * 2 + 1) + j * 2),(i * (width * 2 + 1) + j * 2) + 1);
                if (value.equals("_")) {
                    tiles[i][j] = null;
                } else {
                    tiles[i][j] = new Tile(Integer.parseInt(value));
                }
            }
        }
    }

    @Override
    public String toString(){
        String ret = "";
        for(int i = 0; i < length; i++){
            for(int j = 0; j < length; j++){
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
        int count = 0;
        for(int i = 0; i < tileList.length(); i++){
            if (tileList.charAt(i) == '|'){
                count++;
            }
        }
        return count + 1;
    }

    private int getWidth(String tileList){
        int count = 0;
        for(int i = 0; (i < tileList.length()) && (tileList.charAt(i) != '|'); i++){
            count++;
        }
        return (count+1)/2;
    }
}
