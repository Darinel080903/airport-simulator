package com.example.airport.monitor;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.example.airport.factory.Factory;

public class Airport {
    private boolean ticketAreaFree = true;
    private boolean planeReady = false;
    private int passengersBoarded = 0;
    private int totalPassengers = 30;

    public synchronized void provideTicket(String passengerName, Entity Juan) throws InterruptedException {

        while (!ticketAreaFree) {
            wait();
        }

        ticketAreaFree = false;
        System.out.println(passengerName + " is in the ticket area");
        Thread.sleep(600);
        Juan.setPosition(130, 120);
        Thread.sleep((long) (1000 + Math.random() * 1000));
        Juan.setVisible(false);
        System.out.println(passengerName + " received the ticket");
        ticketAreaFree = true;
        notifyAll();
    }

    public synchronized void validateTicket(String passengerName, Entity Juan) throws InterruptedException {
        System.out.println(passengerName + " is validating the ticket");
        Juan.setVisible(true);
        Juan.setPosition(650, 120);
        Thread.sleep((long) (1000 + Math.random() * 1000));
        Juan.setVisible(false);
        System.out.println(passengerName + " validated the ticket");
    }

    public synchronized void boardPlane(String passengerName, Entity Juan) throws InterruptedException {
        passengersBoarded++;
        Thread.sleep((long) (1000 + Math.random() * 1000));
        System.out.println(passengerName + " boarded the plane");

        if (passengersBoarded == 3) {
            System.out.println("Plane is full. Ready to take off.");
            Thread.sleep(5000);
            System.out.println("Plane landed. Passengers are descending.");
            for (int i = 0; i < 3; i++) {
                final int passengerNumber = i + 1;
                new Thread(() -> {
                    try {
                        descendPassenger(Juan, passengerNumber);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }).start();
            }
            passengersBoarded = 0;
            planeReady = true;
            notifyAll();
        } else {
            while (!planeReady && totalPassengers > 0) {
                wait();
            }
        }
        totalPassengers--;
        if (totalPassengers == 0) {
            planeReady = false;
        }
    }

    private void descendPassenger(Entity Juan, int passengerNumber) throws InterruptedException {
        Juan.setVisible(true);
        Juan.setPosition(650, 400 + (passengerNumber*3));
        Juan.rotationZProperty().setValue(180);
        Thread.sleep((long) (1000 + Math.random() * 1000));
        Juan.setVisible(false);
        System.out.println("Passenger " + passengerNumber + " has descended.");
    }
}
