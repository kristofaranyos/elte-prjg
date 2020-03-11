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
}