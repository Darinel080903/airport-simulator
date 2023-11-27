package com.example.airport.controller;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.dsl.components.FollowComponent;
import com.almasb.fxgl.entity.Entity;
import com.example.airport.factory.Factory;
import com.example.airport.monitor.Airport;
import com.example.airport.threads.Passenger;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.util.Duration;
import org.apache.commons.math3.distribution.ExponentialDistribution;

import java.net.URL;
import java.util.ResourceBundle;

public class GameController implements Initializable {
    private final Airport airport = new Airport();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        FXGL.getGameWorld().addEntityFactory(new Factory());
            FXGL.spawn("Tickets", 46, 120);
            FXGL.spawn("Police", 650, 100);
        for (int i = 0; i < 30; i++) {
            int finalI = i;
            double delay = 3 + Math.random() * 3;
            FXGL.getGameTimer().runOnceAfter(() -> {
                Entity Juan = FXGL.spawn("player", 20, 340+(finalI*10));
                Passenger passenger = new Passenger("Passenger " + (finalI +1), airport, Juan);
                System.out.println("Passenger " + (finalI +1) + " arrived");
                new Thread(passenger).start();
            }, Duration.seconds(delay + i ));
        }
    }
}