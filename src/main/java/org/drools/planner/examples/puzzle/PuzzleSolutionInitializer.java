package org.drools.planner.examples.puzzle;

import java.util.Random;

import org.apache.log4j.Logger;
import org.drools.planner.core.localsearch.LocalSearchSolverScope;
import org.drools.planner.core.solution.initializer.StartingSolutionInitializer;
import org.drools.planner.core.solver.AbstractSolverScope;
import org.drools.planner.examples.puzzle.Puzzle.Direction;

public class PuzzleSolutionInitializer implements StartingSolutionInitializer {

	private static Logger log = Logger.getLogger(PuzzleSolutionInitializer.class);
	private static final int SHUFFLE_COUNT = 40;

	private PuzzleSolution getCurrentSolution(final LocalSearchSolverScope scope) {
		return (PuzzleSolution) scope.getWorkingSolution();
	}
	
	@Override
	public void initializeSolution(final AbstractSolverScope arg0) {
		final LocalSearchSolverScope scope = (LocalSearchSolverScope) arg0;
		final Puzzle p = new Puzzle();
		final Random rnd = new Random();
		p.initHard1();
		/*int moves = SHUFFLE_COUNT;
		while (moves > 0) {
			Direction d = Direction.values()[rnd.nextInt(4)];
			if (p.isMovable(d)) {
				p.move(d);
				moves--;
			}
		}*/
		
		log.info("Starting with puzzle " + p);
		getCurrentSolution(scope).initialize(p, scope);
	}

	@Override
	public boolean isSolutionInitialized(final AbstractSolverScope arg0) {
		return getCurrentSolution((LocalSearchSolverScope) arg0).isInitialized();
	}
	
}
