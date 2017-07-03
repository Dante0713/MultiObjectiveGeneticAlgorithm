package Object;

public class Object2 {
	private double m_Fitness = 0;
	
	public void setFitness(double Value){
		this.m_Fitness = Value;
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
