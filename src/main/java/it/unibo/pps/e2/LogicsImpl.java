package it.unibo.pps.e2;

public class LogicsImpl implements Logics {
	
	private final Pieces pawn;
	private final Pieces knight;
	private final Board board;
	 
    public LogicsImpl(final Board board){
    	this.board = board;
        this.pawn = new Pawn(this.board.randomEmptyPosition());
		this.board.placePieceInBoard(this.pawn);
        this.knight = new Knight(this.board.randomEmptyPosition());
		this.board.placePieceInBoard(this.knight);
    }

	public LogicsImpl(final Board board, final Pieces knight, final Pieces pawn) {
		this.board = board;
		this.knight = knight;
		this.board.placePieceInBoard(knight);
		this.pawn = pawn;
		this.board.placePieceInBoard(pawn);
	}

	@Override
	public boolean hit(final int row, final int col) {
		Pair<Integer, Integer> hitPosition = new Pair<>(row, col);
		if (this.board.isPositionOutOfBoard(hitPosition)) {
			throw new IndexOutOfBoundsException();
		}
		if (this.knight.moveAllowed(row, col)) {
			this.knight.setPosition(hitPosition);
			return this.pawn.positionEquals(this.knight.getPosition());
		}
		return false;
	}

	@Override
	public boolean hasKnight(int row, int col) {
		return this.knight.positionEquals(new Pair<>(row,col));
	}

	@Override
	public boolean hasPawn(int row, int col) {
		return this.pawn.positionEquals(new Pair<>(row,col));
	}
}
