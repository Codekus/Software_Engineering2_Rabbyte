package de.hbrs.se.rabbyte.entities;

import de.hbrs.se.rabbyte.dtos.implemented.GeneralUserDTOImpl;

import javax.persistence.*;


@Entity
@Table(name ="job_advertisement" , schema = "rabbyte")
public class JobAdvertisement {

    private int id;
    private String text;
    private String title;
    private String type;
    private Business business;

    @Id
    @GeneratedValue(
            strategy= GenerationType.AUTO,
            generator = "job_advertisement_id"
    )
    @SequenceGenerator(
            name = "job_advertisement_id",
            sequenceName = "rabbyte.seq_stellenausschreibung_id",
            allocationSize=1
    )
    @Column(name = "job_advertisement_id")
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

    public void setType(String type) {
        this.type = type;
    }
    public String getType(){
        return type;
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
