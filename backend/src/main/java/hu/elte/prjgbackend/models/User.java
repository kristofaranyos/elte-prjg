package hu.elte.prjgbackend.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User{
    @Id
    @Column(unique = true)
    private String id;

    @Column(unique = true)
    private String userName;

    private String pictureUrl;

    private String email;

    @ManyToOne
    @JoinColumn
    private Team team;

    public User(String id, String userName, String pictureUrl, String email){
        this.id = id;
        this.userName = userName;
        this.pictureUrl = pictureUrl;
        this.email = email;
    }
}