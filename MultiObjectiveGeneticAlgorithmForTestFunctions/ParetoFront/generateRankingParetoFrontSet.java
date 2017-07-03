package ParetoFront;

import java.util.ArrayList;
import MOproblem.MultiObjective;
import MOproblem.MultiObjectiveSetting;
import initial.Chromosome;

public class generateRankingParetoFrontSet {

	private ArrayList<RankChromosome> RankTypeChromosomeSet = null;
	private ArrayList<ParetoFronts> m_ParetoFrontSet = null;
	private ArrayList<Integer> unListedChromosomeList = null;
	
	public generateRankingParetoFrontSet(ArrayList<Chromosome> chromosome) {
		RankTypeChromosomeSet = new ArrayList<RankChromosome>();
		System.out.println("chromosome fitness");
		for (Chromosome chromosome2 : chromosome) {
			RankTypeChromosomeSet.add(new RankChromosome().setChromosomeInfo(chromosome2));
			for (double fitness : chromosome2.getEachObjectiveFitness()) {
				System.out.print(fitness + "\t");
			}
			System.out.println();
		}
		System.out.println();
	}

	public void findingSet() {
		fast_non_dominated_sort(); //into
//		System.out.println("fast_non_dominated sort done. ");
//		showRankTypeChromosome();
	}

	/**
	 * paretoFrontSet => paretoFront => RankTypeChromosome => Chromosome
	 */
	private void fast_non_dominated_sort() {
		unListedChromosomeList = new ArrayList<Integer>(); 
		m_ParetoFrontSet = new ArrayList<ParetoFronts>();
		ParetoFronts rankParetoFront = new ParetoFronts();
		rankParetoFront.setRank(1);
		int dominate_relationship_with_p_q = 0;
		
		for (int index_p = 0; index_p < RankTypeChromosomeSet.size(); index_p++) {
			RankChromosome p = RankTypeChromosomeSet.get(index_p);
			for (int index_q = 0; index_q < RankTypeChromosomeSet.size(); index_q++) {
				RankChromosome q = RankTypeChromosomeSet.get(index_q);
				if (index_p != index_q) {
					dominate_relationship_with_p_q = p_Dominate_q(p.getEachObjectiveFitness(), q.getEachObjectiveFitness());
					if (dominate_relationship_with_p_q == 0) {
						addIntoSetOfDominateByP(rankParetoFront, index_q);
					} else if (dominate_relationship_with_p_q == q.getEachObjectiveFitness().length) {
						add_p_DominationCounter(p);
					}
				}
			}
		}
		for (int index_p = 0; index_p < RankTypeChromosomeSet.size(); index_p++) {
			if (RankTypeChromosomeSet.get(index_p).getDominateTimes() == 0) {				
				rankParetoFront.setRank(1);
				rankParetoFront.add_non_dominate_Chromosome_index_list(index_p);
			}
		}
		m_ParetoFrontSet.add(rankParetoFront);
		addRankChromosomeNumbersInRchromosome(0);
		
		int Rank = 1;
		int dominatedTimes = 0;
		int index_q = 0;
		System.out.println("show");
		while (Check_All_Chromosome_Has_Been_List_In_Pareto_Front_Sets(unListedChromosomeList) && Rank < 20) {
			ParetoFronts worseRankParetoFront = new ParetoFronts();
			Rank ++;
			worseRankParetoFront.setRank(Rank);
			index_q = 0;
			int ListSize = unListedChromosomeList.size();
			System.out.println("ListSize: " + ListSize);
			while(index_q < ListSize){// 上一層柏拉圖前緣中的第index_p個RankTypeChromosome所支配的第index_q個RankTypeChromosome
				RankTypeChromosomeSet.get(unListedChromosomeList.get(index_q)).minusDominateTimes();
				dominatedTimes = RankTypeChromosomeSet.get(unListedChromosomeList.get(index_q)).getDominateTimes();
				if (dominatedTimes == 0) {
					worseRankParetoFront.add_non_dominate_Chromosome_index_list(unListedChromosomeList.get(index_q));
					unListedChromosomeList.remove(index_q);					
					index_q  --;
					ListSize --;
				}
				index_q ++;
			}
			if (worseRankParetoFront.getListSize() != 0) {//如果frontList中沒有任何Chromosome
				m_ParetoFrontSet.add(worseRankParetoFront);
				addRankChromosomeNumbersInRchromosome(m_ParetoFrontSet.size()-1);
			}
		}
	}

	private void addRankChromosomeNumbersInRchromosome(int FrontIndex) {
		ArrayList<Integer> CurrentParetoFrontlist = m_ParetoFrontSet.get(FrontIndex).get_non_dominate_Chromosome_index_list();
		int previousParetoNumber = 0;
		int previousParetoIndex = FrontIndex-1;
		if(FrontIndex > 1){
			previousParetoNumber = m_ParetoFrontSet.get(previousParetoIndex).getPreviousFrontChromosomeNumbers() + CurrentParetoFrontlist.size();
		}else if(FrontIndex == 1){
			previousParetoNumber =  m_ParetoFrontSet.get(previousParetoIndex).getListSize() + CurrentParetoFrontlist.size();
		}
		for (int i = 0; i < CurrentParetoFrontlist.size(); i++) {
			int index = CurrentParetoFrontlist.get(i);
			this.RankTypeChromosomeSet.get(index).setSameRankNumber(CurrentParetoFrontlist.size());
			this.RankTypeChromosomeSet.get(index).setPreviousRankNumber(previousParetoNumber);
		}
		m_ParetoFrontSet.get(FrontIndex).setPreviousFrontChromosomeNumbers(previousParetoNumber);
	}

