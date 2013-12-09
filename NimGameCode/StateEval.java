public class StateEval {
	/**
	 * Function isItAWinningState is used for the Nim game with 3 stacks of n items. Each stack has n items, it could be unique
	 * or it can also be different
	 *   
	 *  Each parameter is an integer that represents the number of items on each stack
	 *  If the stack is empty then we pass in a 0
	 *  
	 *  The function calculates the xor value using the ^ operator which returns a decimal value
	 *  
	 *  If that value is 0, it is considered a losing state
	 *  If that value is not 0, it is considered a winning state
	 *   
	 * @param stackNumber1   Number of items on stack 1 
	 * @param stackNumber2	 Number of items on stack 2
	 * @param stackNumber3   Number of items on stack 3
	 * @return boolean value, true ~> winning state, false ~> losing state
	 */
	public static boolean isItAWinningState(int stackNumber1, int stackNumber2, int stackNumber3){
		//If value of operator is greater than 0
		if( (stackNumber1^stackNumber2^stackNumber3) > 0){
			return true;
		}
		//If value of operator is zero
		return false;
	}
	
	
	public static void main(String args[]){
		System.out.println(StateEval.isItAWinningState(1, 2, 3));
	}
}
