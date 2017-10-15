public class NimState extends State {
    public int coins;

    public NimState(){
        this.coins = 13;
        this. player = 1; 
    }

    public NimState(NimState state){
        this.coins = state.coins;
        this.player = state.player;
    }

    public String toString(){
        return "There are " + this.coins + " coins left!";
    }
}