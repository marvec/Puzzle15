package org.drools.planner.examples.puzzle;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.drools.planner.core.localsearch.LocalSearchSolverScope;
import org.drools.planner.core.score.Score;
import org.drools.planner.core.score.SimpleScore;
import org.drools.planner.core.solution.Solution;

public class PuzzleSolution implements Solution {

	private SimpleScore score = null;
	private Puzzle p = null;
	private static Logger log = Logger.getLogger(PuzzleSolution.class);

	public PuzzleSolution() {
		// noop
	}
	
	protected PuzzleSolution(final Puzzle arg0) {
		p = arg0;
	}
	
	@Override
	public Solution cloneSolution() {
		if (log.isDebugEnabled()) {
			log.debug("Cloning solution " + this);
		}
		final Solution s = new PuzzleSolution(p.clone());
		s.setScore(getScore());
		return s;
	}
	
	public Puzzle getPuzzle() {
		return p;
	}

	@Override
	public Collection<? extends Object> getFacts() {
		final List<Puzzle> f = new ArrayList<Puzzle>();
		f.add(p);
		return f;
	}

	@Override
	public Score<SimpleScore> getScore() {
		return score;
	}

	@Override
	public void setScore(@SuppressWarnings("rawtypes") final Score arg0) {
		score = (SimpleScore) arg0;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		PuzzleSolution other = (PuzzleSolution) obj;
		if (p == null) {
			if (other.p != null)
				return false;
		} else if (!p.equals(other.p))
			return false;
		return true;
	}

	protected boolean isInitialized() {
		return p != null;
	}
	
	@SuppressWarnings("restriction")
	protected void initialize(final Puzzle p, final LocalSearchSolverScope scope) {
		if (this.p != null) {
			throw new IllegalStateException("This solution has already been initialized to " + this.p);
		}
		this.p = p;
		scope.getWorkingMemory().insert(this.p);
		scope.getWorkingMemory().insert(new PuzzleMove.MoveNotifier());
		setScore(scope.calculateScoreFromWorkingMemory());
	}
}
