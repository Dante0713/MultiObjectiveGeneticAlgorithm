package fitness;

import java.util.ArrayList;

import initial.Chromosome;

public class calculateFitness {
	private ArrayList<Chromosome> m_Chr = null;
	private double[] extremeFitnessValue = null;
	
	public calculateFitness(ArrayList<Chromosome> chromosome){
		m_Chr = chromosome;
		extremeFitnessValue = new double[]{Double.MAX_VALUE,Double.MIN_VALUE,Double.MAX_VALUE,Double.MIN_VALUE}; //obj1_min, obj1_max, obj2_min, obj2_max
	}
	
	public void Binh_and_Korn_function_Calculate(){
		System.out.println("size: "+this.m_Chr.size());
		for (int i = 0; i < m_Chr.size(); i++) {
			if(m_Chr.get(i).getEachObjectiveFitness() == null){
				m_Chr.get(i).setObjectiveValue(new double[]{objective1(i),objective2(i)});
			}else{
			}
		}
	}
	
	public void Viennet_functionFitness(){
		System.out.println("size: "+this.m_Chr.size());
		for (int i = 0; i < m_Chr.size(); i++) {
			if(m_Chr.get(i).getEachObjectiveFitness() == null){
				m_Chr.get(i).setObjectiveValue(new double[]{objective3(i),objective4(i),objective5(i)});
			}else{
			}
		}
	}
	
	private double objective1(int i){
		double x = m_Chr.get(i).getDoubleChromosome()[0];
		double y = m_Chr.get(i).getDoubleChromosome()[1];
		
		double answer = 4 * x * x + 4 * y * y;
//		System.out.println("answer:" + answer);
		if(answer < extremeFitnessValue[0]){
			extremeFitnessValue[0] = answer;
		}
		if(answer > extremeFitnessValue[1]){
			extremeFitnessValue[1] = answer;
		}
		return answer;
	}
	
	private double objective2(int i){
		double x = m_Chr.get(i).getDoubleChromosome()[0];
		double y = m_Chr.get(i).getDoubleChromosome()[1];
		
		double answer = ((x - 5)*(x - 5)) + ((y - 5)*(y - 5));
//		System.out.println("answer:" + answer);
		if(answer < extremeFitnessValue[2]){
			extremeFitnessValue[2] = answer;
		}
		if(answer > extremeFitnessValue[3]){
			extremeFitnessValue[3] = answer;
		}
		return answer;
	}
	
	private double objective3(int i){
		double answer = 0;
		return answer;
	}
	
	private double objective4(int i){
		double answer = 0;
		return answer;
	}
	
	private double objective5(int i){
		double answer = 0;
		return answer;
	}
	
	public double[] getExtremeFitnessValue(){
		
		System.out.println("ExtremeFitnessValue");
		for (double d : extremeFitnessValue) {
			System.out.print(d+"\t");
		}
		System.out.println();
		return this.extremeFitnessValue.clone();
	}
	
	
}
