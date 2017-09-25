public class StateJugsPuzzle {
    int jugsArray[];

    public StateJugsPuzzle(int[] jugs) { this.jugsArray = jugs; }

    public StateJugsPuzzle(StateJugsPuzzle state){
        jugsArray = new int[3];
        for(int i=0; i<3; i++) 
            this.jugsArray[i] = state.jugsArray[i];
    }

    public boolean equals(Object obj) {
        StateJugsPuzzle state = (StateJugsPuzzle) obj;
        
        for (int i=0; i<this.jugsArray.length; i++)
            if (this.jugsArray[i] != state.jugsArray[i])
                return false;
        
        return true;
    }

    public String toString() {
        String ret = "";
        for (int i=0; i<jugsArray.length; i++)
            ret += " " + this.jugsArray[i];
        return ret;
    }

}