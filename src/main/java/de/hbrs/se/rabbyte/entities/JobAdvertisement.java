package de.hbrs.se.rabbyte.entities;

import javax.persistence.*;


@Entity
@Table(name ="jobAdvertisement" , schema = "rabbyte")
public class JobAdvertisement {

    private int id;
    private String text;
    private String title;
    private Business business;

    @Id
    @GeneratedValue(
            strategy= GenerationType.AUTO,
            generator = "jobAdvertisement_id"
    )
    @SequenceGenerator(
            name = "jobAdvertisement_id",
            sequenceName = "rabbyte.seq_stellenausschreibung_id",
            allocationSize=1
    )
    @Column(name = "jobAdvertisement_id")
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    @ManyToOne()
    @JoinColumn(name="user_id")
    public Business getBusiness() {
        return business;
    }
    public void setBusiness(Business business) {
        this.business = business;
    }

}
