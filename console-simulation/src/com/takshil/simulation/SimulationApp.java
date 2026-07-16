package com.takshil.simulation;

import java.util.List;

public final class SimulationApp {
    private SimulationApp() { }

    public static void main(String[] args) {
        List<Order> orders = List.of(
                new Order(1, 0, 3, 4),
                new Order(2, 2, 3, 4),
                new Order(3, 4, 2, 3),
                new Order(4, 5, 4, 2)
        );

        SimulationEngine engine = new SimulationEngine();
        System.out.println("Single-oven order simulation");
        System.out.println("-----------------------------------------------");
        engine.run(orders).forEach(System.out::println);
    }
}
