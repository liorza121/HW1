import java.util.Locale;

public class Action {
    private Tile tile;
    private Direction dir;

    public Action(Tile tile, Direction dir){
        this.tile = tile;
        this.dir = dir;
    }

    /**
     *
     * @return - the action toString per the requested format
     */
    @Override
    public String toString(){
        return "Move " + tile + " " + dir.toString().toLowerCase(Locale.ROOT);
    }

    /**
     *
     * @return - the tile the action moves
     */
    public Tile getTile(){
        return tile;
    }

    /**
     *
     * @return - the direction of the action
     */
    public Direction getDir() {
        return dir;
    }
}