package com.gmail.kulacholeg.canon.connect.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "operations_properties")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PropertyEntity {
    @Id
    @Column(name = "property_name")
    private String name;

    @Column(name = "property_value")
    private String value;
}
