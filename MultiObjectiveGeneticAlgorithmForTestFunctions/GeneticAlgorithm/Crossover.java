package GeneticAlgorithm;

import java.util.ArrayList;

import initial.Chromosome;

public class Crossover {
	private ArrayList<Chromosome> m_Chromosomes = null;
	private ArrayList<Chromosome> m_pool = null;
	public Crossover(){
		m_pool = new ArrayList<Chromosome>();
	}
	
	public void setChromosome(ArrayList<Chromosome> Chrs){
		this.m_Chromosomes  = Chrs;
	}
	
	public void crossoverProcess(){
		System.out.println("in Cross");
		int dimension = GeneticAlgorithmSetting.dimension-1;
		int crossIndex = 0;
		for (int i = 0; (i+2) < this.m_Chromosomes.size(); i = i+2) {
			crossIndex = (int) Math.round(Math.random()*dimension);
			for (int j = 0; j < dimension; j++) {
				if(j == crossIndex){
					newSelectionPool(i,i+1,j);
				}
			}
		}
		
		checkReduplicateChr();
	}
	
	private void checkReduplicateChr() {
		// TODO Auto-generated method stub
		int flag =0;
		int dimSize = GeneticAlgorithmSetting.dimension;
		for (int i = 0; i < this.m_pool.size(); i++) {
			for (int j = i+1; j < this.m_pool.size(); j++) {
				flag = 0;
				for (int j2 = 0; j2 < dimSize; j2++) {
					if(this.m_pool.get(i).getDoubleChromosome()[j2] == this.m_pool.get(j).getDoubleChromosome()[j2]){
						flag ++;
					}
				}
				if(flag == dimSize){
					this.m_pool.remove(j);
					j--;
				}
			}
		}
	}

	public void newSelectionPool(int index_i,int index_j, int crossoverDim){
		double a[] = m_Chromosomes.get(index_i).getDoubleChromosome();
		double b[] = m_Chromosomes.get(index_j).getDoubleChromosome();
		Chromosome offspring1 = new Chromosome();
		Chromosome offspring2 = new Chromosome();
		
		double temp = a[crossoverDim];
		a[crossoverDim] = b[crossoverDim];
		b[crossoverDim] = temp;
		
		offspring1.setDoubleChromosome(a);
		offspring2.setDoubleChromosome(b);
		
		this.m_pool.add(offspring1);
		this.m_pool.add(offspring2);
	}
	
	public ArrayList<Chromosome> getpool(){
		return this.m_pool;
	}
	
	public static void main(String src[]){
		int dimension = GeneticAlgorithmSetting.dimension-1;
		System.out.println(GeneticAlgorithmSetting.dimension-1);
		for (int i = 0; i < 10; i++) {
			System.out.println((int) Math.round(Math.random()*dimension));
		}
	}
}
