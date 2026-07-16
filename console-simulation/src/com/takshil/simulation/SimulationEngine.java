package com.takshil.simulation;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public final class SimulationEngine {
    private final PriorityQueue<SimulationEvent> events = new PriorityQueue<>();
    private final Queue<Order> ovenQueue = new ArrayDeque<>();
    private final List<String> log = new ArrayList<>();
    private boolean ovenBusy = false;

    public List<String> run(Collection<Order> orders) {
        events.clear();
        ovenQueue.clear();
        log.clear();
        ovenBusy = false;

        for (Order order : orders) {
            events.add(new SimulationEvent(order.getArrivalTime(), EventType.ARRIVAL, order));
        }

        while (!events.isEmpty()) {
            SimulationEvent event = events.poll();
            switch (event.type()) {
                case ARRIVAL -> receive(event);
                case PREPARATION_COMPLETE -> queueForOven(event);
                case COOKING_COMPLETE -> completeCooking(event);
            }
        }
        return List.copyOf(log);
    }

    private void receive(SimulationEvent event) {
        Order order = event.order();
        order.setState(OrderState.PREPARING);
        record(event.time(), order, "arrived; preparation started");
        events.add(new SimulationEvent(
                event.time() + order.getPreparationMinutes(),
                EventType.PREPARATION_COMPLETE,
                order));
    }

    private void queueForOven(SimulationEvent event) {
        Order order = event.order();
        order.setState(OrderState.WAITING_FOR_OVEN);
        ovenQueue.add(order);
        record(event.time(), order, "preparation complete; joined oven queue");
        startNextOrder(event.time());
    }

    private void startNextOrder(int currentTime) {
        if (ovenBusy || ovenQueue.isEmpty()) return;
        Order next = ovenQueue.remove();
        ovenBusy = true;
        next.setState(OrderState.COOKING);
        record(currentTime, next, "entered oven");
        events.add(new SimulationEvent(
                currentTime + next.getCookingMinutes(),
                EventType.COOKING_COMPLETE,
                next));
    }

    private void completeCooking(SimulationEvent event) {
        Order order = event.order();
        order.setState(OrderState.COMPLETED);
        ovenBusy = false;
        record(event.time(), order, "cooking complete");
        startNextOrder(event.time());
    }

    private void record(int time, Order order, String action) {
        log.add(String.format(
                "t=%02d | order=%d | %-22s | queue=%d | oven=%s",
                time,
                order.getId(),
                action,
                ovenQueue.size(),
                ovenBusy ? "busy" : "idle"));
    }
}
