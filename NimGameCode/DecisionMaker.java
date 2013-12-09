
public class DecisionMaker {
	Gameboard node; //Each gameboard is a state of the muffin stacks, representing a possible move for one's turn

	public DecisionMaker(){
		
	}
	
	public Gameboard chooseMove(int[] muffins, Gameboard root){
		//goal is to make next state a losing state (so we want to make all columns 0 in the XOR function
		Gameboard result = new Gameboard();
		int[] testArray = new int[3];
		testArray = resetArray(muffins);
		
		for(int i = 0; i<3; i++){ //consider each stack separately
			System.out.println("Checking col " + i);
			testArray = resetArray(muffins);
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
	
	public int[] resetArray(int[] muffins) {
		int[] array = new int[3];
		for(int i = 0; i<3; i++){
			array[i] = muffins[i];
		}
		return array;
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
