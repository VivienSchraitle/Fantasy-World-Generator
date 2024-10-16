package vivien.doingthigns.fantasyworldgenerator.people;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import vivien.doingthigns.fantasyworldgenerator.data.Ancestry;
import vivien.doingthigns.fantasyworldgenerator.data.DataManager;
import vivien.doingthigns.fantasyworldgenerator.data.Heritage;
import vivien.doingthigns.fantasyworldgenerator.factiongenerator.Faction;

@Getter
@Setter
@Entity
@Table(name = "people")
public class Person implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private float size;
    private int age;
    private String myClass;
    private String myJob;
    private String skinColor;
    private String undertones;
    private String hairColor;
    private String hairstyle;
    private String eyeColor;
    private String[] specialTraits;
    private Ancestry ancestry;
    private Heritage heritage;
    private int financeScore;
    private int reputationScore;
    private int religionScore;
    private Faction myFaction;
}
