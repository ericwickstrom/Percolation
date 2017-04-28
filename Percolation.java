import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class Percolation {
	//holds components
	private WeightedQuickUnionUF mUnionFind;
	
	//holds whether a site is open or not
	private boolean[] mSites;
	
	// initial width of sites
	private final int HEIGHT;
	
	// number of sites
	private final int SIZE;
	
	// number of open sites
	private int mNumberOfOpenSites;
	
	//static locations
	private final int TOP;
	private final int BOTTOM;
	
	public Percolation(int n) throws IllegalArgumentException
	{
		if(n <= 0){
			throw new IllegalArgumentException();
		}

		HEIGHT = n;
		mNumberOfOpenSites = 0;
		
		// 1 for top, 1 for bottom, n*n for grid
		SIZE = n*n+2;
		
		//initialize sites array
		mSites = new boolean[SIZE];
		for(int i = 0; i < SIZE; i++){
			mSites[i] = false;
		}
		// SIZE-2 represents top site
		// SIZE-1 represents bottom site
		// These sites are initially open
		TOP = SIZE-2;
		BOTTOM = SIZE-1;
		mSites[TOP] = true;
		mSites[BOTTOM] = true;
		
		// initialize WeightedQuickUnionUF
		mUnionFind = new WeightedQuickUnionUF(SIZE);
		
	}
	
	private void checkIfSiteValid(int row, int col) throws IndexOutOfBoundsException{
		if(row < 1 || row > HEIGHT || col < 1 || col > HEIGHT){
			throw new IndexOutOfBoundsException("Invalid site: " + row + " " + col);
		}
	}

	// returns linear position of y,x
	private int translate(int row, int col){
		//checkIfSiteValid(row, col);
		return col-1 + ((row-1)*HEIGHT);
	}
	
	/*
	 * Opens a site, then connects to adjacent open sites.
	 * @param row Row of site.
	 * @param col Column of site.
	 */
	public void open(int row, int col) {
		checkIfSiteValid(row, col);
		int location = translate(row, col);
		// If site is already open, do nothing.
		// If site is closed, then open.
		if(!isOpen(location)){
			mSites[location] = true;
			mNumberOfOpenSites++;
			
			// Positions for sites above, below, left and right
			// of new site.
			int above = translate(row-1, col);
			int below = translate(row+1, col);
			int left = translate(row, col-1);
			int right = translate(row, col+1);
			
			// If row = 1, connect to TOP
			// If row != 1, connect to site above if open
			if(row == 1){
				mUnionFind.union(location, TOP);
			} else if(isOpen(above)) {
				mUnionFind.union(location, above);
			}
			
			// If row = HEIGHT, connect to BOTTOM
			// If row != HEIGHT, connect to site below if open
			if(row == HEIGHT){
				mUnionFind.union(location, BOTTOM);
			} else if(isOpen(below)){
				mUnionFind.union(location, below);
			}
			
			// Connect to open left site if not first column
			if(col != 1 && isOpen(left)){
				mUnionFind.union(location, left);
			}
			
			// Connect to open right if not last column
			if(col != HEIGHT && isOpen(right)){
				mUnionFind.union(location, right);
			}
		}
	}
	
	// Returns boolean for if two sites are connected
	private boolean isConnected(int row1, int col1, int row2, int col2){
		checkIfSiteValid(row1, col1);
		checkIfSiteValid(row2, col2);
		return mUnionFind.connected(translate(row1, col1), translate(row2, col2));
	}
	
	// returns boolean for if site is connected to TOP
	private boolean isConnectedToTop(int row, int col){
		checkIfSiteValid(row, col);
		return mUnionFind.connected(TOP, translate(row, col));
	}
	
	/*
	 * Checks if a site is connected to BOTTOM.
	 * @param row Row of site
	 * @param col Column of Site
	 * @return True if connected, false if not connected.
	 */
	private boolean isConnectedToBottom(int row, int col){
		checkIfSiteValid(row, col);
		return mUnionFind.connected(BOTTOM, translate(row, col));
	}
	
	/*
	 * Checks if a site is open.
	 * @param pos Linear position translated from row, column.
	 * @return True if open, false if not open.
	 */
	private boolean isOpen(int pos){
		if(pos < 0 || pos > SIZE){
			throw new IndexOutOfBoundsException();
		}
		return mSites[pos];
	}
	
	/*
	 * Returns whether or not a site is open.
	 * @param row Row of site.
	 * @param col Column of site.
	 * @Return True if site is open, false if site is closed.
	 */
	public boolean isOpen(int row, int col) {
		checkIfSiteValid(row, col);
		return mSites[translate(row, col)];
	}

	/*
	 * A site is full if it is connected to TOP.
	 * @param row Row of site.
	 * @param col Column of site.
	 * @return True if site is connected to TOP, false if not.
	 */
	public boolean isFull(int row, int col) {
		checkIfSiteValid(row, col);
		return mUnionFind.connected(translate(row, col), TOP);
	}

	/*
	 * Returns number of open sites.
	 * @return Number of open sites.
	 */
	public int numberOfOpenSites() {
		return mNumberOfOpenSites;
	}

	// Returns true if TOP is connected to BOTTOM
	public boolean percolates() {
		return mUnionFind.connected(TOP, BOTTOM);
	}
}
