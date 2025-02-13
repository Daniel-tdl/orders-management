package br.com.jmt.orders_management.domain.util;

public class QueueConstant {

    public static final String CREATE_ORDER = "orders-created-exchange.x-queue-orders-created";
    public static final String CREATE_ORDER_EXCHANGE = "orders-created-exchange";

    public static final String CREATE_ORDER_DLQ = "dlq-orders-created-exchange.x-queue-orders-created.dlq";
    public static final String CREATE_ORDER_EXCHANGE_DLQ = "dlq-orders-created-exchange";

    public static final String CREATE_ORDER_ERROR = "dlq-orders-created-exchange.x-queue-orders-created.error";

    private QueueConstant() {
    }
}
