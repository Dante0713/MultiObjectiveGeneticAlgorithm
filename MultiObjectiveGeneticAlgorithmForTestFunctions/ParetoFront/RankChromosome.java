package ParetoFront;

import GeneticAlgorithm.GeneticAlgorithmSetting;
import initial.Chromosome;

public class RankChromosome extends Chromosome{
//	private int m_rank = 0;
	private int m_DominatedTimes = 0;
	private int SameRankNumber = 0;
	private int PreviosRankNumber = 0;
	private float m_nonPunishedFitness = 0.0f;
	private double m_Solution_Rank_fitness = 0.0d;
	private double m_nicheCount = 0.0d;
	
	public RankChromosome(){
		
	}
	
	public RankChromosome setChromosomeInfo(Chromosome chromosome){
		this.setDoubleChromosome(chromosome.getDoubleChromosome());
		this.setObjectiveValue(chromosome.getEachObjectiveFitness());
		return this;
	}
	
	public void minusDominateTimes(){
		this.m_DominatedTimes--;
	}
	
	public void addDominatedTimes(){ //np
		this.m_DominatedTimes++;
	}
	
	public int getDominateTimes(){
		return this.m_DominatedTimes;
	}
	
	public void setSameRankNumber(int sameRankNumber){
		this.SameRankNumber = sameRankNumber;
	}
	
	public void setPreviousRankNumber(int previousNumber){
		this.PreviosRankNumber = previousNumber;
		setNonPunishedMultiobjectiveFitness( );
	}
	
	/**
	 * Step 3.2
	 * */
	public int getSameRankNumber(){
		return this.SameRankNumber;
	}
	
	public int getPreviousRankNumber(){
		return this.PreviosRankNumber;
	}
	
	private void setNonPunishedMultiobjectiveFitness() {
		int ChromosomeNumber = GeneticAlgorithmSetting.chromosomeSize;
		m_nonPunishedFitness = (float) (ChromosomeNumber-PreviosRankNumber-0.5*(SameRankNumber-1));
	}
	
	public float getNonPunishedMultiobjectiveFitness(){
		return this.m_nonPunishedFitness ;
	}
	
	/**
	 * Step 3.3
	 * */
	public void addNicheCount(double nicheCount){
		this.m_nicheCount = this.m_nicheCount + nicheCount;
	}
	
	public void resetNicheCount(){
		this.m_nicheCount = 0;
	}
	
	public double getNicheCount(){
		return this.m_nicheCount;
	}
	
	/**
	 * Step3.4
	 * */
	public void setSharedFitness(double solutionRankFitness){
		m_Solution_Rank_fitness = solutionRankFitness;
	}
	
	public double getSharedFitness(){
		return this.m_Solution_Rank_fitness;
	}
	
	
}
