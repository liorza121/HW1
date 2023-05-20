import javafx.util.Pair;

public class Node {
    private Node _parent;
    private Action _originatingAction;
    private State _state;
    public Node(Node node, Action action, State stateP){
        _parent = node;
        _originatingAction = action;
        _state = stateP;
    }

    /**
     *
     * @return - returns an array containing up to 4 new nodes containing nodes resulting from expanding the solution
     */
    public Node[] expand(){
        Action[] actions  = _state.actions();
        Node[] nextLayer = new Node[actions.length];
        for(int action_i = 0; action_i < actions.length; action_i++){
            nextLayer[action_i] = new Node(this,actions[action_i], _state.result(actions[action_i]));
        }
        return nextLayer;
    }

    /**
     *
     * @return - "how far" the state is from the destination state.
     * currently, the basic value of how many tiles are in their place.
     */
    public int heuristicValue(){
        return _state.SSE();
    }

    public State getState() {
        return _state;
    }
    public Action getAction(){
        return _originatingAction;
    }
    public Node getParent(){
        return _parent;
    }
}
