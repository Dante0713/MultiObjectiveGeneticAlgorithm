package GeneticAlgorithm;

import java.util.ArrayList;
import java.util.Random;

import initial.Chromosome;

public class Selection {
	public int[] Crossover_Roulette_wheel_selection(double[] rouletteWheel, Random rnd, int listSize){
		int[] selectedList = new int[listSize];
		double total = rouletteWheel[rouletteWheel.length-1];
		double pickedKey = 0.0d;
		for (int index = 0; index < selectedList.length; index++) {
			
			pickedKey = rnd.nextDouble();
			int i = 0;boolean flag = true;
			for (i = 0; i < rouletteWheel.length && flag; i++) {
				if( pickedKey <= (rouletteWheel[i] / total )){
					System.out.println("i = "+ i +"\tindex["+index+"] pickedKey: "+ pickedKey+"\t percentage: "+rouletteWheel[i] / total);
					selectedList[index] = i;
					flag=false;
					
				}else{
					i++;
				}
			}
		}

		return selectedList;        
	}
	
	public void NextOffspring_EliteSelection(int amount, ArrayList<Chromosome> chr){
		
	}
	
}	
