/*
 *  ============================================================================================
 *  NestedShape.java : A shape that is a NestedShape
 *  Name: Tasal Amad
 *  YOUR UPI: tama956
 *  ============================================================================================
 */


import java.util.ArrayList;
import java.awt.*;

public class NestedShape extends RectangleShape {
    private ArrayList<Shape> innerShapes = new ArrayList<Shape>();

    public NestedShape () {
        super();
        createInnerShape(0, 0, this.width/2, this.height/2, this.color, PathType.BOUNCE, this.text, ShapeType.RECTANGLE);
    }

    public NestedShape(int x, int y, int w, int h, int mw, int mh, Color c, PathType pt, String t) {
        super(x, y, w, h, mw, mh, c, pt, t);
        createInnerShape(0,0,this.width/2, this.height/2, c,PathType.BOUNCE, t, ShapeType.RECTANGLE);
    }
    public NestedShape(int w, int h) {
        super(0, 0, w, h, DEFAULT_MARGIN_WIDTH, DEFAULT_MARGIN_HEIGHT, Color.black, PathType.BOUNCE, "");
    }

    public Shape createInnerShape(int x, int y, int w, int h, Color c, PathType pt, String text, ShapeType st) {
        Shape shape = null;

        if (st == ShapeType.RECTANGLE) {
            shape = (Shape) new RectangleShape(x, y, w, h, this.width, this.height, c, pt, text);
        }
        else if (st == ShapeType.OVAL) {
            shape = (Shape) new OvalShape(x, y, w, h, this.width, this.height, c, pt, text);
        }
        else if (st == ShapeType.NESTED) {
            shape = (Shape) new NestedShape(x, y, w, h, this.width, this.height, c, pt, text);
        }
        shape.setParent(this);
        innerShapes.add(shape);
        return shape;
    }

    public Shape getInnerShapeAt(int index) {
        return innerShapes.get(index);
    }

    public int getSize() {
        return innerShapes.size();
    }

    public int indexOf(Shape s) {
        return innerShapes.indexOf(s);
    }

    public void add(Shape s) {
        s.setParent(this);
        innerShapes.add(s);
    }

    public void remove(Shape s) {
        s.setParent(null);
        innerShapes.remove(s);
    }

    public ArrayList<Shape> getAllInnerShapes(){
        return innerShapes;
    }

    public void setWidth(int w) {
        width = w;
        for (Shape shape : innerShapes) {
            shape.marginWidth = w;
        }
    }

    public void setHeight(int h) {
        height = h;
        for (Shape shape : innerShapes) {
            shape.marginHeight = h;
        }
    }

    public void setColor(Color c) {
        color = c;
        for (Shape shape : innerShapes){
            shape.color = c;
        }
    }

    public void setText(String t) {
        text = t;
        for (Shape shape : innerShapes){
            shape.text = t;
        }
    }

    public void draw(Painter painter) {
        painter.setPaint(Color.black);
        painter.drawRect(x, y, width, height);
        painter.translate(x, y);
        for (Shape shape : innerShapes) {
            shape.draw(painter);
        }
        painter.translate(-x, -y);
    }

    public void move() {
        super.move();
        for (Shape shape : innerShapes){
            shape.move();
        }
    }
}
