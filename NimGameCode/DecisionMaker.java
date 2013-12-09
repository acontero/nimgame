
public class DecisionMaker {
	Gameboard node; //Each gameboard is a state of the muffin stacks, representing a possible move for one's turn

	public DecisionMaker(){
		
	}
	
	public Gameboard chooseMove(int[] muffins, Gameboard root){
		System.out.println("Muffins State Check: ");
		printBoard(muffins);
		//goal is to make next state a losing state (so we want to make all columns 0 in the XOR function
		Gameboard result = new Gameboard();
		int[] testArray = new int[3];
		testArray = muffins;
		
		for(int i = 0; i<3; i++){ //consider each stack separately
			System.out.println("Muffins State Check: ");
			printBoard(muffins); //should be same always in this fxn
			System.out.println("Checking col " + i);
			testArray = muffins;
			printBoard(testArray);
			
			for(int j = testArray[i]; j>0; j--){
				testArray[i]--; 
				printBoard(testArray);
				if(!isItAWinningState(testArray)){
					System.out.println("Found next move!");
					result.currentMuffins = testArray;
					result.winningState = false;
					return result;
				}
			}
		}	
		return result;
	}
	
	public static boolean isItAWinningState(int[] muffins){
		//If value of operator is greater than 0
		if( (muffins[0]^muffins[1]^muffins[2]) > 0){
			return true;
		}
		//If value of operator is zero
		return false;
	} //isItAWinningState
	
	public static void printBoard(int[] board) {
		for (int i = 0; i < 3; i++)
			System.out.print(board[i] + " ");
		System.out.println();
	} // printBoard
	

}
