package ParetoFront;

import GeneticAlgorithm.GeneticAlgorithmSetting;

public class Sigma_Share_SolutionSpace {
	private double[] m_ExtremeFitnessValue = null;
	private int N = GeneticAlgorithmSetting.chromosomeSize;
	private int dimension = GeneticAlgorithmSetting.dimension;
	
	public Sigma_Share_SolutionSpace(double[] ExtremeFitnessValue){	//sqrt(sum(square(axis-distance)))/(2 * (p กิ q))
		this.m_ExtremeFitnessValue = ExtremeFitnessValue;
	}
	
	public double getSigma_Share(){
		double DimensionMaximumDistance = 0;
		double dividePart = 0;
		for (int k = 0; k < dimension; k++) {
			double add =  Math.pow(m_ExtremeFitnessValue[k*2]- m_ExtremeFitnessValue[k*2+1], 2);
			System.out.println("difference: "+ (m_ExtremeFitnessValue[k*2]- m_ExtremeFitnessValue[k*2+1]));
			System.out.println("distance: "+add);
			DimensionMaximumDistance = DimensionMaximumDistance + add;
		}
		DimensionMaximumDistance = Math.sqrt(DimensionMaximumDistance);
		dividePart = 2 * Math.pow(N, 1.0/dimension);
		
		return DimensionMaximumDistance/dividePart; 
	}
	
}
