package az.edu.itbrains.ecommerce.models;

import az.edu.itbrains.ecommerce.enums.OrderStatus;
import az.edu.itbrains.ecommerce.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.List;


@Data
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private UserEntity user;
    private Date orderDate;
    private String address;
    private String phoneNumber;
    private String message;
    private OrderStatus orderStatus;
    private PaymentStatus paymentStatus;
    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems;
}
