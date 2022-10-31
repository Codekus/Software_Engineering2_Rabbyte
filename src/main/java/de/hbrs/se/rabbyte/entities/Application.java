package de.hbrs.se.rabbyte.entities;

import javax.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "bewerbung", schema = "rabbyte" )
public class Application {

    private int id;
    private JobAdvertisement jobAdvertisement;
    private Student student;
    private LocalDate date;
    private String applicationText;



    @Id
    @GeneratedValue(
            strategy=GenerationType.AUTO,
            generator = "bewerbung_id"
    )
    @SequenceGenerator(
            name = "application_id",
            sequenceName = "rabbyte.seq_bewerbung_id",
            allocationSize=1
    )
    @Column(name = "bewerbung_id")
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "stellenauschreibung_id", referencedColumnName = "stellenausschreibung_id", nullable = false)
    public JobAdvertisement getJobAdvertisement() {
        return jobAdvertisement;
    }
    public void setJobAdvertisement(JobAdvertisement jobAdvertisement) {
        this.jobAdvertisement = jobAdvertisement;
    }

    @ManyToOne()
    @JoinColumn(name="nutzer_ID")
    public Student getStudent() {
        return student;
    }
    public void setStudent(Student student) {
        this.student = student;
    }

    @Basic
    @Column(name = "datum")
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate  date) {
        this.date = date;
    }

    @Basic
    @Column(name = "bewerbung_inhalt")
    public String getApplicationText() {
        return applicationText;
    }
    public void setApplicationText(String applicationText) {
        this.applicationText = applicationText;
    }


}
