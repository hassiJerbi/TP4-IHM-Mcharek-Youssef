package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Drawing {

    ObservableList<Shape> shapes;

    public Drawing(){

        shapes=FXCollections.observableArrayList();

    }

    public void ajoutShape(Shape shape){

        shapes.add(shape);

    }

}
