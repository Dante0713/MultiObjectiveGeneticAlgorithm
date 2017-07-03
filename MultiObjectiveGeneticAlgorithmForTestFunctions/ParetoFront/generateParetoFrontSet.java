package ParetoFront;

import java.util.ArrayList;
import MOproblem.MultiObjectiveSetting;
import initial.Chromosome;

public class generateParetoFrontSet {
	private ArrayList<Chromosome> m_ParetoFrontSet;
	private ArrayList<Chromosome> m_Chr;
	
	
	public generateParetoFrontSet(ArrayList<Chromosome> chromosome){
		m_Chr = chromosome;
		m_ParetoFrontSet = new ArrayList<Chromosome>();
	}
	
	public void findingSet(){
		normalWayParetoFront();
	}
	
	public void normalWayParetoFront(){
		int dominatedNumberCounterCheck = 0;
		int dimension = m_Chr.get(0).getEachObjectiveFitness().length;
		int paretoFrontCheckIndex = 0;
		int paretoFrontSize = 0;
		boolean addItIntoParetoFront = false;
		ArrayList<Chromosome> discardChromosomeInParetoFrontSet = new ArrayList<Chromosome>(); 
		
		System.out.println("---------------------------------------------------------------");
		
		for (Chromosome chromosome : m_Chr) {
			if(this.m_ParetoFrontSet.size() == 0){
				saveChromosomeInParetoFrontSet(chromosome);
			}else{
				paretoFrontSize = m_ParetoFrontSet.size();
				paretoFrontCheckIndex = 0;
				while(paretoFrontCheckIndex < paretoFrontSize){
						for (int j = 0; j < chromosome.getEachObjectiveFitness().length; j++) {
							if(NewChromosomeisBetter(m_ParetoFrontSet.get(paretoFrontCheckIndex).getEachObjectiveFitness()[j], chromosome.getEachObjectiveFitness()[j],j)){
								dominatedNumberCounterCheck++;
							}
						}
					if(dominatedNumberCounterCheck == dimension){
						System.out.println("compare chromosome: "+chromosome.getEachObjectiveFitness()[0]+"\t"+chromosome.getEachObjectiveFitness()[1]);
						discardChromosomeInParetoFrontSet = discardChromosomeInParetoFrontSet(discardChromosomeInParetoFrontSet, paretoFrontCheckIndex);
						paretoFrontCheckIndex--;
						paretoFrontSize -- ;
					}else{
						addItIntoParetoFront = true;
					}
					paretoFrontCheckIndex++;
					dominatedNumberCounterCheck = 0;
				}
				if(addItIntoParetoFront){
					saveChromosomeInParetoFrontSet(chromosome);
				}
			}
		}
	}
	
//	private void show(){
//		for (Chromosome chromosome : this.m_ParetoFrontSet) {
//			for (double fitness : chromosome.getFitness()) {
//				System.out.print(fitness+"\t");
//			}
//			System.out.println();
//		}
//	}
	
	private void saveChromosomeInParetoFrontSet(Chromosome chr) {
		System.out.println("add chromosome into ParetorFront: "+chr.getEachObjectiveFitness()[0]+"\t"+chr.getEachObjectiveFitness()[1]);
		m_ParetoFrontSet.add(chr);
//		show();
//		System.out.println("---------------------------------------------------------------");
	}

	private ArrayList<Chromosome> discardChromosomeInParetoFrontSet(ArrayList<Chromosome> discardChromosome, int index) {
		System.out.println("remove ParetoFrontSet["+index+"]:"+m_ParetoFrontSet.get(index).getEachObjectiveFitness()[0]+"\t"+m_ParetoFrontSet.get(index).getEachObjectiveFitness()[1]);
		discardChromosome.add(m_ParetoFrontSet.get(index));
		m_ParetoFrontSet.remove(index);
		
		System.out.println("after remove");
//		show();
//		System.out.println("---------------------------------------------------------------");
		return discardChromosome;
	}

	public boolean NewChromosomeisBetter(double paretoFrontChromosome, double NewChromosome, int decisionIndex){
		if(MultiObjectiveSetting.ObjectiveDecision[decisionIndex] == "small"){
			if(smallerIsBetter(paretoFrontChromosome, NewChromosome)){
				return true;
			}else{
				return false;
			}
		}else if(MultiObjectiveSetting.ObjectiveDecision[decisionIndex] == "large"){
			if(biggerIsBetter(paretoFrontChromosome, NewChromosome)){
				return true;
			}else{
				return false;
			}
		}else{
			System.out.println("Your Decision Setting Array was setting wrong!");
			return false;
		}
		
	}
	
	public boolean biggerIsBetter(double paretoFrontChromosome,double NewChromosome){
		if(NewChromosome > paretoFrontChromosome){
			return true;
		}else{
			return false;
		}
	}
	
	public boolean smallerIsBetter(double paretoFrontChromosome,double NewChromosome){
		if(NewChromosome < paretoFrontChromosome){
			return true;
		}else{
			return false;
		}
	}
	
	public ArrayList<Chromosome> getSet(){
		return m_ParetoFrontSet;
	}
	
}
