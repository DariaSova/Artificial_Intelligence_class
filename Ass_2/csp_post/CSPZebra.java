import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.math.*;

public class CSPZebra extends CSP {

	static Set<Object> varColors = new HashSet<Object> (Arrays.asList(new String[] {"blue", "green", "ivory", "red", "yellow"}));
	static Set<Object> varDrinks = new HashSet<Object> (Arrays.asList(new String[] {"coffee", "milk", "orange-juice", "tea", "water"}));
	static Set<Object> varNationalities = new HashSet<Object> (Arrays.asList(new String[] {"englishman", "japanese", "norwegian", "spaniard", "ukrainian"}));
	static Set<Object> varPets = new HashSet<Object> (Arrays.asList(new String[] {"dog", "fox", "horse", "snails", "zebra"}));
	static Set<Object> varCigarettes = new HashSet<Object> (Arrays.asList(new String[] {"chesterfield", "kools", "lucky-strike", "old-gold", "parliament"}));
		
	
	public boolean isGood(Object X, Object Y, Object x, Object y) {
    	//if X is not even mentioned in by the constraints, just return true
    	//as nothing can be violated
   		if(!C.containsKey(X)) return true;

	    //check to see if there is an arc between X and Y
	    //if there isn't an arc, then no constraint, i.e. it is good
	   	if(!C.get(X).contains(Y)) return true;
    	
		// Check for constraints.
	    //The Englishman lives in the red house.
		if(X.equals("englishman") && Y.equals("red") && !x.equals(y)) return false;
	    // The Spaniard owns a dog.
	    if(X.equals("spaniard") && Y.equals("dog") && !x.equals(y)) return false;
	    // Coffee is drunk in the green house.
	    if(X.equals("coffee") && Y.equals("green") && !x.equals(y)) return false;
	    //	The Ukrainian drinks tea.
	    if(X.equals("ukrainian") && Y.equals("tea") && !x.equals(y)) return false;
	    // The green house is directly to the right of the ivory house.
	    if(X.equals("green") && Y.equals("ivory") && (Integer)x-(Integer)y !=1) return false;
	    //	The Old-Gold smoker owns snails.
	    if(X.equals("old-gold") && Y.equals("snails") && !x.equals(y)) return false;
	    //	Kools are being smoked in the yellow house.
	    if(X.equals("kools") && Y.equals("yellow") && !x.equals(y)) return false;
		// Milk is drunk in the middle house. 
	    if(X.equals("milk") && (Integer) x != 3) return false;
		// The Norwegian lives in the first house on the left.
		if (X.equals("norwegian") && (Integer) x != 1) return false;
		// The Chesterfield smoker lives next to the fox owner.
    	if(X.equals("chesterfield") && Y.equals("fox") && Math.abs((Integer)x-(Integer)y) !=1) return false;
    	// Kools are smoked in the house next to the house where the horse is kept.
    	if(X.equals("kools") && Y.equals("horse") && Math.abs((Integer)x-(Integer)y) !=1) return false;
    	// The Lucky-Strike smoker drinks orange juice.
    	if(X.equals("lucky-strike") && Y.equals("orange-juice") && !x.equals(y)) return false;
 	    // The Japanese smokes Parliament.
 	    if(X.equals("japanese") && Y.equals("parliament") && !x.equals(y)) return false;
	    // The Norwegian lives next to the blue house.
		if(X.equals("norwegian") && Y.equals("blue") && Math.abs((Integer)x-(Integer)y) !=1) return false;

	    //Uniqueness constraints
      	if(varColors.contains(X) && varColors.contains(Y) && !X.equals(Y) && x.equals(y)) return false;
      	if(varDrinks.contains(X) && varDrinks.contains(Y) && !X.equals(Y) && x.equals(y)) return false;
      	if(varNationalities.contains(X) && varNationalities.contains(Y) && !X.equals(Y) && x.equals(y)) return false;
      	if(varPets.contains(X) && varPets.contains(Y) && !X.equals(Y) && x.equals(y)) return false;
      	if(varCigarettes.contains(X) && varCigarettes.contains(Y) && !X.equals(Y) && x.equals(y)) return false;

      	return true;
   }

   private static void addDomain(CSPZebra csp, Set<Object> vars){
		Integer[] dom = {1,2,3,4,5};
		for(Object X : vars)
			csp.addDomain(X, dom); 
   }

   private static void addUniqueConstraints(CSPZebra csp, Set<Object> vars){
	   //Inner For loop?
		for(Object X : vars)
			for(Object Y : vars)
				csp.addBidirectionalArc(X,Y);
   }

   public static void main(String[] args) throws Exception {
   		CSPZebra csp = new CSPZebra();

		addDomain(csp, varColors);
		addDomain(csp, varDrinks);
		addDomain(csp, varNationalities);
		addDomain(csp, varCigarettes);
		addDomain(csp, varPets);
		
		// Unnary constraints.
		// Milk is drunk in the middle house.
		for (int i=1; i<=5; i++)
			if (i !=3) csp.D.get("milk").remove(i);
	    // The Norwegian lives in the first house on the left.
		for (int i=2; i<=5; i++)
			csp.D.get("norwegian").remove(i);

		// Binary constraints
		csp.addBidirectionalArc("englishman", "red");
		csp.addBidirectionalArc("spaniard", "dog");
		csp.addBidirectionalArc("coffee", "green");
		csp.addBidirectionalArc("ukrainian", "tea");
		csp.addBidirectionalArc("green", "ivory");
		csp.addBidirectionalArc("old-gold", "snails");
		csp.addBidirectionalArc("kools", "yellow");
		csp.addBidirectionalArc("chesterfield", "fox");
		csp.addBidirectionalArc("lucky-strike", "orange-juice");
		csp.addBidirectionalArc("japanese", "parliament");
		csp.addBidirectionalArc("norwegian", "blue");
		csp.addBidirectionalArc("kools", "horse");
		
		//Uniqueness constraints
		addUniqueConstraints(csp, varColors);
		addUniqueConstraints(csp, varDrinks);
		addUniqueConstraints(csp, varNationalities);
		addUniqueConstraints(csp, varCigarettes);
		addUniqueConstraints(csp, varPets);

		//Now let's search for solution
		Search search = new Search(csp);
		System.out.println(search.BacktrackingSearch());
	}
}
//{blue=2, chesterfield=2, coffee=5, dog=4, englishman=3, fox=1, green=5, horse=2, ivory=4, japanese=5, kools=1, lucky-strike=4, milk=3, norwegian=1, old-gold=3, orange-juice=4, parliament=5, red=3, snails=3, spaniard=4, tea=2, ukranian=2, water=1, yellow=1, zebra=5}