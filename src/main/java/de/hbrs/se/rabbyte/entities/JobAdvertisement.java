package de.hbrs.se.rabbyte.entities;

import de.hbrs.se.rabbyte.dtos.BusinessDTO;

import javax.persistence.*;


@Entity
@Table(name ="Stellenauschreibung" , schema = "rabbyte")
public class JobAdvertisement {

    private int id;
    private String text;
    private String title;
    private Business business;

    @Id
    @GeneratedValue(
            strategy= GenerationType.AUTO,
            generator = "stellenausschreibung_id"
    )
    @SequenceGenerator(
            name = "stellenausschreibung_id",
            sequenceName = "rabbyte.seq_stellenausschreibung_id",
            allocationSize=1
    )
    @Column(name = "stellenausschreibung_id")
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
    @JoinColumn(name="nutzer_id")
    public Business getBusiness() {
        return business;
    }
    public void setBusiness(Business business) {
        this.business = business;
    }

}