	private boolean Check_All_Chromosome_Has_Been_List_In_Pareto_Front_Sets(ArrayList<Integer> unListedChromosomeList) {
		if(unListedChromosomeList.size() == 0){
			return false;
		}
		return true;
	}

	private void add_p_DominationCounter(RankChromosome p) {
		p.addDominatedTimes();
	}

	private void addIntoSetOfDominateByP(ParetoFronts rankParetoFront, int Index_q) {
		boolean repeatChromosomeInParetoFront = false;
		for (int dominateIndex : unListedChromosomeList) {
			if(dominateIndex == Index_q){
				repeatChromosomeInParetoFront = true;
			}
		}
		if(!repeatChromosomeInParetoFront){
			unListedChromosomeList.add(Index_q);
		}
	}

//	private void showRankTypeChromosome() {
//		for (RankChromosome rankedChromosome : RankTypeChromosomeSet) {
//			for (double fitness : rankedChromosome.getEachObjectiveFitness()) {
//				System.out.print(fitness + "\t");
//			}
//			System.out.println("dominate times: " + rankedChromosome.getDominateTimes()+"\tDominating List: ");
//		}
//		
//		for (ParetoFronts Pareto_Front : m_ParetoFrontSet) {
//			for (int non_dominate_List : Pareto_Front.get_non_dominate_Chromosome_index_list()) {
//				System.out.print(non_dominate_List+"\t");
//			}
//			System.out.println();				
//		}
//		
//		System.out.println("unlistChromosome");
//		for (Integer UnListedChromosomeList : unListedChromosomeList) {
//			System.out.print(UnListedChromosomeList+"\t");
//		}System.out.println();
//		
//		System.out.println("chromosomeInfo");
//		int index = 0;
//		for (RankChromosome rankChromosome : RankTypeChromosomeSet) {
//			System.out.println("rankedChromosome ["+index+"] same rank size: "+rankChromosome.getSameRankNumber());
//			System.out.println("rankedChromosome ["+index+"] previous rank size: "+rankChromosome.getPreviousRankNumber());
//			System.out.println("rankedchromosome ["+index+"] MultiObjective fitness: "+ rankChromosome.getNonPunishedMultiobjectiveFitness());
//			index++;
//		}
//	}

	public void show() {
		for (ParetoFronts ParetoFront : this.m_ParetoFrontSet) {
			System.out.print("Rank: " + ParetoFront.getRank() + "\n");
			for (int paretoFrontIndex : ParetoFront.get_non_dominate_Chromosome_index_list()) {
				System.out.print("chromosome["+paretoFrontIndex+"] fitness: ");
				for (double fitness : this.RankTypeChromosomeSet.get(paretoFrontIndex).getEachObjectiveFitness()) {
					System.out.print(fitness + "\t");
				}
				System.out.println();
			}
		}
	}

	/**
	 * dominateFlag == dimension =>"q_dominate_p" dominateFlag == 0
	 * =>"p_dominate_q" 0 < dominateFlag < 1 => "p_q non-dominate each other"
	 */
	public int p_Dominate_q(double[] Chromosome_p, double[] Chromosome_q) {
		int dominateFlag = 0;
		for (int index = 0; index < Chromosome_p.length; index++) {
			if (MultiObjectiveSetting.ObjectiveDecision[index] == "small") {
				if (smallerIsBetter(Chromosome_p[index], Chromosome_q[index])) {
					dominateFlag++;
				}
			} else if (MultiObjectiveSetting.ObjectiveDecision[index] == "large") {
				if (biggerIsBetter(Chromosome_p[index], Chromosome_q[index])) {
					dominateFlag++;
				}
			} else {
				System.out.println("Your MultiObjectiveSetting.ObjectiveDecision was setting wrong!");
			}
		}
		return dominateFlag;
	}

	public boolean biggerIsBetter(double paretoFrontChromosome, double NewChromosome) {
		if (NewChromosome > paretoFrontChromosome) {
			return true;
		} else {
			return false;
		}
	}

	public boolean smallerIsBetter(double paretoFrontChromosome, double NewChromosome) {
		if (NewChromosome < paretoFrontChromosome) {
			return true;
		} else {
			return false;
		}
	}

	public ArrayList<ParetoFronts> getSet() {
		return m_ParetoFrontSet;
	}
	
	public ArrayList<RankChromosome> getChromosome(){
		return this.RankTypeChromosomeSet;
	}

	public static void main(String src[]) {
		MultiObjective MO = new MultiObjective();
		MO.MultiObjectiveProgress(20);
	}
}
