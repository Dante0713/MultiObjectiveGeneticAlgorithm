package MOproblem;

import java.util.ArrayList;
import java.util.Collections;

import org.jfree.chart.XYLineAndShapeRender;
import org.jfree.ui.RefineryUtilities;

import ParetoFront.ParetoFronts;
import ParetoFront.RankChromosome;
import ParetoFront.generateRankingParetoFrontSet;
import ParetoFront.generateSharedFitness;
import fitness.calculateFitness;
import initial.Chromosome;

public class MultiObjective {
	private ArrayList<Chromosome> m_chromosome = null;
	// private ArrayList<Chromosome> m_paretoFront = null;
	private ArrayList<ParetoFronts> m_ParetoFrontList = null;
	private ArrayList<RankChromosome> m_RChromosome = null;
	private double[] m_ExtremeFitnessValue = null;
	private int limitSize = MultiObjectiveSetting.paretoFrontlimitSize;

	public MultiObjective() {
	}

	public void MultiObjectiveProgress(int iteration) {
		calculateChromosomefitness();
		findRankRaretoFront(); //into
		drawOrNot(iteration);
		generateSharedFitness();
		limit();
	}
	
	private void limit() {
		sort();
		while(this.m_chromosome.size() > limitSize){
			this.m_chromosome.remove(0);
		}
	}

	public void draw(int iteration) {
		final XYLineAndShapeRender demo = new XYLineAndShapeRender(iteration+" iteration pareto-front graph",
				getParetoInformationForDrawingGraph(), getRankList());
		demo.pack();
		RefineryUtilities.centerFrameOnScreen(demo);
		demo.setVisible(true);
	}

	public void feedChromosomeInMultiObjectiveProblem(ArrayList<Chromosome> chrs) {
		this.m_chromosome = chrs;
	}

	public void calculateChromosomefitness() {
		calculateFitness cF = new calculateFitness(this.m_chromosome);
		cF.Binh_and_Korn_function_Calculate();
		m_ExtremeFitnessValue = cF.getExtremeFitnessValue();
	}

	// public void findParetoFront(){
	// gnerateParetoFrontSet pfs = new gnerateParetoFrontSet(m_chromosome);
	// pfs.findingSet();
	// this.m_paretoFront = pfs.getSet();
	// }

	public void findRankRaretoFront() {
		generateRankingParetoFrontSet grpfs = new generateRankingParetoFrontSet(m_chromosome);
		grpfs.findingSet(); //into
		m_ParetoFrontList = grpfs.getSet();
		m_RChromosome = grpfs.getChromosome();
		
	}

	public void generateSharedFitness() {
		generateSharedFitness gsf = new generateSharedFitness();
		gsf.setExtremeFitnessValue(m_ExtremeFitnessValue);
		gsf.setChromosome(m_RChromosome);
		gsf.setParetoFront(m_ParetoFrontList);
		gsf.shareingFunction();
		m_RChromosome = gsf.getRankChromosome();
		m_chromosome = gsf.getChromosome();
	}

	public ArrayList<double[][]> getParetoInformationForDrawingGraph() {
		int objectiveNumbers = MultiObjectiveSetting.ObjectiveDecision.length;
		ArrayList<double[][]> ParetoInfo = new ArrayList<double[][]>();
		for (ParetoFronts pf : m_ParetoFrontList) {
			double[][] data = new double[pf.getListSize()][m_chromosome.get(0).getEachObjectiveFitness().length]; // fitness_X,
																												  // fitness_Y,
																												  // fitness_Z.....
			for (int i = 0; i < pf.getListSize(); i++) {
				System.out.println("index: "+ i +"\tmax: "+pf.getListSize()+"\t chr.size:"+m_chromosome.size()+"\t index list: "+pf.get_non_dominate_Chromosome_index_list().get(i));
				for (int j = 0; j < objectiveNumbers; j++) {
					data[i][j] = this.m_RChromosome.get(pf.get_non_dominate_Chromosome_index_list().get(i))
							.getEachObjectiveFitness()[j];
				}
			}
			ParetoInfo.add(data);
		}
		return ParetoInfo;
	}

	public int[] getRankList() {
		int[] rankList = new int[m_ParetoFrontList.size()];
		for (int i = 0; i < rankList.length; i++) {
			rankList[i] = m_ParetoFrontList.get(i).getRank();
		}
		return rankList;
	}

	public void show() {
		System.out.println("chromosome");
		for (Chromosome chromosome : m_chromosome) {
			for (double data : chromosome.getDoubleChromosome()) {
				System.out.print(data + "\t");
			}
			System.out.println();
		}
		//
		// System.out.println("Fitness");
		// for (chromosome chromosome : m_chromosome) {
		// for (int i = 0; i < chromosome.getFitness().length; i++) {
		// System.out.print(chromosome.getFitness()[i]+"\t");
		// }
		// System.out.println();
		// }

		// System.out.println("ParetoFront");
		// for (Chromosome paretoFront : m_ParetoFrontList) {
		// for (double paretoFitness : paretoFront.getFitness()) {
		// System.out.print(paretoFitness+"\t");
		// }
		// System.out.println();
		// }
	}

	public ArrayList<Chromosome> getChromosome() {
		return this.m_chromosome;
	}

	// public ArrayList<Chromosome> getParetoFront(){
	// return this.m_paretoFront;
	// }
	
	public void sort(){
		Collections.sort(m_chromosome);
	}
	
	public void drawOrNot(int iteration) {
		// TODO Auto-generated method stub
		if(iteration % 20 == 0){
			draw(iteration);
		}
	}

	public static void main(String src[]) {
		MultiObjective MO = new MultiObjective();
		MO.MultiObjectiveProgress(20);
	}

	
}
