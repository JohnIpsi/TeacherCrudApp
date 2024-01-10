package gr.aueb.elearn.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.Date;


@Entity
@Table(name = "teachers")
public class Teacher implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private int id;

    @Column(name = "FIRSTNAME", nullable = false)
    private String firstName;

    @Column(name = "SURNAME", nullable = false)
    private String surname;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "INS_DATE", nullable = false)
    private Date insDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UPD_DATE")
    private Date updDate;

    @PrePersist
    protected void onCreate() {
        this.insDate = Date.from(Instant.now());
    }

    @PreUpdate
    protected void onUpdate() {
        this.updDate = Date.from(Instant.now());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getInsDate() {
        return insDate;
    }

    public void setInsDate(Date insDate) {
        this.insDate = insDate;
    }

    public Date getUpdDate() {
        return updDate;
    }

    public void setUpdDate(Date updDate) {
        this.updDate = updDate;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", surname='" + surname + '\'' +
                ", insDate=" + insDate +
                ", updDate=" + updDate +
                '}';
    }
}
