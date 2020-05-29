package controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import model.*;

public class Controller {

    @FXML
    RadioButton selectMoveId;
    @FXML
    RadioButton ellipseId;
    @FXML
    RadioButton rectangleId;
    @FXML
    RadioButton lineId;
    @FXML
    ColorPicker colorPicker;
    @FXML
    Button deleteId;
    @FXML
    Button cloneId;
    @FXML
    Pane areaId;
    @FXML
    ToggleGroup groupAction;
    @FXML
    VBox Box;


    double startX = 0;
    double startY = 0;
    Shape cShape = null;

    @FXML
    public void initialize() {

        Drawing drawing = new Drawing();
        Box.setSpacing(5);
        colorPicker.setValue(Color.RED);
        groupAction.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                if (newValue == selectMoveId) {
                    deleteId.setDisable(false);
                    cloneId.setDisable(false);
                } else {
                    deleteId.setDisable(true);
                    cloneId.setDisable(true);
                }
            }
        });
        ellipseId.setSelected(true);

        areaId.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                startX = event.getX();
                startY = event.getY();
                if (lineId.isSelected()) {
                    cShape = new Line(event.getX(), event.getY(), event.getX(), event.getY(), colorPicker.getValue());
                    drawing.ajoutShape(cShape);
                    areaId.getChildren().add(createViewFromShape(cShape));
                } else if (rectangleId.isSelected()) {
                    cShape = new Rectangle(event.getX(), event.getY(), event.getX(), event.getY(), colorPicker.getValue());
                    drawing.ajoutShape(cShape);
                    areaId.getChildren().add(createViewFromShape(cShape));
                } else if (ellipseId.isSelected()) {
                    cShape = new Ellipse(event.getX(), event.getY(), event.getX(), event.getY(), colorPicker.getValue());
                    drawing.ajoutShape(cShape);
                    areaId.getChildren().add(createViewFromShape(cShape));
                }
            }
        });

        areaId.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (cShape != null && !selectMoveId.isSelected()) {
                    if (cShape instanceof Line) {
                        cShape.setXEnd(event.getX());
                        cShape.setYEnd(event.getY());
                        return;
                    }
                    double dy = event.getY() - startY;
                    double dx = event.getX() - startX;

                    System.out.println("dx= " + dx);


                    if (dy < 0) {
                        cShape.setYEnd(startY);
                        cShape.setYStart(event.getY());
                    } else {
                        cShape.setYEnd(event.getY());
                    }

                    if (dx < 0) {
                        cShape.setXEnd(startX);
                        cShape.setXStart(event.getX());
                    } else {
                        cShape.setXEnd(event.getX());
                    }
                }
            }
        });

        areaId.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                startX = 0;
                startY = 0;
                cShape = null;
            }
        });

    }

    private javafx.scene.shape.Shape createViewFromShape(Shape shape) {
        javafx.scene.shape.Shape view = null;
        if (shape instanceof Line) {
            javafx.scene.shape.Line line = new javafx.scene.shape.Line(shape.getXStart(), shape.getYStart(), shape.getXEnd(), shape.getYEnd());
            line.endXProperty().bind(shape.xEndProperty());
            line.endYProperty().bind(shape.yEndProperty());
            line.startXProperty().bind(shape.xStartProperty());
            line.strokeProperty().bind(shape.fColorProperty());
            line.startYProperty().bind(shape.yStartProperty());
            line.setStrokeWidth(1);
            view = line;
        } else if (shape instanceof Ellipse) {
            javafx.scene.shape.Ellipse ellipse = new javafx.scene.shape.Ellipse(shape.getXStart(), shape.getYStart(), 0, 0);
            ellipse.radiusXProperty().bind(((Ellipse) shape).width());
            ellipse.radiusYProperty().bind(((Ellipse) shape).height());
            ellipse.setStroke(Color.RED);
            ellipse.centerXProperty().bind(shape.xStartProperty());
            ellipse.centerYProperty().bind(shape.yStartProperty());
            ellipse.strokeProperty().bind(shape.sColorProperty());
            view = ellipse;
        }else if (shape instanceof Rectangle) {
            javafx.scene.shape.Rectangle rectangle = new javafx.scene.shape.Rectangle(shape.getXStart(), shape.getYStart(), shape.getXEnd(), shape.getYEnd());
            rectangle.widthProperty().bind(((Rectangle) shape).width());
            rectangle.heightProperty().bind(((Rectangle) shape).height());
            rectangle.setStroke(Color.RED);
            rectangle.xProperty().bind(shape.xStartProperty());
            rectangle.yProperty().bind(shape.yStartProperty());
            rectangle.setStrokeWidth(1);
            view = rectangle;
        }
        view.fillProperty().bind(shape.fColorProperty());
        view.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (selectMoveId.isSelected()) {
                    startX = event.getX();
                    startY = event.getY();
                    cShape = shape;
                }
            }
        });
        return view;
    }
}
