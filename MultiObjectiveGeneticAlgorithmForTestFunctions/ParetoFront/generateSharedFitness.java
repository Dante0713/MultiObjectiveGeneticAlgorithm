package ParetoFront;

import java.util.ArrayList;

import MOproblem.MultiObjective;
import initial.Chromosome;

public class generateSharedFitness {
	private double m_Sigma_share = 0.0d;
	private double[] m_ExtremeFitnessValue = null;
	private ArrayList<RankChromosome> m_Rchromosome = null;
	private ArrayList<Chromosome> m_Chrs = null;
	private ArrayList<ParetoFronts> m_paretoFront = null;
	
	public generateSharedFitness(){
	}
	
	public void setChromosome(ArrayList<RankChromosome> Rchromosome){
		this.m_Rchromosome = Rchromosome;
	}
	
	public void setParetoFront(ArrayList<ParetoFronts> paretoFront){
		this.m_paretoFront = paretoFront;
	}
	
	public void setExtremeFitnessValue(double[] extremeFitnessValue){
		m_ExtremeFitnessValue = extremeFitnessValue;
	}
	
	public void shareingFunction(){
		setSigma_Share();
		ArrayList<Integer> calculateList = null;
		//		for (int i = 0; i < this.m_paretoFront.size(); i++) {
			calculateList = this.m_paretoFront.get(0).get_non_dominate_Chromosome_index_list();
			
			for (int index_a = 0; index_a < calculateList.size()-1; index_a++) {
				double nicheCount = 0.0d;
				for (int index_b = index_a+1; index_b < calculateList.size(); index_b++) {
					double add = generateNicheCountWithAverageDistanceTau_Share(calculateList.get(index_a), calculateList.get(index_b));
					nicheCount = add + nicheCount;
					System.out.println("Chromosome["+calculateList.get(index_a)+"] & Chromosome["+calculateList.get(index_b)+"] niche count + "+add);
					m_Rchromosome.get(calculateList.get(index_a)).addNicheCount(nicheCount);
					m_Rchromosome.get(calculateList.get(index_b)).addNicheCount(nicheCount);
				}
			}
//		}
		double nicheCount = 0.0f;
		float nonPunishedMultiObjectiveFitness = 0.0f;
		double sharedFitness = 0.0d;
		m_Chrs = new ArrayList<Chromosome>(); 
		for (int index : calculateList) {
			nicheCount = m_Rchromosome.get(index).getNicheCount();
			nonPunishedMultiObjectiveFitness = m_Rchromosome.get(index).getNonPunishedMultiobjectiveFitness();
			if(nicheCount>0){
				sharedFitness = nonPunishedMultiObjectiveFitness / nicheCount;
			}else{
				sharedFitness = nonPunishedMultiObjectiveFitness;
			}
			m_Chrs.add(new Chromosome().setInfo(m_Rchromosome.get(index).getDoubleChromosome(), m_Rchromosome.get(index).getEachObjectiveFitness(), sharedFitness));
			m_Rchromosome.get(index).setSharedFitness(sharedFitness);
		}
		
		show();
	}
	
	private void show(){
		for (int i = 0; i < m_Chrs.size(); i++) {
			System.out.println("chromosome["+i+"]");
			System.out.println("\tNiche count: "+this.m_Chrs.get(i).getSelectionPossibility());
			System.out.println("\tSharedFitness: "+this.m_Chrs.get(i).getSelectionPossibility());
		}
	}
	
//	public void generateNicheCountWithConstantTau_Share(){
//		double nicheCount = 0.0d;
//		ArrayList<Integer> calculateList = null;
//		double distance = 0.0d;
//		double Tau_share = MultiObjectiveSetting.tau_share;
//		for (int i = 0; i < this.m_paretoFront.size(); i++) {
//			calculateList = this.m_paretoFront.get(i).get_non_dominate_Chromosome_index_list();
//			for (int index_a = 0; index_a < calculateList.size(); index_a++) {
//				for (int index_b = 0; index_b < calculateList.size(); index_b++) {
//					if(index_a != index_b){
//						distance = Euclidean_distance(index_a,index_b);
//						nicheCount = (Tau_share-distance) / Tau_share;
//						if(nicheCount < 0 ){
//							nicheCount = 0;
//						}
//					}
//				}
//			}
//		}
//	}
	
	private void setSigma_Share(){
		Sigma_Share_SolutionSpace Sigma_share = new Sigma_Share_SolutionSpace(m_ExtremeFitnessValue);
		this.m_Sigma_share = Sigma_share.getSigma_Share();
		System.out.println("m_Sigma_share: "+m_Sigma_share);
	}
	
	private double generateNicheCountWithAverageDistanceTau_Share(int index_a, int index_b){
		double distance = 0.0d, nicheCount = 0.0d;
		if(index_a != index_b){
			distance = Euclidean_distance(index_a,index_b);
			nicheCount = (this.m_Sigma_share-distance) / this.m_Sigma_share;
			if(nicheCount < 0 ){
				nicheCount = 0;
			}
		}
		return nicheCount;
	}
	
	private double Euclidean_distance(int i, int j){
		double[] x = this.m_Rchromosome.get(i).getEachObjectiveFitness().clone();
		double[] y = this.m_Rchromosome.get(j).getEachObjectiveFitness().clone();
		
		double square_of_distance = 0;
		for (int k = 0; k < x.length; k++) {
			square_of_distance = square_of_distance + Math.pow(x[k] - y[k], 2); 
		}
		
		return Math.sqrt(square_of_distance); 
	}
	
	public ArrayList<Chromosome> getChromosome(){
		return this.m_Chrs;
	}
	
	public ArrayList<RankChromosome> getRankChromosome(){
		return this.m_Rchromosome;
	}
	
	public static void main(String src[]){
		MultiObjective MO = new MultiObjective();
		MO.MultiObjectiveProgress(20);
	}
}
