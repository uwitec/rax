package erp.model;

import com.lowagie.text.Rectangle;

public class ExpressPos {
    public int X;
    public int Y;

    public ExpressPos(int x, int y) {
        this.X = x;
        this.Y = y;
    }
    public Rectangle toRectangle() {
        return new Rectangle(this.X, this.Y);
    }
}