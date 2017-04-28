import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

	private double mStddev;
	private double mMean;
	private double mConfidenceLo;
	private double mConfidenceHi;
	
	public static void main(String args[]){
		String mean = "mean                    = ";
		String stddev = "stddev                  = ";
		String ci = "95% confidence interval = ";
		int n = Integer.parseInt(args[0]);
		int t = Integer.parseInt(args[1]);
		PercolationStats ps = new PercolationStats(n, t);
		System.out.println(mean + ps.mean());
		System.out.println(stddev + ps.stddev());
		System.out.println(ci + "[" + ps.confidenceLo() + ", " + ps.confidenceHi() + "]");
	}
	
	/*
	 * Constructs object and runs statistical tests.
	 * @param n N is the size of the grid.
	 * @param trials The number of trials to run.
	 */
	public PercolationStats(int n, int trials){
		double[] thresholds = new double[trials];
		for(int i = 0; i < trials; i++){
			Percolation percolation = new Percolation(n);
			thresholds[i] = calculateThreshold(percolation, n);
		}
		mStddev = StdStats.stddev(thresholds);
		mMean = StdStats.mean(thresholds);
		mConfidenceLo = mMean - (1.96 * mStddev)/Math.sqrt(trials);
		mConfidenceHi = mMean + (1.96 * mStddev)/Math.sqrt(trials);
	}
	
	/*
	 * Provides one instance of a trial.
	 * Sites are opened at random until system percolates.
	 * @return Threshold, which is the percentage of open sites when system percolates.
	 */
	private double calculateThreshold(Percolation percolation, int n){
		while(!percolation.percolates()){
			
			int row = StdRandom.uniform(1, n+1);
			int col = StdRandom.uniform(1, n+1);
			percolation.open(row, col);
			
			
		}
		double threshold = (double) percolation.numberOfOpenSites() / (double) (n*n);
		return threshold;
	}
	
	
	public double mean() {
		return mMean;
	}

	
	public double stddev() {
		return mStddev;
	}

	
	public double confidenceLo() {
		return mConfidenceLo;
	}

	
	public double confidenceHi() {
		return mConfidenceHi;
	}

}
