import java.util.HashSet;
import java.util.Set;

public class ProblemJugsPuzzle extends Problem {
    static final int jug12 = 0;
    static final int jug8 = 1;
    static final int jug3 = 2;

    //TODO: check this calculations according to the problem description
    double step_cost(Object fromState, Object toState) { 
        int costs=0;
        StateJugsPuzzle prev = (StateJugsPuzzle) fromState;
        StateJugsPuzzle next = (StateJugsPuzzle) toState;

        for(int i=0; i<3;i++){
            costs+=(next.jugsArray[i] - prev.jugsArray[i]);
        }

        return costs;
    }
    
    public double h(Object state) {
        return 0;
    }
    
    boolean goal_test(Object state) {
        StateJugsPuzzle currState = (StateJugsPuzzle) state;

        int[] results =  currState.jugsArray;
        for(int i: results)
            if(i==1) return true;

        return false;
    }

    private boolean isValid(StateJugsPuzzle state) {
        int[] jugs = state.jugsArray;

        if(jugs[0] > 12 || jugs[0]<0) return false;
        if(jugs[1] > 8 || jugs[1]<0) return false;
        if(jugs[2] > 3 || jugs[2]<0) return false;

        return true;
    }

     Set<Object> getSuccessors(Object state) {
        Set<Object> set = new HashSet<Object>();
        StateJugsPuzzle jugs_state = (StateJugsPuzzle) state;

        //Let's create without any constraint, then remove the illegal ones
        StateJugsPuzzle successor_state;
        int amount = 0;
        int level12, level8, level3, left12, left8, left3 = 0;

        //Fill 12 galons jug
        successor_state = new StateJugsPuzzle(jugs_state);
        if (successor_state.jugsArray[jug12] < 12) {
            amount = 12 - successor_state.jugsArray[jug12];
            successor_state.jugsArray[jug12] += amount;
            if (isValid(successor_state)) set.add(successor_state);
        }

        //Fill 8 galons jug
        successor_state = new StateJugsPuzzle(jugs_state);
        if (successor_state.jugsArray[jug8] < 8) {
            amount = 8 - successor_state.jugsArray[jug8];
            successor_state.jugsArray[jug8] += amount;
            if (isValid(successor_state)) set.add(successor_state);
        }
        if(isValid(successor_state)) set.add(successor_state);


        //Fill 3 galons jug
        successor_state = new StateJugsPuzzle(jugs_state);
        if (successor_state.jugsArray[jug3] < 3) {
            amount = 3 - successor_state.jugsArray[jug3];
            successor_state.jugsArray[jug3] += amount;
            if (isValid(successor_state)) set.add(successor_state);
        }
        if(isValid(successor_state)) set.add(successor_state);


        //Pour 12 -> 8
        successor_state = new StateJugsPuzzle(jugs_state);
        level12 = successor_state.jugsArray[jug12];
        left8 = 8 - successor_state.jugsArray[jug8];
        if(level12 <= left8) {
            successor_state.jugsArray[jug12] -= level12;
            successor_state.jugsArray[jug8] += level12;
        } else {
            successor_state.jugsArray[jug12] -= left8;
            successor_state.jugsArray[jug8] += left8;
        }
        if(isValid(successor_state)) set.add(successor_state);


        //Pour 12 -> 3
        successor_state = new StateJugsPuzzle(jugs_state);
        level12 = successor_state.jugsArray[jug12];
        left3 = 3 - successor_state.jugsArray[jug3];
        if(level12 <= left3) {
            successor_state.jugsArray[jug12] -= level12;
            successor_state.jugsArray[jug3] += level12;
        } else {
            successor_state.jugsArray[jug12] -= left3;
            successor_state.jugsArray[jug3] += left3;
        }
        if(isValid(successor_state)) set.add(successor_state);

        //Pour 8->3
        successor_state = new StateJugsPuzzle(jugs_state);
        level8 = successor_state.jugsArray[jug8];
        left3 = 3 - successor_state.jugsArray[jug3];
        if(level8 <= left3) {
            successor_state.jugsArray[jug8] -= level8;
            successor_state.jugsArray[jug3] += level8;
        } else {
            successor_state.jugsArray[jug8] -= left3;
            successor_state.jugsArray[jug3] += left3;
        }
        if(isValid(successor_state)) set.add(successor_state);

        //Pour 8->12
        successor_state = new StateJugsPuzzle(jugs_state);
        level8 = successor_state.jugsArray[jug8];
        left12 = 12 - successor_state.jugsArray[jug12];
        if(level8 <= left12) {
            successor_state.jugsArray[jug8] -= level8;
            successor_state.jugsArray[jug12] += level8;
        } else {
            successor_state.jugsArray[jug8] -= left12;
            successor_state.jugsArray[jug12] += left12;
        }
        if(isValid(successor_state)) set.add(successor_state);

        //Pour 3->8
        successor_state = new StateJugsPuzzle(jugs_state);
        level3 = successor_state.jugsArray[jug3];
        left8 = 8 - successor_state.jugsArray[jug8];
        if(level3 <= left8) {
            successor_state.jugsArray[jug3] -= level3;
            successor_state.jugsArray[jug8] += level3;
        } else {
            successor_state.jugsArray[jug3] -= left8;
            successor_state.jugsArray[jug8] += left8;
        }
        if(isValid(successor_state)) set.add(successor_state);

        //Pour 3->12
        successor_state = new StateJugsPuzzle(jugs_state);
        level3 = successor_state.jugsArray[jug3];
        left12 = 12 - successor_state.jugsArray[jug12];
        if(level3 <= left12) {
            successor_state.jugsArray[jug3] -= level3;
            successor_state.jugsArray[jug12] += level3;
        } else {
            successor_state.jugsArray[jug3] -= left12;
            successor_state.jugsArray[jug12] += left12;
        }
        if(isValid(successor_state)) set.add(successor_state);


        //Pour out 12
        successor_state = new StateJugsPuzzle(jugs_state);
        if (successor_state.jugsArray[jug12]>0){
            successor_state.jugsArray[jug12] = 0;
            if(isValid(successor_state)) set.add(successor_state);
        }

        //Pour out 8
        successor_state = new StateJugsPuzzle(jugs_state);
        if (successor_state.jugsArray[jug8]>0){
            successor_state.jugsArray[jug8] = 0;
            if(isValid(successor_state)) set.add(successor_state);
        }

        //Pour out 3
        successor_state = new StateJugsPuzzle(jugs_state);
        if (successor_state.jugsArray[jug3]>0){
            successor_state.jugsArray[jug3] = 0;
            if(isValid(successor_state)) set.add(successor_state);
        }

        return set;

     }


    public static void main(String[] args) throws Exception {
		ProblemJugsPuzzle problem = new ProblemJugsPuzzle();
        System.out.println("Water Jugs Problem Solver:");
        int [] waterJugs = {0,0,0};
        problem.initialState = new StateJugsPuzzle(waterJugs);
		Search search  = new Search(problem);
		
		System.out.println("BreadthFirstTreeSearch:\t\t" + search.BreadthFirstTreeSearch());
    }

}