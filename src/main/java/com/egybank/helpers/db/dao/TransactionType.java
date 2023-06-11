package com.egybank.helpers.db.dao;

public enum TransactionType {
        DEPOSIT,
        WITHDRAW,
    PAYBILL,
    TRANSFER,
        BUYITEM;

        public static TransactionType fromInteger(int x) {
            return switch (x) {
                case 0 -> DEPOSIT;
                case 1 -> WITHDRAW;
                case 2 -> PAYBILL;
                case 3 -> BUYITEM;
                case 4 -> TRANSFER;
                default -> null;
            };
        }

        public int getIntRepresentation() {
            return switch (this) {
                case DEPOSIT -> 0;
                case WITHDRAW -> 1;
                case PAYBILL -> 2;
                case BUYITEM -> 3;
                case TRANSFER -> 4;
                default -> -1;
            };
        }
    }