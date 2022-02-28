package dadsgame.businessapi.entity;

import org.hibernate.annotations.Table;

import javax.persistence.*;

@Entity
@Table(appliesTo = "game")
public class Game {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(columnDefinition = "serial")
  private long id;
  private String name;
  private String publisher;
  private String developer;
  private String description;
  private double price;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public String getPublisher() {
    return publisher;
  }

  public void setPublisher(String publisher) {
    this.publisher = publisher;
  }


  public String getDeveloper() {
    return developer;
  }

  public void setDeveloper(String developer) {
    this.developer = developer;
  }


  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }


  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

}
