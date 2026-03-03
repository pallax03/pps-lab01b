package it.unibo.pps.e2;

public class LogicsImpl implements Logics {

	private final Pair<Integer, Integer> pawn;
	private Pair<Integer, Integer> knight;
	private final Board board;
	 
    public LogicsImpl(final Board board){
    	this.board = board;
        this.pawn = this.randomEmptyPosition();
        this.knight = this.randomEmptyPosition();
    }

	private Pair<Integer, Integer> randomEmptyPosition() {
		Pair<Integer, Integer> newPosition = this.board.randomPosition();
		return this.pawn!=null && this.pawn.equals(newPosition) ? randomEmptyPosition() : newPosition;
	}

	public LogicsImpl(final Board board, final Pair<Integer, Integer> knight, final Pair<Integer, Integer> pawn) {
		this.board = board;
		this.knight = knight;
		this.pawn = pawn;;
	}

	@Override
	public boolean hit(final int row, final int col) {
		Pair<Integer, Integer> hitPosition = new Pair<>(row, col);
		if (this.board.isPositionOutOfBoard(hitPosition)) {
			throw new IndexOutOfBoundsException();
		}

		if (MoveStrategy.knight().moveAllowed(this.knight, hitPosition)) {
			this.knight = hitPosition;
			return this.pawn.equals(this.knight);
		}
		return false;
	}

	@Override
	public boolean hasKnight(int row, int col) {
		return this.knight.equals(new Pair<>(row,col));
	}

	@Override
	public boolean hasPawn(int row, int col) {
		return this.pawn.equals(new Pair<>(row,col));
	}
}
