package uz.sherzodn.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Sherzod Nurjonov
 */
@Entity
@Table(name = "votes")
public class Vote implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @Column(name = "voted_date")
    private Date votedDate;

    public Vote() {
    }

    public Vote(User user, Restaurant restaurant, Date votedDate) {
        this.user = user;
        this.restaurant = restaurant;
        this.votedDate = votedDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Date getVotedDate() {
        return votedDate;
    }

    public void setVotedDate(Date votedDate) {
        this.votedDate = votedDate;
    }
}
