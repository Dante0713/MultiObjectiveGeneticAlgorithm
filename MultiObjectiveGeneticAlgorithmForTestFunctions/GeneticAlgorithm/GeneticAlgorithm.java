package GeneticAlgorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import MultiObjectiveGeneticAlgorithm.MultiObjectiveGeneticAlgorithm;
import initial.Chromosome;

public class GeneticAlgorithm implements GeneticAlgorithms{
	private ArrayList<Chromosome> m_Chromosomes = null;
	private ArrayList<Chromosome> m_SelectionPool = null;
	private Crossover cr;
	private Mutation mu;
	private Selection se;
	private int crossoversize = GeneticAlgorithmSetting.crossoverSize;
	
	public GeneticAlgorithm(){
		m_SelectionPool = new ArrayList<Chromosome>();
		cr = new Crossover();
		mu = new Mutation();
		se = new Selection();
	}
	
	public void setChromosome(ArrayList<Chromosome> Chrs){
		this.m_Chromosomes = Chrs;
	}
	
	@Override
	public void GAprocess() {
		crossover();
		System.out.println("crossover done.");
		mutation();
		System.out.println("mutation done.");
	}
	
//	private void show(){
//		for (Chromosome chromosome : m_SelectionPool) {
//			for (double d : chromosome.getDoubleChromosome()) {
//				System.out.print(d+"\t");
//			}
//			System.out.println();
//		}
//	}

	@Override
	public void crossover() {
		selection();
		suffle();
		cr.setChromosome(m_SelectionPool);
		cr.crossoverProcess();
		this.m_SelectionPool = cr.getpool();
		this.m_SelectionPool.addAll(m_Chromosomes);
	}

	private void suffle() {
		Collections.shuffle(m_SelectionPool);
	}

	@Override
	public void mutation() {
		mu.setMutationPool(this.m_SelectionPool);
		mu.mutationProcess();
	}

	@Override
	public void selection() {
		Random rnd = new Random();
		rnd.setSeed(947);
		
		double[] possibility = new double[m_Chromosomes.size()];
		for (int i = 0; i < possibility.length; i++) {
			possibility[i] = m_Chromosomes.get(i).getSelectionPossibility();
			if(i > 0){
				possibility[i] = possibility[i-1] + possibility[i];
			}
		}
		int list[] = se.Crossover_Roulette_wheel_selection(possibility, rnd, crossoversize);
		System.out.println("list.size: "+list.length);
		for (int i = 0; i < list.length; i++) {
			m_SelectionPool.add(this.m_Chromosomes.get(list[i]));
		}
		System.out.println("pool size:"+m_SelectionPool.size());
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	} 
	
	public ArrayList<Chromosome> getChromosome(){
		m_Chromosomes = this.m_SelectionPool;
		return this.m_Chromosomes;
	}
	
	public static void main(String[] src){
		MultiObjectiveGeneticAlgorithm moga = new MultiObjectiveGeneticAlgorithm();
		moga.initial();
		moga.MultiObjectiveProcess(20);
		ArrayList<Chromosome> m_Chr = moga.getChromosome();
		GeneticAlgorithm ga = new GeneticAlgorithm();
		ga.setChromosome(m_Chr);
		ga.GAprocess();
		
	}
}
