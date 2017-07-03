package initial;

import Object.Object1;
import Object.Object2;

public class chromosomeObjectVersion {
	int[] m_IntegerChromosome = null;
	double[] m_DoubleChromosome = null;
	Object1 m_Obj_1 = null;
	Object2 m_Obj_2 = null;
	
	public chromosomeObjectVersion(){
		m_Obj_1 = new Object1();
		m_Obj_2 = new Object2();
	}
	
	public void setIntegerChromosome(int[] integerChromosome){
		this.m_IntegerChromosome = integerChromosome.clone();
	}
	
	public void setDoubleChromosome(double[] doubleChromosome){
		this.m_DoubleChromosome = doubleChromosome;
	}
	
	public int[] getIntegerChromosome(){
		return this.m_IntegerChromosome;
	}
	
	public double[] getDoubleChromosome(){
		return this.m_DoubleChromosome;
	}
	
	public void setObject1(Object1 object1){
		this.m_Obj_1 = object1;
	}
	
	public void setObject2(Object2 object2){
		this.m_Obj_2 = object2;
	}
	
	public Object1 getObject1(){
		return this.m_Obj_1;
	}
	
	public Object2 getObject2(){
		return this.m_Obj_2;
	}
}
