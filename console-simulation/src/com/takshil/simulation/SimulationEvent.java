package com.takshil.simulation;

public record SimulationEvent(int time, EventType type, Order order) implements Comparable<SimulationEvent> {
    public SimulationEvent {
        if (time < 0 || type == null || order == null) {
            throw new IllegalArgumentException("Event values are required.");
        }
    }

    @Override
    public int compareTo(SimulationEvent other) {
        int byTime = Integer.compare(this.time, other.time);
        if (byTime != 0) return byTime;
        return Integer.compare(this.order.getId(), other.order.getId());
    }
}
