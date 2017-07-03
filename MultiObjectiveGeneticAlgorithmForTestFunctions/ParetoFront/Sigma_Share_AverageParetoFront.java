package ParetoFront;

import java.util.ArrayList;

public class Sigma_Share_AverageParetoFront {//From Average distance of each ParetoFront 
	private ArrayList<RankChromosome> rankChromosomes = null;
	private double Tau_Share = 0.0d;
	public Sigma_Share_AverageParetoFront(ArrayList<RankChromosome> rankChromosome){
		rankChromosomes = rankChromosome;
	}
	
	private double Euclidean_distance(int i, int j){
		double[] x = this.rankChromosomes.get(i).getEachObjectiveFitness().clone();
		double[] y = this.rankChromosomes.get(j).getEachObjectiveFitness().clone();
		
		double temp = 0;
		double square_of_distance = 0;
		for (int k = 0; k < x.length; k++) {
			temp = x[k] - y[k];
			square_of_distance = square_of_distance + (temp * temp); 
		}
		
		return Math.sqrt(square_of_distance); 
	}
	
	private double getSumOfDistance(){
		double SumOfDistance = 0;
		int size =  rankChromosomes.size();
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if(i!=j){
					SumOfDistance+=Euclidean_distance(i,j);
				}
			}
		}
		return SumOfDistance;
	}
	
	public void setTau_Share(){
		double Average_distance = getSumOfDistance();
		this.Tau_Share = Average_distance/this.rankChromosomes.size();
	}
	
	public double getTau_Share(){
		return this.Tau_Share;
	}
	
	
}
