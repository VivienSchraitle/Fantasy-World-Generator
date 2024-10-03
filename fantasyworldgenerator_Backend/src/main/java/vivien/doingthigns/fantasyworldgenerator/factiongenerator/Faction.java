package vivien.doingthigns.fantasyworldgenerator.factiongenerator;
import java.io.Serializable;
import java.util.*;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.MapKeyColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vivien.doingthigns.fantasyworldgenerator.config.converters.StringArrayConverter;
import vivien.doingthigns.fantasyworldgenerator.config.converters.StringListConverter;
import vivien.doingthigns.fantasyworldgenerator.data.DataManager;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name ="factions")
public class Faction implements Serializable{

    // Defining scores for faction generation
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int magicScore;
    private int intensityScore;
    private int sizeScale;
    private int militaristicScore;
    private int financeScore;
    private int reputationScore;
    private int religionScore;

    // Defining scores for person generation
    private int primPercent;
    private int secPercent;
    private String primHeritageName;
    private int assignedLeaders;
    private String secHeritageName;

    // Generated values for faction design
    private String facName = "";


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
    private String powerParameters = "";
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

    // Standings as an array of strings
    @Lob
    @Column(name = "standings_array", columnDefinition = "TEXT")
    @Convert(converter = StringArrayConverter.class)
    private String[] standingsArray;

    private String powerType;
    private String votingSystem;


    public Faction(int scale, int money, int magic, int military, int religious, int reputation, int intensity, int pPercent, int sPercent, String primHeri, String secoHeri) {
        this.magicScore = magic;
        this.militaristicScore = military;
        this.financeScore = money;
        this.sizeScale = scale;
        this.reputationScore = reputation;
        this.primPercent = pPercent;
        this.secPercent = sPercent;
        this.intensityScore = intensity;
        this.religionScore = religious;
        this.primHeritageName =primHeri;
        if(primHeri.equals("Random"))
        {
            primHeri = DataManager.getHeritages().get(DataManager.getRandom().nextInt(DataManager.getHeritages().size())).getName();   
        }
        
        this.secHeritageName =secoHeri;
        if(secoHeri.equals("Random"))
        {
            secoHeri = DataManager.getHeritages().get(DataManager.getRandom().nextInt(DataManager.getHeritages().size())).getName();   
        }
    }

    public String generateName() {
        StringBuilder name = new StringBuilder();
        long helper = DataManager.getRandom().nextInt(100);
        if (helper > 70) {
            name.append("The ");
        }
        name.append(DataManager.getAttribute()[DataManager.getRandom().nextInt(DataManager.getAttribute().length)]).append(" ");
        name.append(DataManager.getMainName()[DataManager.getRandom().nextInt(DataManager.getMainName().length)]);
        facName = name.toString();
        return facName;
    }

    public String generateDomains() {
        StringBuilder domains = new StringBuilder("Their fields of concern: ");
        int amount = (sizeScale / 10) + (int)(intensityScore/20);
        int rndIndex = DataManager.getRandom().nextInt(50, 100);
        int distributer = Math.max(1, rndIndex + magicScore + militaristicScore + religionScore);
        int magicAmount = (int) Math.round((double) amount / distributer);
        int militaryAmount = (int) Math.round((double) amount / distributer);
        int religionAmount = (int) Math.round((double) religionScore / distributer);

        domainsArray = new String[amount];

        for (int i = 0; i < amount - magicAmount - militaryAmount - religionAmount; i++) {
            domainsArray[i] = DataManager.getFactionDomainsMundane()[DataManager.getRandom().nextInt(DataManager.getFactionDomainsMundane().length)];
            domains.append(domainsArray[i]).append(", ");
        }
        for (int i = amount - magicAmount - militaryAmount - religionAmount; i < amount - militaryAmount - religionAmount; i++) {
            domainsArray[i] = DataManager.getFactionDomainsMagical()[DataManager.getRandom().nextInt(DataManager.getFactionDomainsMagical().length)];
            domains.append(domainsArray[i]).append(", ");
        }
        for (int i = amount - militaryAmount - religionAmount; i < amount - religionAmount; i++) {
            domainsArray[i] = DataManager.getFactionDomainsMilitary()[DataManager.getRandom().nextInt(DataManager.getFactionDomainsMilitary().length)];
            domains.append(domainsArray[i]).append(", ");
        }
        for (int i = amount - religionAmount; i < amount; i++) {
            domainsArray[i] = DataManager.getFactionDomainsReligious()[DataManager.getRandom().nextInt(DataManager.getFactionDomainsReligious().length)];
            domains.append(domainsArray[i]).append(", ");
        }

        domains.append("and they are defined by their craving for: ")
               .append(DataManager.getFactionEssence()[DataManager.getRandom().nextInt(DataManager.getFactionEssence().length)]);

        return domains.toString();
    }

