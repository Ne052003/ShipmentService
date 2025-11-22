package com.neoapps.driven_adapters.entities;

import com.neoapps.model.ShipmentStatus;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table("shipments")
@Getter
@Setter
public class ShipmentEntity {
    @Id
    @Column("shipment_id")
    private Long id;
    @Column("order_id")
    private Long orderId;
    @Column("address_id")
    private Long addressId;
    @Column("customer_id")
    private Long customerId;
    @Column("address_snap")
    private String addressSnap;
    @Column("created_at")
    private LocalDateTime createdAt;
    private ShipmentStatus status;

}
