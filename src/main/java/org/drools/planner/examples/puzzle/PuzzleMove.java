package org.drools.planner.examples.puzzle;

import org.drools.WorkingMemory;
import org.drools.planner.core.move.Move;
import org.drools.planner.examples.puzzle.Puzzle.Direction;

@SuppressWarnings("restriction")
public class PuzzleMove implements Move {

	Puzzle p;
	Direction d;
	
	public static class MoveNotifier {
		
	}
	
	public PuzzleMove(Puzzle p, Puzzle.Direction d) {
		this.p = p;
		this.d = d;
	}
	
	@Override
	public Move createUndoMove(final WorkingMemory arg0) {
		switch (d) {
			case UP:
				return new PuzzleMove(p, Direction.DOWN);
			case DOWN:
				return new PuzzleMove(p, Direction.UP);
			case LEFT:
				return new PuzzleMove(p, Direction.RIGHT);
			case RIGHT:
				return new PuzzleMove(p, Direction.LEFT);
		}
		return null;
	}

	@Override
	public void doMove(final WorkingMemory arg0) {
		p.move(d);
		arg0.insert(new MoveNotifier());
	}

	@Override
	public boolean isMoveDoable(final WorkingMemory arg0) {
		return p.isMovable(d);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((d == null) ? 0 : d.hashCode());
		result = prime * result + ((p == null) ? 0 : p.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PuzzleMove other = (PuzzleMove) obj;
		if (d != other.d)
			return false;
		if (p == null) {
			if (other.p != null)
				return false;
		} else if (!p.equals(other.p))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PuzzleMove [Puzzle=" + p + ", Direction=" + d + "]";
	}

}
