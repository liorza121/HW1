import java.util.Locale;

public class Action {
    private Tile tile;
    private Direction dir;

    public Action(Tile tile, Direction dir){
        this.tile = tile;
        this.dir = dir;
    }

    @Override
    public String toString(){
        return "Move " + tile + " " + dir.toString().toLowerCase(Locale.ROOT);
    }
    public Tile getTile(){
        return tile;
    }
    public Direction getDir() {
        return dir;
    }
}
