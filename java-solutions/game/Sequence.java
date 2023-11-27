package game;

public class Sequence {
    private int beginI;
    private int beginJ;
    private int endI;
    private int endJ;

    public Sequence(int beginI, int beginJ, int endI, int endJ) {
        this.beginI = beginI;
        this.beginJ = beginJ;
        this.endI = endI;
        this.endJ = endJ;
    }

    public int getBeginI() {
        return beginI;
    }

    public int getBeginJ() {
        return beginJ;
    }

    public int getEndI() {
        return endI;
    }

    public int getEndJ() {
        return endJ;
    }

    public void setBeginI(int beginI) {
        this.beginI = beginI;
    }

    public void setBeginJ(int beginJ) {
        this.beginJ = beginJ;
    }

    public void setEndI(int endI) {
        this.endI = endI;
    }

    public void setEndJ(int endJ) {
        this.endJ = endJ;
    }
}
