package az.edu.itbrains.ecommerce.enums;


public enum PaymentStatus {
    PENDING,    // Payment is pending
    COMPLETED,  // Payment has been completed successfully
    FAILED,     // Payment failed
    CANCELLED,  // Payment was cancelled
    REFUNDED;   // Payment was refunded

}
