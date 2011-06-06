package org.drools.planner.examples.puzzle;

import org.drools.planner.config.XmlSolverConfigurer;
import org.drools.planner.core.Solver;

public class PuzzleSolver {
	public static void main(String[] args) {
		final XmlSolverConfigurer configurer = new XmlSolverConfigurer();
		configurer.configure("/Puzzle.xml");
		final PuzzleSolution solution = new PuzzleSolution();
		final Solver solver = configurer.buildSolver();
		solver.setStartingSolution(solution);
		solver.solve();
		final PuzzleSolution best = (PuzzleSolution) solver.getBestSolution();
		System.out.println("Starting solution: " + solution.getPuzzle());
		System.out.println("Best solution: " + best.getPuzzle());
		System.out.printf("Done in %dms%n", solver.getTimeMillisSpend());
	}

}
