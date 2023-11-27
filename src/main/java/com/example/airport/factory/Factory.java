package com.example.airport.factory;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.dsl.components.FollowComponent;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.physics.box2d.dynamics.BodyType;
import com.example.airport.factory.types.Type;

public class Factory implements EntityFactory {

    @Spawns("player")
    public Entity newPlayer(SpawnData data) {
        FollowComponent followComponent = new FollowComponent();
        followComponent.setSpeed(1);
        return FXGL.entityBuilder(data)
                .type(Type.PLAYER)
                .view("player.png")
                .with(followComponent)
                .buildAndAttach();
    }

    @Spawns("Tickets")
    public Entity newTickets(SpawnData data) {
        PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.KINEMATIC);
        return FXGL.entityBuilder(data)
                .type(Type.TICKETS)
                .view("ticketSeller.png")
                .with(physics)
                .build();
    }

    @Spawns("Police")
    public Entity newPolice(SpawnData data) {
        PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.KINEMATIC);
        return FXGL.entityBuilder(data)
                .view("corruptPerson.png")
                .type(Type.POLICE)
                .with(physics)
                .build();
    }

}
