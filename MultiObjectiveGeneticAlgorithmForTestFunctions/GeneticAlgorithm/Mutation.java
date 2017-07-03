package GeneticAlgorithm;

import java.util.ArrayList;
import java.util.Random;

import initial.Chromosome;
import initial.generateRandomChromosome;

public class Mutation {
	private ArrayList<Chromosome> m_pool = null;
	private double m_mutationRate = GeneticAlgorithmSetting.mutationRate;
	private int dimension = GeneticAlgorithmSetting.dimension;
	
	public Mutation(){
		
	}
	
	public void setMutationPool(ArrayList<Chromosome> pool){
		this.m_pool = pool;
	}
	
	public void mutationProcess(){
		Random rnd = new Random();
		rnd.setSeed(751);
		double randomNum = 0;
		generateRandomChromosome grc = new generateRandomChromosome();
		
		for (int i = 0; i < m_pool.size(); i++) {
			System.out.println("m_mutationRate"+m_mutationRate);
			randomNum = rnd.nextDouble();
			System.out.println("randomNum: "+randomNum);
			if( rnd.nextDouble() < this.m_mutationRate ){
				m_pool.add( grc.generateRandomDimensionData(m_pool.get(i).getDoubleChromosome().clone(), dimension) );
			}
		}
	}
	
}
