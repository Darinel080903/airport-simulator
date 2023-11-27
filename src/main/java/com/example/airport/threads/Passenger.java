package com.example.airport.threads;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.example.airport.factory.Factory;
import com.example.airport.monitor.Airport;

public class Passenger implements Runnable {
    private final Entity Juan;
    private String name;
    private Airport airport;

    public Passenger(String name, Airport airport, Entity Juan){
        this.name = name;
        this.airport = airport;
        this.Juan = Juan;
    }

    @Override
    public void run() {
        try {
            airport.provideTicket(name, Juan);
            airport.validateTicket(name, Juan);
            airport.boardPlane(name, Juan);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}