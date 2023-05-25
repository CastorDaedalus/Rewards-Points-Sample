package com.van.josh.rewardspoints.Constants;

public enum TransactionType {
    SCHEDULED("Purchase"),
    IMMEDIATE("Sale");

    private final String label;

    TransactionType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
