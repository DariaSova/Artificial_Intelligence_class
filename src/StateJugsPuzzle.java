public class StateJugsPuzzle {
    int jugsArray[];

    public StateJugsPuzzle(int[] jugs) { this.jugsArray = jugs; }

    public StateJugsPuzzle(StateJugsPuzzle state){
        jugsArray = new int[4];
        for(int i=0; i<4; i++) 
            this.jugsArray[i] = state.jugsArray[i];
    }

    public boolean equals(Object obj) {
        StateJugsPuzzle state = (StateJugsPuzzle) obj;
        
        for (int i=0; i<this.jugsArray.length; i++)
            if (this.jugsArray[i] != state.jugsArray[i])
                return false;
        
        return true;
    }

    public int hashCode() {
        return jugsArray[0]*1000 + jugsArray[1]*100 + jugsArray[2]*10 + jugsArray[3];
    }

    public String toString() {
        String ret = "";
        for (int i=0; i<jugsArray.length; i++)
            ret += " " + this.jugsArray[i];
        return ret;
    }

}