    public String generateGoals() {
        int amount = (sizeScale / 10) + (int)(intensityScore/20);
        goalsArray = new String[amount];
        StringBuilder goals = new StringBuilder("Their main goals are: \n");
        for (int i = 0; i < amount; i++) {
            goalsArray[i] = DataManager.getFactionGoals()[DataManager.getRandom().nextInt(DataManager.getFactionGoals().length)];
            goals.append(goalsArray[i]).append(", ");
        }
        return goals.toString();
    }

    public String generateStyle() {
        StringBuilder styleDescription = new StringBuilder("Their organization style is: ");
        leadership = generateLeadership();
        joinRitual = generateJoinRitual();
        powerParameters = generatePowerParameters();
        return styleDescription.append(leadership).append("\n")
                .append(joinRitual).append("\n")
                .append(powerParameters).toString();
    }

    public String generateLeadership() {
        int rndScore = DataManager.getRandom().nextInt(100);
        if ((int)(intensityScore/20) == 1) {
            if (rndScore < 25) return DataManager.getSourceOfPower()[0]; // Anarchistic
            if (rndScore <= 58) {
                assignedLeaders = DataManager.getRandom().nextInt(5, sizeScale * 4 + 5);
                return DataManager.getSourceOfPower()[1]; // Democratic
            }
            if (rndScore <= 90) {
                assignedLeaders = DataManager.getRandom().nextInt(3, sizeScale / 5 + 3);
                return DataManager.getSourceOfPower()[2]; // Oligarchic
            }
            assignedLeaders = 1;
            return DataManager.getSourceOfPower()[3]; // Autocratic
        } else if ((int)(intensityScore/20) == 2) {
            if (rndScore < 10) return DataManager.getSourceOfPower()[0]; // Anarchistic
            if (rndScore <= 40) {
                assignedLeaders = DataManager.getRandom().nextInt(5, sizeScale * 4 + 5);
                return DataManager.getSourceOfPower()[1]; // Democratic
            }
            if (rndScore <= 80) {
                assignedLeaders = DataManager.getRandom().nextInt(3, sizeScale / 5 + 3);
                return DataManager.getSourceOfPower()[2]; // Oligarchic
            }
            assignedLeaders = 1;
            return DataManager.getSourceOfPower()[3]; // Autocratic
        } else {
            if (rndScore < 5) return DataManager.getSourceOfPower()[0]; // Anarchistic
            if (rndScore <= 30) {
                assignedLeaders = DataManager.getRandom().nextInt(5, sizeScale * 4 + 5);
                return DataManager.getSourceOfPower()[1]; // Democratic
            }
            if (rndScore <= 60) {
                assignedLeaders = DataManager.getRandom().nextInt(3, sizeScale / 5 + 3);
                return DataManager.getSourceOfPower()[2]; // Oligarchic
            }
            assignedLeaders = 1;
            return DataManager.getSourceOfPower()[3]; // Autocratic
        }
    }

