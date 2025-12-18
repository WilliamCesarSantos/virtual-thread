package br.com.will.classes.virtualthread.infrastructure.database;

import br.com.will.classes.virtualthread.domain.Payment;
import br.com.will.classes.virtualthread.port.PaymentRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.GetItemRequest;
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest;

import java.util.Map;

@Repository
public class DynamoPaymentRepository implements PaymentRepository {

    private final DynamoDbClient dynamoDbClient;
    private final String paymentTableName;

    public DynamoPaymentRepository(
            @Value("${aws.dynamodb.table.inventory}") String paymentTableName,
            DynamoDbClient dynamoDbClient
    ) {
        this.dynamoDbClient = dynamoDbClient;
        this.paymentTableName = paymentTableName;
    }

    @Override
    public boolean existsByOrderId(String orderId) {
        GetItemRequest request = GetItemRequest.builder()
                .tableName(paymentTableName)
                .key(Map.of("orderId", AttributeValue.fromS(orderId)))
                .build();
        return dynamoDbClient.getItem(request).hasItem();
    }

    @Override
    public void save(Payment payment) {
        PutItemRequest request = PutItemRequest.builder()
                .tableName(paymentTableName)
                .item(Map.of(
                        "orderId", AttributeValue.fromS(payment.orderId()),
                        "amount", AttributeValue.fromN(String.valueOf(payment.amount())),
                        "status", AttributeValue.fromS(payment.status().name()),
                        "transactionId", AttributeValue.fromS(
                                payment.transactionId() == null ? "" : payment.transactionId()
                        )
                )).build();
        dynamoDbClient.putItem(request);
    }
}
