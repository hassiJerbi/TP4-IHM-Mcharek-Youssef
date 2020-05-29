package model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.paint.Color;

public class Shape {

    protected DoubleProperty xStart;
    protected DoubleProperty yStart;
    protected ObjectProperty<Color> fColor;
    protected DoubleProperty sWidth;
    protected ObjectProperty<Color> sColor;
    protected DoubleProperty xEnd;
    protected DoubleProperty yEnd;

    public Shape(double xStart, double yStart, double xEnd, double yEnd, Color fColor) {

        this.xStart = new SimpleDoubleProperty(xStart);
        this.yStart = new SimpleDoubleProperty(yStart);
        this.fColor = new SimpleObjectProperty<>(fColor);
        sWidth = new SimpleDoubleProperty(0);
        sColor = new SimpleObjectProperty<>(Color.GRAY);
        this.xEnd = new SimpleDoubleProperty(xEnd);
        this.yEnd = new SimpleDoubleProperty(yEnd);
        changeStyleForUnSelected();

    }


    public void changeStyleForUnSelected(){
        setSColor(Color.BLACK);
        setSWidth(2);
    }
/*
    public void setFColor(Color fColor) {
        this.fColor.set(fColor);
    }

    public double getSWidth() {
        return sWidth.get();
    }

    public DoubleProperty sWidthProperty() {
        return sWidth;
    }

    public Color getSColor() {
        return sColor.get();
    }


    public Color getFillColor() {
        return fColor.get();
    }

    public void changeStyleForSelected() {
        setSColor(Color.RED);
        setSWidth(1);
    }
*/

    public void setSWidth(double sWidth) {
        this.sWidth.set(sWidth);
    }

    public ObjectProperty<Color> sColorProperty() {
        return sColor;
    }

    public void setSColor(Color sColor) {
        this.sColor.set(sColor);
    }



    public DoubleProperty xStartProperty() {
        return xStart;
    }



    public DoubleProperty yStartProperty() {
        return yStart;
    }



    public DoubleProperty xEndProperty() {
        return xEnd;
    }

    public double getYEnd() {
        return yEnd.get();
    }

    public DoubleProperty yEndProperty() {
        return yEnd;
    }

    public void setXStart(double xStart) {
        this.xStart.set(xStart);
    }

    public double getXStart() {
        return xStart.get();
    }

    public void setYStart(double yStart) {
        this.yStart.set(yStart);
    }

    public double getYStart() {
        return yStart.get();
    }

    public void setXEnd(double xEnd) {
        this.xEnd.set(xEnd);
    }

    public double getXEnd() {
        return xEnd.get();
    }

    public void setYEnd(double yEnd) {
        this.yEnd.set(yEnd);
    }

    public ObjectProperty<Color> fColorProperty() {
        return fColor;
    }
}

