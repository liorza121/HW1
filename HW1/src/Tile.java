public class Tile {

    private final int value;
    private final int wanted_row;
    private final int wanted_col;


    public Tile(int value, int wanted_row, int wanted_col){
        this.value = value;
        this.wanted_row = wanted_row;
        this.wanted_col = wanted_col;

    }

    /**
     *
     * @return - returns the value of the tile as a string
     */
    @Override
    public String toString(){
        return String.valueOf(this.value);
    }

    /**
     *
     * @param other - the other tile to be checked
     * @return - returns wether or not the tiles are equal
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Tile)) {
            return false;
        }
        Tile tile = (Tile) other;
        return value == tile.value;
    }

    /**
     *
     * @return - changes the hashcode to the value of the tile
     */
    @Override
    public int hashCode() {
        return Integer.hashCode(value);
    }

    /**
     *
     * @return - returns the value of the tile
     */
    public int getValue(){
        return value;
    }

    /**
     *
     * @return - returns the desired column of the tile
     */
    public int getWanted_col() {
        return wanted_col;
    }

    /**
     *
     * @return - returns the desired row of the tile
     */
    public int getWanted_row() {
        return wanted_row;
    }
}