package model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.paint.Color;

public class Ellipse extends Shape{
    public Ellipse(double xStart, double yStart, double xEnd, double yEnd, Color fColor) {

        super(xStart, yStart, xEnd, yEnd, fColor);
        height = new SimpleDoubleProperty(0);
        width = new SimpleDoubleProperty(0);
    }

    protected DoubleProperty width;
    protected DoubleProperty height;

    /*
    public double getWidth() {
        return width.get();
    }

    public double getHeight() {
        return height.get();
    }
    */
    public DoubleProperty width() {
        return width;
    }



    public DoubleProperty height() {
        return height;
    }

    public void setWidth(double width) {
        this.width.set(width);
    }

    public void setHeight(double height) {
        this.height.set(height);
    }

    @Override
    public void setXEnd(double xEnd) {
        super.setXEnd(xEnd);
        setWidth(Math.abs(getXEnd()-getXStart()));
    }

    @Override
    public void setYEnd(double yEnd) {
        super.setYEnd(yEnd);
        setHeight(Math.abs(getYEnd()-getYStart()));
    }
}