package com.game.gaika.data;

public class Pair<T> {//extends IMapPoint{

    private T x;
    private T y;

    public Pair(T pX, T pY) {
        x = pX;
        y = pY;
    }

    public boolean equals(Object obj) {

        if (x.equals(((Pair) obj).x) && y.equals(((Pair) obj).y)) {
            return true;
        }
        return false;
    }

    //	@Override
    public T getMapX() {
        return this.x;
    }

    //	@Override
    public T getMapY() {
        return this.y;
    }

    //	@Override
    public Pair<T> getMapXY() {
        return new Pair(this.x, this.y);
    }

    public T getNear() {
        return this.x;
    }

    public T getFar() {
        return this.y;
    }

    public T getFirst() {
        return this.x;
    }

    public T getSecond() {
        return this.y;
    }

    public T getX() {
        return this.x;
    }

    public T getY() {
        return this.y;
    }
}
