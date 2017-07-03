package initial;

public class Chromosome implements Comparable<Chromosome> {
	private double[] m_DoubleChromosome = null;
	private double[] m_Fitness = null;
	private double m_possibility = 0;
	
	public Chromosome setInfo(double[] doubleChromosome,double[] fitness,double possibility){
		this.m_DoubleChromosome = doubleChromosome;
		this.m_Fitness = fitness;
		this.m_possibility = possibility;
		return this;
	}
	
	public Chromosome setMutationChromosome(double[] doubleChromosome){
		this.m_DoubleChromosome = doubleChromosome;
		return this;
	}
	
	public void setDoubleChromosome(double[] doubleChromosome){
		this.m_DoubleChromosome = doubleChromosome;
	}
	
	public double[] getDoubleChromosome(){
		return this.m_DoubleChromosome;
	}
	
	public void setObjectiveValue(double[] fitness){ //[]{fitness of objective 1, fitness of objective 2,..., fitness of objective n}
		this.m_Fitness = fitness;
	}
	
	public double[] getEachObjectiveFitness(){
		return this.m_Fitness;
	}
	
	public void setSelectionPossibility(double possibility){
		m_possibility = possibility;
	}
	
	public double getSelectionPossibility(){
		return this.m_possibility;
	}
	
	@Override
	public int compareTo(Chromosome  compareChr) {
		// TODO Auto-generated method stub
		double comparePossibility = ((Chromosome) compareChr).getSelectionPossibility();
		if(comparePossibility > this.getSelectionPossibility()){
			return -1;
		}else if(comparePossibility < this.getSelectionPossibility()){
			return 1;
		}else{
			return 0;
		}
		
	}

}
