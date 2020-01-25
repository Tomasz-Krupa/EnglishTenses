package org.example;

import java.time.LocalDateTime;

public class OrderTO {
    private int productId;
    private int clientId;
    private Integer employeeId;
    private int qty;
    private LocalDateTime placedAt;

    public int getProductId() {
        return productId;
    }

    public OrderTO setProductId(int productId) {
        this.productId = productId;
        return this;
    }

    public int getClientId() {
        return clientId;
    }

    public OrderTO setClientId(int clientId) {
        this.clientId = clientId;
        return this;
    }

    public int getQty() {
        return qty;
    }

    public OrderTO setQty(int qty) {
        this.qty = qty;
        return this;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public OrderTO setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
        return this;
    }

    public LocalDateTime getPlacedAt() {
        return placedAt;
    }

    public OrderTO setPlacedAt(LocalDateTime placedAt) {
        this.placedAt = placedAt;
        return this;
    }

    @Override
    public String toString() {
        return "OrderTO{" +
                "productId=" + productId +
                ", clientId=" + clientId +
                ", employeeId=" + employeeId +
                ", qty=" + qty +
                ", placedAt=" + placedAt +
                '}';
    }
}
