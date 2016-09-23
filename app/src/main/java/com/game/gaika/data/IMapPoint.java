package com.game.gaika.data;

public abstract class IMapPoint {
	abstract public int getMapX();

	abstract public int getMapY();

	public Pair getMapXY() {
		return new Pair(getMapX(), getMapY());
	}

	public boolean equalsMapPoint(IMapPoint p) {
		return this.getMapX() == p.getMapX() && this.getMapY() == p.getMapY();
	}

	public float getPixelX() {
		return getMapX() * 50 + 50;
	}

	public float getPixelY() {
		if (getMapX() % 2 == 1) {
			return getMapY() * 48 + 48 + 24;
		} else {
			return getMapY() * 48 + 48;
		}
	}

	public int getMapDistance(/*IMapPoint p1,*/ IMapPoint p2) {
GameMap map = GameDataManager.getInstance().getCurrentChapter().getGameMap();
		int x1 = this.getMapX();
		int y1 = this.getMapY();
		int x2 = p2.getMapX();
		int y2 = p2.getMapY();

		int retStep = 0;

		MapNode n1 = map.nodes[x1][y1];
		MapNode n2 = map.nodes[x2][y2];

		MapNode n = n1;

		while (!(n.x == n2.x && n.y == n2.y)) {

			int nextX = n.x;
			if (nextX < n2.x) {
				nextX++;
			} else if (nextX > n2.x) {
				nextX--;
			}

			int nextY = n.y;
			if (nextY < n2.y) {
				if (n.x == n2.x) {
					nextY++;
				} else if (n.x % 2 == 1) {
					nextY++;
				} else if (n.x % 2 == 0) {

				}

			} else if (nextY > n2.y) {
				if (n.x == n2.x) {
					nextY--;
				} else if (n.x % 2 == 1) {

				} else if (n.x % 2 == 0) {
					nextY--;
				}
			}

			retStep++;
			n = map.nodes[nextX][nextY];
		}
		return retStep;
	}
}
