public class Tile {

    private final int value;
    private final int wanted_row;
    private final int wanted_col;


    public Tile(int value, int wanted_row, int wanted_col){
        this.value = value;
        this.wanted_row = wanted_row;
        this.wanted_col = wanted_col;

    }

    @Override
    public String toString(){
        return String.valueOf(this.value);
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Tile)) {
            return false;
        }
        Tile tile = (Tile) other;
        return value == tile.value;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(value);
    }
    public int getValue(){
        return value;
    }

    public int getWanted_col() {
        return wanted_col;
    }

    public int getWanted_row() {
        return wanted_row;
    }
}