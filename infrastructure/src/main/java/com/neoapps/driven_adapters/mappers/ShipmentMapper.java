package com.neoapps.driven_adapters.mappers;

import com.neoapps.driven_adapters.entities.ShipmentEntity;
import com.neoapps.model.Shipment;
import org.springframework.stereotype.Component;

@Component
public class ShipmentMapper {

    public Shipment toShipment(ShipmentEntity shipmentEntity) {
        Shipment shipment = new Shipment();
        shipment.setId(shipmentEntity.getId());
        shipment.setOrderId(shipmentEntity.getOrderId());
        shipment.setCustomerId(shipmentEntity.getCustomerId());
        shipment.setAddressId(shipmentEntity.getAddressId());
        shipment.setAddressSnap(shipmentEntity.getAddressSnap());
        shipment.setStatus(shipmentEntity.getStatus());
        shipment.setCreatedAt(shipmentEntity.getCreatedAt());

        return shipment;
    }

    public ShipmentEntity toShipmentEntity(Shipment shipment) {

        ShipmentEntity shipmentEntity = new ShipmentEntity();
        shipmentEntity.setId(shipment.getId());
        shipmentEntity.setOrderId(shipment.getOrderId());
        shipmentEntity.setCustomerId(shipment.getCustomerId());
        shipmentEntity.setAddressId(shipment.getAddressId());
        shipmentEntity.setAddressSnap(shipment.getAddressSnap());
        shipmentEntity.setStatus(shipment.getStatus());
        shipmentEntity.setCreatedAt(shipment.getCreatedAt());

        return shipmentEntity;
    }
}
