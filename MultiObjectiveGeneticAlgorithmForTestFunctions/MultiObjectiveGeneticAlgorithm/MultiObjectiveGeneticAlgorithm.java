package MultiObjectiveGeneticAlgorithm;

import java.util.ArrayList;

import GeneticAlgorithm.GeneticAlgorithm;
import GeneticAlgorithm.GeneticAlgorithmSetting;
import MOproblem.MultiObjective;
import initial.Chromosome;
import initial.generateRandomChromosome;

public class MultiObjectiveGeneticAlgorithm {
	private ArrayList<Chromosome> m_chromosome = null;
	private int m_chromosomeNumber = GeneticAlgorithmSetting.chromosomeSize;
	
	
	public MultiObjectiveGeneticAlgorithm(){
		
	}
	
	public void MultiObjectiveGeneticAlgorithmProcess(){
		int iteration = 0;
		initial();
		while(iteration < GeneticAlgorithmSetting.iteration){
			MultiObjectiveProcess(iteration);
			GeneticAlgorithm();
			System.out.println("iteration " +iteration+" done.");
			iteration++;
		}
	}
	
	public void initial() {
		// TODO Auto-generated method stub
		generateRandomChromosome grc = new generateRandomChromosome();
		m_chromosome = new ArrayList<Chromosome>();
		for (int i = 0; i < m_chromosomeNumber ; i++) {
			m_chromosome.add(grc.generateRandomSulution());
		}
	}

	public void MultiObjectiveProcess(int iteration){
		MultiObjective Mo = new MultiObjective();
		Mo.feedChromosomeInMultiObjectiveProblem(m_chromosome);
		Mo.MultiObjectiveProgress(iteration); // into
		m_chromosome = Mo.getChromosome();
	}
	
	public void GeneticAlgorithm(){
		GeneticAlgorithm ga = new GeneticAlgorithm();
		ga.setChromosome(m_chromosome);
		ga.GAprocess();
		m_chromosome = ga.getChromosome();
	}
	
	public ArrayList<Chromosome> getChromosome(){
		return this.m_chromosome;
	}
	
	public static void main(String src[]){
		MultiObjectiveGeneticAlgorithm moga = new MultiObjectiveGeneticAlgorithm();
		moga.MultiObjectiveGeneticAlgorithmProcess();
	}
}