    public String generateJoinRitual() {
        int rndScore = DataManager.getRandom().nextInt(400);
        int helper = switch (leadership) {
            case "Anarchistic" -> -1;
            case "Democratic" -> 0;
            case "Oligarchic" -> 1;
            case "Autocratic" -> 2;
            default -> -300;
        };

        int dedicationValue = rndScore + intensityScore * 5 + helper * 100;
        if (dedicationValue < 300)
            return DataManager.getJoinRitualSimple()[DataManager.getRandom().nextInt(DataManager.getJoinRitualSimple().length)];
        if (dedicationValue < 600)
            return DataManager.getJoinRitualMedium()[DataManager.getRandom().nextInt(DataManager.getJoinRitualMedium().length)];
        if (dedicationValue < 1101)
            return DataManager.getJoinRitualHard()[DataManager.getRandom().nextInt(DataManager.getJoinRitualHard().length)];

        System.out.println("UNDEFINED CONTROL SEQUENCE JOIN RITUAL VALUE = " + dedicationValue);
        System.exit(0);
        return null;  // To satisfy compiler
    }

    public String generatePowerParameters() {
        StringBuilder powerParameters = new StringBuilder();
        int helper = switch (leadership) {
            case "Anarchistic" -> -1;
            case "Democratic" -> 0;
            case "Oligarchic" -> 1;
            case "Autocratic" -> 2;
            default -> -300;
        };

        if (helper == 0) {
            powerType = DataManager.getVotingType()[DataManager.getRandom().nextInt(DataManager.getVotingType().length)];
            votingSystem = DataManager.getOliDemoVotingResults()[DataManager.getRandom().nextInt(DataManager.getOliDemoVotingResults().length)];
            powerParameters.append("The faction is led by a democratic voting system in a ")
                    .append(powerType).append(" manner. The democracy functions over ").append(votingSystem).append(".");
        } else if (helper == 1) {
            powerType = DataManager.getOliType()[DataManager.getRandom().nextInt(DataManager.getOliType().length)];
            votingSystem = DataManager.getOliDemoVotingResults()[DataManager.getRandom().nextInt(DataManager.getOliDemoVotingResults().length)];
            powerParameters.append("The faction is led by an oligarchy, granting voting rights to the ")
                    .append(powerType).append(". The oligarchy functions over ").append(votingSystem).append(".");
        } else if (helper == 2) {
            powerType = DataManager.getAutocracyType()[DataManager.getRandom().nextInt(DataManager.getAutocracyType().length)];
            powerParameters.append("The faction is led by an autocracy, and leader is put in power by ")
                    .append(powerType).append(".");
        } else if (helper == -1) {
            powerParameters.append("The faction is an Anarchistic communion.");
        }

        return powerParameters.toString();
    }

    public String generateParameters() {
        int valuesCount = Math.clamp(sizeScale / 10 + (int)(intensityScore/20),1, 7);
        int moneyCount = Math.clamp((sizeScale + financeScore) / 20, 1 , 7);
        int doctrineCount = (int)(intensityScore/20);

        factionValues = new String[valuesCount];
        moneySources.clear();
        doctrines = new String[Math.clamp(doctrineCount,1, 5)];

        moneySources.put("Low", new ArrayList<>());
        moneySources.put("Mid", new ArrayList<>());
        moneySources.put("High", new ArrayList<>());
        moneySources.put("Insane", new ArrayList<>());

        StringBuilder valuesStr = new StringBuilder("Their values are: \n");
        StringBuilder moneyStr = new StringBuilder("They finance themselves by: \n");
        StringBuilder doctrineStr = new StringBuilder("They teach: \n");

        for (int i = 0; i < valuesCount; i++) {
            factionValues[i] = DataManager.getVirtues()[DataManager.getRandom().nextInt(DataManager.getVirtues().length)];
            valuesStr.append(factionValues[i]).append(", ");
        }

        int reducer = 0;
        List<String> list = new ArrayList<>();
        for (int i = 0; i < moneyCount; i++) {
            int rndScore = DataManager.getRandom().nextInt(101);
            int finances = rndScore + 3 * financeScore - reducer * 7;

            String jobCategory;
            String randomJob;

            if (finances < 70) {
                jobCategory = "Low";
                randomJob = generateWeightedRandomGeneralJob(DataManager.getLowJobMappings());
            } else if (finances < 200) {
                jobCategory = "Mid";
                randomJob = generateWeightedRandomGeneralJob(DataManager.getMidJobMappings());
            } else if (finances < 350) {
                jobCategory = "High";
                randomJob = generateWeightedRandomGeneralJob(DataManager.getHighJobMappings());
                reducer += 1;
            } else {
                jobCategory = "Insane";
                randomJob = generateWeightedRandomGeneralJob(DataManager.getSuperJobMappings());
                reducer += 5;
                i += 2;
            }

            moneySources.get(jobCategory).add(randomJob);
            list.add(randomJob);
        }

        for (int i = 0; i < doctrineCount; i++) {
            doctrines[i] = DataManager.getDoctrines()[DataManager.getRandom().nextInt(DataManager.getDoctrines().length)];
            doctrineStr.append(doctrines[i]).append(", ");
        }

        return valuesStr.toString() + "\n" + moneyStr.toString() + String.join(", ", list) + "\n" + doctrineStr.toString();
    }

