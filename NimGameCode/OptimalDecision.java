import java.util.Random;


public class OptimalDecision {
 
	public OptimalDecision(){};
	
	
	/* START CODE FOR XOR HUERISTIC*/
	
	//Looks for best move
	public static int[] chooseMove(int[] muffins){
		//goal is to make next state a losing state (so we want to make all columns 0 in the XOR function
		int[] testArray = new int[3];
		testArray = resetArray(muffins);
		
		//Time it
		
		for(int i = 0; i<3; i++){ //consider each stack separately
			testArray = resetArray(muffins);
			
			for(int j = testArray[i]; j>0; j--){
				testArray[i]--; 
				if(!isItAWinningState(testArray)){
					//print out how long it took
					return testArray;

				}
			}
		}
		return testArray;	
		
	}
	
	//chooses a random configuration
	public static int[] chooseRandom(int[] muffins){
		Random generator = new Random();
		//clones the array
		int[] newConfig = muffins.clone();
		//generates a random number
		int num = generator.nextInt(3);
		while(newConfig[num]<=0){
			num = generator.nextInt(3);
		}
		//removes only one from a random stack
		newConfig[num] -= 1;
		return newConfig;
	}
	
	/* END CODE FOR XOR HUERISTIC*/
	
	
	
	/*START CODE FOR 4N */
	/**
	 * This method follows the game playing strategy where the monkey chooses the move that 
	 * leaves the monkey's opponent with 4n muffins left, where n = 1,2,3, etc.
	 * @param args
	 */
	public static int[] chooseMoveMethod2(int[] state){
		//goal is to make next state a losing state (so we want to make all columns 0 in the XOR function
		int[] dummy = state.clone();
		int[] testArray = new int[3];
		

		boolean foundOptimalMove = false;
		
		for(int i = 0; i<3; i++){ //consider each stack separately
			testArray = resetArray(dummy);
			
			for(int j = testArray[i]; j>0; j--){
				testArray[i]--; 
				
				if(isOptimalAmount(testArray)){
					foundOptimalMove = true;
					return testArray;
				}
			}
		}
		
		if(foundOptimalMove==false){
			//if no optimal move is found, just take 1 muffin from any available stack with muffins.
			testArray = resetArray(dummy);
			Random generator = new Random();
			int num = generator.nextInt(3);
			while(testArray[num]<=0){
				num = generator.nextInt(3);
			}
			testArray[num] -= 1;

		}
		return testArray;
	}

	public static boolean isOptimalAmount(int[] testArray) {
		int sum = 0;
		for(int i = 0; i<3; i++){
			sum += testArray[i];
		}
		
		if(sum%4==0)
			return true;
		else
			return false;
		
	}
	/*END CODE FOR 4N*/
	
	
	
	
	
	//resets array to find all configurations
	public static int[] resetArray(int[] muffins) {
		int[] array = new int[3];
		for(int i = 0; i<3; i++){
			array[i] = muffins[i];
		}
		return array;
	}

	//Checks if current state is a winning state
	public static boolean isItAWinningState(int[] muffins){
		//If value of operator is greater than 0
		if( (muffins[0]^muffins[1]^muffins[2]) > 0){
			return true;
		}
		//If value of operator is zero
		return false;
	} //isItAWinningState
	
	
	
	//Returns the stack number and the number of tokens
	public static int[] whichStackChanged(int[] original, int[] move){
		
		
		for(int i=0;i<3;i++){
			if(original[i] != move[i]){
				
				int[] answer = new int[2];
				//index 0 will have number of the stack that changed
				answer[0] = i+1;
				//index 1 will have by how much it changed
				answer[1] = original[i] - move[i];
				
				return answer;
			}
		}
		return move;
	}


	
	//Computer's move
	public static int[] computerMove(int[] state){
				
		//If it is a winning state choose the best move
		if (isItAWinningState(state)){
			return whichStackChanged(state,chooseMove(state));
		}
		//If it is a losing state just take randomly
		else{
			return whichStackChanged(state,chooseRandom(state));
		}
	}
	
	
	//Computer's move 2
	public static int[] computerMove2(int[] state){
		return whichStackChanged(state,chooseMoveMethod2(state));
	}
	
	
}
