package Object;

public interface Objects {
	public void setFitness(double Value);
	public double getFitness();
	
	public boolean IsBetterThanParetoFront(double paretoFrontChromosome,double NewChromosome);
}
