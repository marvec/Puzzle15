package org.drools.planner.examples.puzzle;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.drools.planner.core.move.Move;
import org.drools.planner.core.move.factory.AbstractMoveFactory;
import org.drools.planner.core.solution.Solution;
import org.drools.planner.examples.puzzle.Puzzle.Direction;

public class PuzzleMoveFactory  extends AbstractMoveFactory {

	private static Logger log = Logger.getLogger(PuzzleMoveFactory.class);
	
	@Override
	public List<Move> createMoveList(Solution arg0) {
		final Puzzle p = ((PuzzleSolution) arg0).getPuzzle();
		final List<Move> m = new ArrayList<Move>();
		for (Direction d: Direction.values()) {
			if (p.isMovable(d)) {
				if (log.isDebugEnabled()) {
					log.debug("Adding move " + d);
				}
				m.add(new PuzzleMove(p, d));
			}
			
		}
		return m;
	}
}