    public String generateStandings() {
        adjustScores();
        int magicIndex = Math.min(Math.max(0, (int) Math.round((double) magicScore / 10) + DataManager.getRandom().nextInt(-1, 2)), DataManager.getWealth().length - 1);
        int intensityIndex = Math.min(Math.max(0, (int)(intensityScore/20) - 1 + DataManager.getRandom().nextInt(-1, 2)), DataManager.getIntensity().length - 1);
        int scaleIndex = Math.min(Math.max(0, (int) Math.round((double) sizeScale / 5) + DataManager.getRandom().nextInt(-1, 2)), DataManager.getSize().length - 1);
        int militaryIndex = Math.min(Math.max(0, (int) Math.round((double) militaristicScore / 10) + DataManager.getRandom().nextInt(-1, 2)), DataManager.getMilitaryInclination().length - 1);
        int financeIndex = Math.min(Math.max(0, (int) Math.round((double) financeScore / 10) + DataManager.getRandom().nextInt(-1, 2)), DataManager.getWealth().length - 1);
        int reputationIndex = Math.min(Math.max(0, (int) Math.round((double) reputationScore / 10) + DataManager.getRandom().nextInt(-1, 2)), DataManager.getReputation().length - 1);

        standingsArray = new String[6];
        standingsArray[0] = DataManager.getMagicalInclination()[magicIndex];
        standingsArray[1] = DataManager.getMilitaryInclination()[militaryIndex];
        standingsArray[2] = DataManager.getWealth()[financeIndex];
        standingsArray[3] = DataManager.getSize()[scaleIndex];
        standingsArray[4] = DataManager.getIntensity()[intensityIndex];
        standingsArray[5] = DataManager.getReputation()[reputationIndex];

        return String.join("\n", standingsArray);
    }

    private void adjustScores() {
        int highestScore = Math.max(magicScore, Math.max(sizeScale, Math.max(militaristicScore, financeScore)));

        if (highestScore >= 70) {
            if (magicScore != highestScore && highestScore - 30 > magicScore) magicScore += 15;
            if (sizeScale != highestScore && highestScore - 30 > sizeScale) sizeScale += 15;
            if (militaristicScore != highestScore && highestScore - 20 > militaristicScore) militaristicScore += 15;
            if (financeScore != highestScore && highestScore - 20 > financeScore) financeScore += 15;

            magicScore = Math.min(magicScore, 100);
            sizeScale = Math.min(sizeScale, 100);
            militaristicScore = Math.min(militaristicScore, 100);
            financeScore = Math.min(financeScore, 100);
        }
    }

    public String generateWeightedRandomGeneralJob(Map<String, List<String>> baseDir) {
        List<String> generalJobs = new ArrayList<>(baseDir.keySet());
        int totalWeight = generalJobs.stream().mapToInt(job -> baseDir.get(job).size()).sum();
        int randomWeight = DataManager.getRandom().nextInt(totalWeight);

        int cumulativeWeight = 0;
        for (String job : generalJobs) {
            cumulativeWeight += baseDir.get(job).size();
            if (randomWeight < cumulativeWeight) {
                return job;
            }
        }

        return null; // Should not reach here
    }

}

