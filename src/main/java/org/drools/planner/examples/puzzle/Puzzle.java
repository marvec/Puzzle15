package org.drools.planner.examples.puzzle;

import java.util.Arrays;

import org.apache.log4j.Logger;

public class Puzzle {
	private static final Logger log = Logger.getLogger(Puzzle.class);
	public enum Direction { UP, RIGHT, DOWN, LEFT };
	private int[][] p = new int[4][4];
	
	protected Puzzle() {
		for (int x = 0; x < 4; x++) {
			for (int y = 0; y < 4; y++) {
				p[x][y] = y * 4 + x + 1;
			}
		}
	}

	@Override
	public Puzzle clone() {
		Puzzle res = new Puzzle();
		for (int y = 0; y < 4; y++) {
			res.p[y] = Arrays.copyOf(p[y], 4);
		}
		
		return res;
	}
	
	private void swap(int x1, int y1, int x2, int y2) {
		int tmp = p[x1][y1];
		p[x1][y1] = p[x2][y2];
		p[x2][y2] = tmp;
	}
	
	protected int getSpaceIndex() {
		for (int i = 0; i < 16; i++) {
			if (p[i % 4][i / 4] == 16) {
				return i;
			}
		}
		throw new IllegalStateException("Space was not found: " + toString());
	}
	
	protected boolean isMovable(Direction d) {
		int i = getSpaceIndex();
		int px = i % 4;
		int py = i / 4;
		switch (d) {
			case UP: 
				if (py > 0) {
					return true;
				}
				break;
			case RIGHT:
				if (px < 3) {
					return true;
				}
				break;
			case DOWN:
				if (py < 3) {
					return true;
				}
				break;
			case LEFT:
				if (px > 0) {
					return true;
				}		
				break;
		}
		return false;
	}
	
	public Puzzle move(Direction d) {
		int i = getSpaceIndex();
		int px = i % 4;
		int py = i / 4;
		
		switch (d) {
			case UP: 
				if (py > 0) {
					swap(px, py, px, py - 1);
					return this;
				} else {
					return null;
				}
			case RIGHT:
				if (px < 3) {
					swap(px, py, px + 1, py);
					return this;
				} else {
					return null;
				}
			case DOWN:
				if (py < 3) {
					swap(px, py, px, py + 1);
					return this;
				} else {
					return null;
				}
			case LEFT:
				if (px > 0) {
					swap(px, py, px - 1, py);
					return this;
				} else {
					return null;
				}				
		}
		return null;
	}
	
	public static Direction getOppositeDirection(Direction d) {
		switch (d) {
			case UP: return Direction.DOWN;
			case DOWN: return Direction.UP;
			case LEFT: return Direction.RIGHT;
			case RIGHT: return Direction.LEFT;
		}
		return null;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(p[0]);
		result += Arrays.hashCode(p[1]);
		result += Arrays.hashCode(p[2]);
		result += Arrays.hashCode(p[3]);
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
		Puzzle other = (Puzzle) obj;
		boolean arrayEq = true;
		for (int i = 0; i < p.length; i++) {
			arrayEq = arrayEq && Arrays.equals(p[i], other.p[i]);
		}
		if (!arrayEq)
			return false;
		return true;
	}
	
	public int getScore() {
		int score = 0;
		for (int i = 0; i < 16; i++) {
			/*int tile = p[i % 4][i / 4];
			int x = Math.abs((tile % 4) - (i % 4));
			int y = Math.abs((tile / 4) - (i / 4));			
			score -= Math.round(Math.sqrt(x * x + y * y) * 1000);*/
			score -= Math.abs(p[i % 4][i / 4] - 1 - i);
		}
		return score;
	}

	@Override
	public String toString() {
		// Arrays.deepToString() uses different order so we have to build the string manually
		StringBuffer sb = new StringBuffer();
		for (int y = 0; y < 4; y++) {
			sb.append("[");
			for (int x = 0; x < 4; x++) {
				sb.append(p[x][y] + (x < 3 ? " " : ""));
			}
			sb.append("]" + (y < 3 ? " " : ""));
		}
		return "Puzzle [p=" + sb.toString() + ", score=" + getScore() + "]";
	}
}
