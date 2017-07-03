package MOproblem;

public class MultiObjectiveSetting {
	
	/**
	 * Initialization
	 * */
	
	public static final int paretoFrontlimitSize = 50;
	public static final String[] ObjectiveDecision = new String[]{"small", "small"}; // large or small
	public static final int[] generateTestFunctionRandomChromosomeSettings = new int[]{ 0, 5, 0, 3};//minimum_1, Maximum_1, minimum_2, Maximum_2

	/**
	 * Fitness sharing, niching technique
	 * */
	public static final double tau_share = 2;
	
}
