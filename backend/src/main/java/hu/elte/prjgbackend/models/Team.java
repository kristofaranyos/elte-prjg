package hu.elte.prjgbackend.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String teamName;

    @OneToMany(mappedBy = "team")
    private Set<User> players;

    @OneToMany(mappedBy = "team")
    private Set<Image> uploadedImages;

    private float latitude;

    @ManyToOne()
    @JoinColumn
    private Game game;
}