package hu.elte.prjgbackend.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @Type(type="org.hibernate.type.UUIDCharType")
    private UUID token = UUID.randomUUID();

    @OneToOne
    private User user;

    @Column(columnDefinition = "timestamp")
    private Timestamp expires;
}
