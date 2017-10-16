import java.util.Scanner;
import java.util.HashSet;
import java.util.Set;

public class NimGame extends Game {
    int WinningScore = 100;
    int LosingScore = -100;
    int NeutralScore = 0; 

    public NimGame() {
        this.currentState = new NimState();
    }

    public boolean isWinState(State state){
        NimState st = (NimState) state;
        if(st.coins==1) return true;

        return false;
    }

    public boolean isStuckState(State state) {
        //there is no stuck state
        return false;
    }

	public Set<State> getSuccessors(State state){
        if(isWinState(state)) return null;

        Set<State> successors = new HashSet<State>();
        NimState st = (NimState) state;

        NimState successor_st;

        for(int i=1; i<=3; i++){
            successor_st = new NimState(st);
            successor_st.coins -= i;
            successor_st.player = (state.player==0 ? 1 : 0);
           
            successors.add(successor_st);
        }

        return successors;
    }

	public double eval(State state){
        if (isWinState(state)) {
            int last_player = state.player == 0 ? 1 : 0;
            if (last_player == 0) //computer wins
                return WinningScore;
            else //human wins
                return LosingScore;
        }
        return NeutralScore;
    }

    public static void main(String[] args) throws Exception {
        Game Nim = new NimGame();
        Search search = new Search(Nim);
        int depth = 13;

        Scanner sc = new Scanner(System.in);
        // Nim.currentState.player =0; //let computer start
        System.out.println(Nim.currentState);

        while(true){
            NimState nextState = null;
            switch(Nim.currentState.player){
                // Computer
                case 0:
                nextState = new NimState((NimState)search.bestSuccessorState(depth));
                nextState.player = 0;
                System.out.println(">Computer: \n" + nextState);
                break;
                
                // Human
                case 1:
                System.out.println("Your turn: how many stones would you like to take?");
                int val = sc.nextInt();
                while(val<1||val>3) {
                    System.out.println("Your turn: you can only take 1-3 stones: ");
                    val = sc.nextInt();
                }

                nextState = new NimState((NimState)Nim.currentState);
                nextState.player = 1;
                nextState.coins -= val;
                System.out.println(">Human: \n" + nextState);
                break;
            }

            Nim.currentState = nextState;
            if(Nim.isWinState(Nim.currentState)){
                if(Nim.currentState.player == 0) System.out.println("Computer wins!");
                else System.out.println("You win!");

                break;
            }

            Nim.currentState.player = Nim.currentState.player ==0 ? 1 : 0;


        }
        // sc.close();
    }
}