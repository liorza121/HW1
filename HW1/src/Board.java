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
}
