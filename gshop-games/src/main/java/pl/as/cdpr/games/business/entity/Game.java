package pl.as.cdpr.games.business.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.panache.common.Parameters;

@NamedQueries({
	@NamedQuery(name = "Game.findByIds", query = 
			"  SELECT g "
			+ "  FROM Game g"
			+ " WHERE g.id IN :games")
})
@Entity
@Table(name = "GAME")
public class Game extends PanacheEntityBase {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;
	
	@NotNull
	@Length(min = 1, max = 200)
	public String name;
	
	public String publisher;
	
	@Column(name = "publish_date")
	public Date publishDate;
	
	@NotNull
	@Min(value = 0)
	public int quantity;
	
	@NotNull
	public double price;
	
	public static List<Game> findByIds(List<Integer> games) {
		return list("#Game.findByIds", Parameters.with("games", games));
	}

}
