package ParetoFront;

import java.util.ArrayList;

public class ParetoFronts { //Fi = pareto-front //i = rank
	private ArrayList<Integer> m_ParetoFrontSetList = null;
	private int m_PreviousRankChromosomeNumber = 0;
	private int m_Rank = 0;
	
	public ParetoFronts(){
		m_ParetoFrontSetList = new ArrayList<Integer>();
	}
	
	public void add_non_dominate_Chromosome_index_list (int nonDominateChromosome){
		this.m_ParetoFrontSetList.add(nonDominateChromosome);
	}
	
	public ArrayList<Integer> get_non_dominate_Chromosome_index_list(){
		return this.m_ParetoFrontSetList ;
	}
	
	public void remove_from_non_dominate_Chromosome_index_list(int index){
		this.m_ParetoFrontSetList.remove(index);
	}
	
	public int getListSize(){
		return this.m_ParetoFrontSetList.size();
	}
	
	public void setRank(int rank){
		this.m_Rank = rank;
	}
	
	public int getRank(){
		return this.m_Rank;
	}
	
	public void setPreviousFrontChromosomeNumbers(int previousRankChromosomeNumber){
		this.m_PreviousRankChromosomeNumber = previousRankChromosomeNumber;
	}
	
	public int getPreviousFrontChromosomeNumbers(){
		return this.m_PreviousRankChromosomeNumber;
	}
	
}
