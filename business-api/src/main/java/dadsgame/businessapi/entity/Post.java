package dadsgame.businessapi.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Table(name = "post")
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
