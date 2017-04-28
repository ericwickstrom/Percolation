import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PercolationTests {

	@Test
	public void x1y1ShouldBe1() {
		Percolation percolation = new Percolation(10);
		assertEquals(0, percolation.translate(1, 1));
	}

	@Test
	public void x1y2ShouldBe10() {
		Percolation percolation = new Percolation(10);
		assertEquals(10, percolation.translate(2, 1));
	}
	
	@Test
	public void row1col1ShouldBeOpen(){
		Percolation percolation = new Percolation(10);
		percolation.open(1, 1);
		assertTrue(percolation.isOpen(1, 1));
	}
	
	@Test
	public void row2col2ShouldBeOpen(){
		Percolation percolation = new Percolation(10);
		percolation.open(2, 2);
		assertTrue(percolation.isOpen(2, 2));
	}
	
	@Test
	public void x1y1ShouldConnectToTop(){
		Percolation percolation = new Percolation(10);
		percolation.open(1, 1);
		assertTrue(percolation.isConnectedToTop(1, 1));
	}
	
	@Test
	public void row1col10ShouldConnectToTop(){
		Percolation percolation = new Percolation(10);
		percolation.open(1, 10);
		assertTrue(percolation.isConnectedToTop(1, 10));
	}
	
	@Test
	public void row2col1ShouldNotConnectToTop(){
		Percolation percolation = new Percolation(10);
		percolation.open(2, 1);
		assertFalse(percolation.isConnectedToTop(2, 1));
	}
	
	@Test
	public void row10col1ShouldConnectToBottom(){
		Percolation percolation = new Percolation(10);
		percolation.open(10, 1);
		assertTrue(percolation.isConnectedToBottom(10, 1));
	}
	
	@Test
	public void Row1Col2ShouldConnectToRow2Col2WhenOpened(){
		Percolation percolation = new Percolation(10);
		percolation.open(1, 2);
		percolation.open(2, 2);
		assertTrue(percolation.isConnected(1, 2, 2, 2));
	}

	@Test
	public void Row2Col2ShouldConnectToRow1Col2WhenOpened(){
		Percolation percolation = new Percolation(10);
		percolation.open(2, 2);
		percolation.open(1, 2);
		
		
		assertTrue(percolation.isConnected(1, 2, 2, 2));
	}
	
	@Test
	public void middleSiteShouldConnectToAdjacentSites(){
		Percolation percolation = new Percolation(3);
		percolation.open(1, 2);
		percolation.open(2, 1);
		percolation.open(2, 3);
		percolation.open(3, 2);
		percolation.open(2, 2);
		boolean top = percolation.isConnected(1, 2, 2, 2);
		boolean bottom = percolation.isConnected(3, 2, 2, 2);
		boolean left = percolation.isConnected(2, 1, 2, 2);
		boolean right = percolation.isConnected(2, 3, 2, 2);
		assertTrue(top && bottom && left && right);
	}
	
	@Test
	public void siteShouldBeFull(){
		Percolation percolation = new Percolation(10);
		percolation.open(1, 2);
		percolation.open(2, 2);
		percolation.open(3, 2);
		percolation.open(3, 3);
		assertTrue(percolation.isFull(3, 3));
	}
	
	@Test
	public void shouldPercolate(){
		Percolation percolation = new Percolation(3);
		percolation.open(1, 2);
		percolation.open(2, 2);
		percolation.open(3, 2);
		assertTrue(percolation.percolates());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void n0ShouldThrowIllegalArgumentException(){
		Percolation percolation = new Percolation(0);
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void shouldThrowIndexOutOfBoundsException(){
		Percolation percolation = new Percolation(2);
		percolation.checkIfSiteValid(-1, 1);
	}
}
