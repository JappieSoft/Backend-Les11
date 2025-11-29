package nl.novi.vinylshop.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "publishers")
public class PublisherEntity extends BaseEntity{

        @Column(name="address")
        private String address;

        @Column(name="contact_details")
        private String contactDetails;
}
