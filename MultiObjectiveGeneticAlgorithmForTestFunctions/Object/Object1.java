package Object;

public class Object1 implements Objects {
	private double m_Fitness = 0;
	
	
	public void setFitness(double fitness){
		this.m_Fitness = fitness;
	}
	
	public double getFitness(){
		return this.m_Fitness;
	}
	
	public boolean IsBetterThanParetoFront(double paretoFrontChromosome, double NewChromosome){
		if(NewChromosome < paretoFrontChromosome){
			return true;
		}else{
			return false;
		}
	}
}
