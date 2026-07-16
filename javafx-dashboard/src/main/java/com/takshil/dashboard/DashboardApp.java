package com.takshil.dashboard;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayDeque;
import java.util.Queue;

public class DashboardApp extends Application {
    private final Queue<String> queue = new ArrayDeque<>();
    private final ListView<String> eventLog = new ListView<>();
    private final Label ovenStatus = new Label("Oven: idle");
    private final Label queueStatus = new Label("Queue: 0 orders");
    private int nextOrder = 1;

    @Override
    public void start(Stage stage) {
        Label title = new Label("Single-oven simulation dashboard");
        title.getStyleClass().add("title");
        Label subtitle = new Label("JavaFX · Queue logic · Event-driven interface");
        subtitle.getStyleClass().add("subtitle");

        Button add = new Button("Add prepared order");
        Button process = new Button("Process next order");
        Button reset = new Button("Reset");

        add.setOnAction(event -> addOrder());
        process.setOnAction(event -> processOrder());
        reset.setOnAction(event -> reset());

        HBox controls = new HBox(10, add, process, reset);
        VBox header = new VBox(8, title, subtitle, new HBox(20, ovenStatus, queueStatus), controls);
        header.setPadding(new Insets(24));

        eventLog.setItems(FXCollections.observableArrayList());
        eventLog.setPlaceholder(new Label("Add an order to begin."));

        BorderPane root = new BorderPane();
        root.setTop(header);
        root.setCenter(eventLog);
        BorderPane.setMargin(eventLog, new Insets(0, 24, 24, 24));

        Scene scene = new Scene(root, 850, 560);
        scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
        stage.setTitle("JavaFX Simulation Dashboard");
        stage.setScene(scene);
        stage.show();
    }

    private void addOrder() {
        String order = "Order " + nextOrder++;
        queue.add(order);
        eventLog.getItems().add("Prepared: " + order + " joined the oven queue");
        refresh();
    }

    private void processOrder() {
        String order = queue.poll();
        if (order == null) {
            eventLog.getItems().add("No order available to process");
            refresh();
            return;
        }
        ovenStatus.setText("Oven: processing " + order);
        eventLog.getItems().add("Cooking complete: " + order);
        ovenStatus.setText("Oven: idle");
        refresh();
    }

    private void reset() {
        queue.clear();
        eventLog.getItems().clear();
        nextOrder = 1;
        ovenStatus.setText("Oven: idle");
        refresh();
    }

    private void refresh() {
        queueStatus.setText("Queue: " + queue.size() + (queue.size() == 1 ? " order" : " orders"));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
