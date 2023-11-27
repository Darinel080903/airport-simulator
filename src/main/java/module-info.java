module com.example.airport {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.almasb.fxgl.all;
    requires commons.math3;

    opens assets.textures;
    opens com.example.airport to javafx.fxml;
    exports com.example.airport;
    exports com.example.airport.factory;
    opens com.example.airport.factory to javafx.fxml;
    exports com.example.airport.controller;
    opens com.example.airport.controller to javafx.fxml;
}