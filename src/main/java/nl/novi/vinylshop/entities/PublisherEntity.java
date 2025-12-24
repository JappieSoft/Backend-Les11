package nl.novi.vinylshop.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "publishers")
public class PublisherEntity extends BaseEntity{

        @Column(name = "name", nullable = false)
        private String name;

        @Column(name="address")
        private String address;

        @Column(name="contact_details")
        private String contactDetails;

        @OneToMany(mappedBy = "publisher")
        private Set<AlbumEntity> albums = new HashSet<>();


    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactDetails() {
        return contactDetails;
    }
    public void setContactDetails(String contactDetails) {
        this.contactDetails = contactDetails;
    }

    public Set<AlbumEntity> getAlbums() { return albums; }
    public void setAlbums(Set<AlbumEntity> albums) { this.albums = albums; }
}
