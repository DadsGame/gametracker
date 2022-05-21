package dadsgame.businessapi.entity;

import lombok.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "post")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Post {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @Column
    private String author;

    @Column
    private String title;

    @Column
    private String content;

    @OneToMany(mappedBy = "postComment")
    private List<Comment> comments;

    @Column(name = "id_gametopic")
    private int gameTopic;
}
