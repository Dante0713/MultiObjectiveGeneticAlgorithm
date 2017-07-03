package initial;

import java.util.ArrayList;
import MOproblem.MultiObjectiveSetting;

public class generateRandomChromosome {
	int[] m_SettingData = null;
	public generateRandomChromosome(){
		m_SettingData = MultiObjectiveSetting.generateTestFunctionRandomChromosomeSettings;
	}
	
	public void InsertSettingData(int[] settingData){
		m_SettingData = settingData;
		if(settingData.length % 2 == 0){
			System.out.println("settingData Error");
		}else if(settingData[0] <= 0){
			System.out.println("data amount Error");
		}
	}
	
	public Chromosome generateRandomSulution(){
		int dimension = (m_SettingData.length) / 2;
		double[] chromosome = new double[dimension];
		Chromosome newChr = null;
		int dataIndex = this.m_SettingData.length;
		for (int chrIndex = 0, settingDataIndex = 0; settingDataIndex < dataIndex; settingDataIndex=settingDataIndex+dimension, chrIndex++) {
			chromosome[chrIndex] = m_SettingData[settingDataIndex] + (m_SettingData[(settingDataIndex+1)] - m_SettingData[settingDataIndex]) * Math.random();
//			System.out.println("chromosome["+chrIndex+"]:"+ chromosome[chrIndex] );
		}
		newChr = new Chromosome();
		newChr.setDoubleChromosome(chromosome.clone());
		return newChr;
	}
	
	public Chromosome generateRandomDimensionData(double[] Solution, int dimension){
		int randomDim = (int)Math.random()*dimension;
		Solution[randomDim] = (m_SettingData[randomDim*2] + (m_SettingData[(randomDim*2+1)] - m_SettingData[randomDim*2]) * Math.random());
		return new Chromosome().setMutationChromosome(Solution);
	}
	
	public static void main(String src[]){
		generateRandomChromosome grc = new generateRandomChromosome();
		ArrayList<Chromosome> m_Chr = new ArrayList<Chromosome>();
		int chromosomeNumber = 100;
		for (int i = 0; i < chromosomeNumber; i++) {
			m_Chr.add(grc.generateRandomSulution());
		}
	}
}
