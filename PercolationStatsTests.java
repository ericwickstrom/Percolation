import static org.junit.Assert.*;

import org.junit.Test;

public class PercolationStatsTests {
	
	
	// N = 200, T = 100
	@Test
	public void mean200n100t() {
		PercolationStats ps = new PercolationStats(200, 100);
		assertEquals(0.592, ps.mean(), 0.01);
	}
	
	@Test
	public void stddev200n100t() {
		PercolationStats ps = new PercolationStats(200, 100);
		assertEquals(0.009, ps.stddev(), 0.01);
	}
	
	@Test
	public void confidenceLo200n100t() {
		PercolationStats ps = new PercolationStats(200, 100);
		assertEquals(0.591, ps.confidenceLo(), 0.001);
	}
	
	@Test
	public void confidenceHi200n100t() {
		PercolationStats ps = new PercolationStats(200, 100);
		assertEquals(0.594, ps.confidenceHi(), 0.001);
	}
	
	// N = 2, T = 10000
	@Test
	public void mean2n10000t() {
		PercolationStats ps = new PercolationStats(2, 10000);
		assertEquals(0.6669, ps.mean(), 0.01);
	}
	
	@Test
	public void stddev2n10000t() {
		PercolationStats ps = new PercolationStats(2, 10000);
		assertEquals(0.1177, ps.stddev(), 0.01);
	}
	
	@Test
	public void confidenceLo2n10000t() {
		PercolationStats ps = new PercolationStats(2, 10000);
		assertEquals(0.664, ps.confidenceLo(), 0.01);
	}
	
	@Test
	public void confidenceHi2n10000t() {
		PercolationStats ps = new PercolationStats(2, 10000);
		assertEquals(0.669, ps.confidenceHi(), 0.01);
	}
}
