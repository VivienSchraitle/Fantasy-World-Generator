package vivien.doingthigns.fantasyworldgenerator.people;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.Table;
import vivien.doingthigns.fantasyworldgenerator.data.DataManager;
import vivien.doingthigns.fantasyworldgenerator.factiongenerator.Faction;

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
    private DataManager.Ancestry ancestry;
    private DataManager.Heritage heritage;
    private int financeScore;
    private int reputationScore;
    private int religionScore;
    private Faction myFaction;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getMyClass() {
        return myClass;
    }

    public void setMyClass(String myClass) {
        this.myClass = myClass;
    }

    public String getMyJob() {
        return myJob;
    }

    public void setMyJob(String myJob) {
        this.myJob = myJob;
    }

    public String getSkinColor() {
        return skinColor;
    }

    public void setSkinColor(String skinColor) {
        this.skinColor = skinColor;
    }

    public String getUndertones() {
        return undertones;
    }

    public void setUndertones(String undertones) {
        this.undertones = undertones;
    }

    public String getHairColor() {
        return hairColor;
    }

    public void setHairColor(String hairColor) {
        this.hairColor = hairColor;
    }

    public String getHairstyle() {
        return hairstyle;
    }

    public void setHairstyle(String hairstyle) {
        this.hairstyle = hairstyle;
    }

    public String getEyeColor() {
        return eyeColor;
    }

    public void setEyeColor(String eyeColor) {
        this.eyeColor = eyeColor;
    }

    public String[] getSpecialTraits() {
        return specialTraits;
    }

    public void setSpecialTraits(String[] specialTraits) {
        this.specialTraits = specialTraits;
    }

    public DataManager.Ancestry getAncestry() {
        return ancestry;
    }

    public void setAncestry(DataManager.Ancestry ancestry) {
        this.ancestry = ancestry;
    }

    public DataManager.Heritage getHeritage() {
        return heritage;
    }

    public void setHeritage(DataManager.Heritage heritage) {
        this.heritage = heritage;
    }

    public int getFinanceScore() {
        return financeScore;
    }

    public void setFinanceScore(int financeScore) {
        this.financeScore = financeScore;
    }

    public int getReputationScore() {
        return reputationScore;
    }

    public void setReputationScore(int reputationScore) {
        this.reputationScore = reputationScore;
    }

    public int getReligionScore() {
        return religionScore;
    }

    public void setReligionScore(int religionScore) {
        this.religionScore = religionScore;
    }

    public Faction getMyFaction() {
        return myFaction;
    }

    public void setMyFaction(Faction myFaction) {
        this.myFaction = myFaction;
    }
}
