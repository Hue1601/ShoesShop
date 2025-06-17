package org.example.shoesshopbe.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Table(name = "Orders")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Orders {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "UserID")
    private Users user;

    private BigDecimal totalAmount;

    private String orderStatus;

    private LocalDateTime createdAt;

    private String buyerName;

    private String buyerEmail;

    private String buyerPhoneNumber;

    private BigDecimal shippingFee;

    private Integer paymentType;

    private BigDecimal amountPaid;

    @ManyToOne
    @JoinColumn(name="AddressID")
    private Address address;

    @ManyToOne
    @JoinColumn(name="VoucherID")
    private Voucher voucher;

}
