public class State {
    private Board _board;
    public State(Board board){
        _board = board;
    }
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof State)) {
            return false;
        }
        State otherState = (State) other;
        return _board.equals(otherState._board);
    }
    public int getCorrectTiles(){
        return _board.getCorrectTiles();
    }
    /**
     * <p>
     *     this function checks if the current state is the goal state
     * </p>
     * @return boolean value, goal or not
     */
    public boolean isGoal(){
        for(int i = 0; i < _board.getRowCount(); i++){
            for(int j = 0; j < _board.getColCount(); j++){
                if(!_board.tileInPlace(i,j))
                    return false;
            }
        }
        return true;
    }

    /**
     *
     * @return - returns an array with all the possible actions.
     */
    public Action[] actions(){
        int j = 0;
        Action[] actionsTemp = new Action[Direction.values().length];
        int legalCount = 0;
        Tile source;
        for(Direction dir : Direction.values()){
            source = _board.isDirectionLegal(dir);
            if(source != null) {
                legalCount++;
                actionsTemp[dir.ordinal()] = new Action(source, dir);
            }
            else
                actionsTemp[dir.ordinal()] = null;
        }
        Action[] actions = new Action[legalCount];
        for(int i = 0; i < legalCount; i++){
            while(actionsTemp[j] == null){
                j++; // make sure we don't add null tiles
            }
            actions[i] = actionsTemp[j];
            j++;
        }
        return actions;
    }

    /**
     *
     * @param action - the action to be taken
     * @return - returns a new state, after the action is taken.
     */
    public State result(Action action){
        return new State(_board.result(action));
    }
    @Override
    public int hashCode() {
        return _board.hashCode();
    }
}
