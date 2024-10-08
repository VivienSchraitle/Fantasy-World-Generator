// FactionEntity.java
package vivien.doingthigns.fantasyworldgenerator.factiongenerator;

import java.io.Serializable;
import java.util.*;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vivien.doingthigns.fantasyworldgenerator.config.converters.StringArrayConverter;
import vivien.doingthigns.fantasyworldgenerator.config.converters.StringListConverter;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name ="factions")
public class Faction implements Serializable {
    @Id 
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    // Faction attributes for database
    private int sizeScale;
    private int magicScore;
    private int intensityScore;    
    private int militaristicScore;
    private int financeScore;
    private int reputationScore;
    private int religionScore;

    private int primPercent;
    private int secPercent;
    private String primHeritageName;
    private int assignedLeaders;
    private String secHeritageName;
    private String facName = "";
    private String powerType;
    private String votingSystem;
    
    @Lob
    @Column(name = "goals_array", columnDefinition = "TEXT")
    @Convert(converter = StringArrayConverter.class)
    private String[] goalsArray = {};

    @Lob
    @Column(name = "domains_array", columnDefinition = "TEXT")
    @Convert(converter = StringArrayConverter.class)
    private String[] domainsArray = {};

    private String leadership = "";
    private String joinRitual = "";

    @Lob
    @Column(name = "faction_values", columnDefinition = "TEXT")
    @Convert(converter = StringArrayConverter.class)
    private String[] factionValues = {};

    @ElementCollection
    @CollectionTable(name = "money_sources", joinColumns = @JoinColumn(name = "parent_id"))
    @MapKeyColumn(name = "source_name")
    @Column(name = "sources", columnDefinition = "TEXT")
    @Convert(converter = StringListConverter.class)
    private Map<String, List<String>> moneySources = new HashMap<>();

    @Lob
    @Column(name = "doctrines", columnDefinition = "TEXT")
    @Convert(converter = StringArrayConverter.class)
    private String[] doctrines = {};

    /*@Lob
    @Column(name = "standings_array", columnDefinition = "TEXT")
    @Convert(converter = StringArrayConverter.class)
    private String[] standingsArray;*/


}
