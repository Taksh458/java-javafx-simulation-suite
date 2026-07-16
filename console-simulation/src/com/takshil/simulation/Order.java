package com.takshil.simulation;

public final class Order {
    private final int id;
    private final int arrivalTime;
    private final int preparationMinutes;
    private final int cookingMinutes;
    private OrderState state;

    public Order(int id, int arrivalTime, int preparationMinutes, int cookingMinutes) {
        if (id <= 0 || arrivalTime < 0 || preparationMinutes <= 0 || cookingMinutes <= 0) {
            throw new IllegalArgumentException("Order values must be positive and arrival cannot be negative.");
        }
        this.id = id;
        this.arrivalTime = arrivalTime;
        this.preparationMinutes = preparationMinutes;
        this.cookingMinutes = cookingMinutes;
        this.state = OrderState.RECEIVED;
    }

    public int getId() { return id; }
    public int getArrivalTime() { return arrivalTime; }
    public int getPreparationMinutes() { return preparationMinutes; }
    public int getCookingMinutes() { return cookingMinutes; }
    public OrderState getState() { return state; }
    public void setState(OrderState state) { this.state = state; }
}
