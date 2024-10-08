package vivien.doingthigns.fantasyworldgenerator.people;

import vivien.doingthigns.fantasyworldgenerator.data.DataManager;
import vivien.doingthigns.fantasyworldgenerator.factiongenerator.Faction;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class People implements Serializable{

    private static final int LOW_FINANCE_THRESHOLD = 70;
    private static final int MID_FINANCE_THRESHOLD = 200;
    private static final int HIGH_FINANCE_THRESHOLD = 350;

    private DataManager.Ancestry ancestry;
    private DataManager.Heritage heritage;
    private Faction localFaction;

    public int financeScore;
    public int reputationScore;
    public int religionScore;
    
        /// this is the main entrypoint to make factions
    public void populateFaction(Faction myFaction) {
        localFaction = myFaction;
        List<Person> factionPeople = new ArrayList<>();

        if (!localFaction.getLeadership().equals("Anarchistic")) {
            factionPeople = generateFactionLeaders();
            for (Person leader : factionPeople) {
                writePerson(leader, "Leader");
            }
        }

        for (int i = 0; i < localFaction.getSizeScale(); i++) {
            Person member = generatePerson("","");
            writePerson(member, "Member");
        }
    }
        /// save a person to harddisc
    public void writePerson(Person person, String stance) {
        String fullPath = new File("Generated Factions").getAbsolutePath();
        String factionDir = new File(fullPath, localFaction.getFacName() + "/" + stance).getAbsolutePath();
        File dir = new File(factionDir);
        dir.mkdirs();

        try (FileWriter outputFile = new FileWriter(new File(factionDir, person.getName() + ".md"))) {
            outputFile.write(person.getName() + "\n");
            if (!person.getAncestry().getName().equals(person.getHeritage().getName())) {
                outputFile.write(person.getAncestry().getName() + " " + person.getHeritage().getName() + "\n");
            } else {
                outputFile.write(person.getAncestry().getName() + "\n");
            }
            outputFile.write("\n");
            outputFile.write("Age: " + person.getAge() + "\n");
            outputFile.write("Height: " + person.getSize() + "\n");
            outputFile.write("Job: " + person.getMyJob() + "\n");
            outputFile.write("Class: " + (person.getMyClass() != null ? person.getMyClass() : "None") + "\n");
            outputFile.write("Skin: " + person.getSkinColor() + " with " + person.getUndertones() + "\n");
            outputFile.write("Hair: " + person.getHairColor() + " in a " + person.getHairstyle() + "\n");
            outputFile.write("Eyes: " + person.getEyeColor() + "\n");
            if (person.getSpecialTraits() != null) {
                outputFile.write("Special Traits: " + String.join(", ", person.getSpecialTraits()) + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
        ///creates expicit leaders for a faction
    public List<Person> generateFactionLeaders() {
        String leadership = localFaction.getLeadership();
        List<Person> leaders = new ArrayList<>();
        switch (leadership) {
            case "Democratic":
                leaders = handleDemocraticFaction();
                break;
            case "Oligarchic":
                leaders.add(generatePerson("Oligarch", localFaction.getPowerType()));
                break;
            case "Autocratic":
                leaders.add(generatePerson("Autocrat", localFaction.getPowerType()));
                break;
            default:
                break;
        }
        return leaders;
    }
        ///creates the actuall person
    public Person generatePerson(String position, String type) {
        Person person = new Person();
        person.setMyFaction(localFaction);
        generateName(person);

        if (!"".equals(position) && !"".equals(type)) {
            person.setMyJob(position + " chosen through " + type);
            person.setFinanceScore(DataManager.getRandom().nextInt(localFaction.getFinanceScore(), 100));
            person.setReligionScore(DataManager.getRandom().nextInt(0, 100));
            person.setReputationScore(DataManager.getRandom().nextInt(0, 100));
        } else if (!"".equals(position)) {
            person.setMyJob(position);
            person.setFinanceScore(Math.max(0, Math.min(localFaction.getFinanceScore() + DataManager.getRandom().nextInt(-10, 10), 100)));
            person.setReligionScore(Math.max(0, Math.min(localFaction.getReligionScore() + DataManager.getRandom().nextInt(-10, 10), 100)));
            person.setReputationScore(Math.max(0, Math.min(localFaction.getReputationScore() + DataManager.getRandom().nextInt(-10, 10), 100)));
        } else if (DataManager.getRandom().nextInt(100)>10) {
            person.setFinanceScore(DataManager.getRandom().nextInt(localFaction.getFinanceScore()));
            person.setReligionScore(DataManager.getRandom().nextInt(0, 100));
            person.setReputationScore(DataManager.getRandom().nextInt(localFaction.getReputationScore()));
            assignFactionBasedJob(person);
        } else {
            person.setFinanceScore(Math.max(0, Math.min(localFaction.getFinanceScore() + DataManager.getRandom().nextInt(-30, 30), 100)));
            person.setReligionScore(DataManager.getRandom().nextInt(0, 100));
            person.setReputationScore(DataManager.getRandom().nextInt(0, 100));
            generateNonFactionJob(person);
        }

        generateAncestry(person);
        generateHeritage(person);
        generateAppearance(person);
        generateClass(person);

        return person;
    }

    public Person generateUnaffiliatedPerson() {
        Person person = new Person();
        person.setFinanceScore(DataManager.getRandom().nextInt(0, 100));
        person.setReligionScore(DataManager.getRandom().nextInt(0, 100));
        person.setReputationScore(DataManager.getRandom().nextInt(0, 100));
        generateNonFactionJob(person);
        generateAncestry(person);
        generateHeritage(person);
        generateClass(person);
        generateName(person);
        generateAppearance(person);
        return person;
    }

    private List<Person> handleDemocraticFaction() {
        List<Person> leaders = new ArrayList<>();
        if (localFaction.getVotingSystem().contains("non elected leader")) {
            leaders.add(generatePerson("Non Elected Leader",""));
        } else if (localFaction.getVotingSystem().contains("elected leader")) {
            leaders.add(generatePerson("Elected Leader", ""));
        }

        if (localFaction.getVotingSystem().contains("council")) {
            for (int i = 0; i < localFaction.getAssignedLeaders(); i++) {
                leaders.add(generatePerson("Council Member", ""));
            }
        }
        return leaders;
    }

    private void assignFactionBasedJob(Person person) {
        int randomScore = DataManager.getRandom().nextInt(101);
        person.setFinanceScore(Math.max(0, Math.min(localFaction.getFinanceScore() + DataManager.getRandom().nextInt(-localFaction.getFinanceScore() / 3, localFaction.getFinanceScore() / 3), 100)));
        int totalScore = randomScore + 3 * financeScore;

        if (totalScore < LOW_FINANCE_THRESHOLD) {
            person.setMyJob(generateJobFromMapping("Low", DataManager.getLowJobMappings(), 20, person));
        } else if (totalScore < MID_FINANCE_THRESHOLD) {
            person.setMyJob(generateJobFromMapping("Mid", DataManager.getMidJobMappings(), 40, person));
        } else if (totalScore < HIGH_FINANCE_THRESHOLD) {
            person.setMyJob(generateJobFromMapping("High", DataManager.getHighJobMappings(), 60, person));
        } else {
            person.setMyJob(generateJobFromMapping("Insane", DataManager.getSuperJobMappings(), 80, person));
        }
    }

    private String generateJobFromMapping(String moneySourceKey, Map<String, List<String>> jobMappings, int successRateThreshold, Person person) {
        if (localFaction.getMoneySources().get(moneySourceKey).size() > 0) {
            String source = localFaction.getMoneySources().get(moneySourceKey).get(DataManager.getRandom().nextInt(localFaction.getMoneySources().get(moneySourceKey).size()));
            return jobMappings.get(source).get(DataManager.getRandom().nextInt(jobMappings.get(source).size()));
        }
        return generateNonFactionJob(person);
    }

    public DataManager.Ancestry generateAncestry(Person person) {
        int totalLikelihood = DataManager.getAncestries().stream().mapToInt(a -> a.getLh()).sum();
        int randomValue = DataManager.getRandom().nextInt(totalLikelihood);

        for (DataManager.Ancestry a : DataManager.getAncestries()) {
            randomValue -= a.getLh();
            if (randomValue < 0) {
                ancestry = a;
                break;
            }
        }
        person.setAncestry(ancestry);
        return ancestry;
    }

    public DataManager.Heritage generateHeritage(Person person) {
        int totalLikelihood = DataManager.getHeritages().stream().mapToInt(h -> h.getLh()).sum();
        int randomValue = DataManager.getRandom().nextInt(totalLikelihood);

        if (DataManager.getRandom().nextInt(101) < 50) {
            person.setHeritage(heritage = DataManager.getHeritages().stream().filter(h -> h.getName().equals(person.getAncestry().getName())).findFirst().orElse(null));
        } else {
            for (DataManager.Heritage h : DataManager.getHeritages()) {
                randomValue -= h.getLh();
                if (randomValue < 0) {
                    heritage = h;
                    break;
                }
            }
        }
        return heritage;
    }

    public String generateClass(Person person) {
        int baseNumber = DataManager.getRandom().nextInt(DataManager.getClasses().length - 4);
        baseNumber = Math.max(0, Math.min(baseNumber + DataManager.getRandom().nextInt(-1, 3), DataManager.getClasses().length - 1));
        person.setMyClass(DataManager.getClasses()[baseNumber]);
        return DataManager.getClasses()[baseNumber];
    }

    public String generateNonFactionJob(Person person) {
        if (person.getFinanceScore() < LOW_FINANCE_THRESHOLD) {
            String randomKey = new ArrayList<>(DataManager.getLowJobMappings().keySet()).get(DataManager.getRandom().nextInt(DataManager.getLowJobMappings().keySet().size()));
            person.setMyJob(DataManager.getLowJobMappings().get(randomKey).get(DataManager.getRandom().nextInt(DataManager.getLowJobMappings().get(randomKey).size())));
        } else if (person.getFinanceScore() < MID_FINANCE_THRESHOLD) {
            String randomKey = new ArrayList<>(DataManager.getMidJobMappings().keySet()).get(DataManager.getRandom().nextInt(DataManager.getMidJobMappings().keySet().size()));
            person.setMyJob(DataManager.getMidJobMappings().get(randomKey).get(DataManager.getRandom().nextInt(DataManager.getMidJobMappings().get(randomKey).size())));
        } else if (person.getFinanceScore() < HIGH_FINANCE_THRESHOLD) {
            String randomKey = new ArrayList<>(DataManager.getHighJobMappings().keySet()).get(DataManager.getRandom().nextInt(DataManager.getHighJobMappings().keySet().size()));
            person.setMyJob(DataManager.getHighJobMappings().get(randomKey).get(DataManager.getRandom().nextInt(DataManager.getHighJobMappings().get(randomKey).size())));
        } else {
            String randomKey = new ArrayList<>(DataManager.getSuperJobMappings().keySet()).get(DataManager.getRandom().nextInt(DataManager.getSuperJobMappings().keySet().size()));
            person.setMyJob(DataManager.getSuperJobMappings().get(randomKey).get(DataManager.getRandom().nextInt(DataManager.getSuperJobMappings().get(randomKey).size())));
        }
        return person.getMyJob();
    }

    public void generateAppearance(Person person) {
        person.setEyeColor((DataManager.getRandom().nextInt(101) > 50) ? person.getAncestry().getEyeColor()[DataManager.getRandom().nextInt(person.getAncestry().getEyeColor().length)] : person.getHeritage().getEyeColor()[DataManager.getRandom().nextInt(person.getHeritage().getEyeColor().length)]);
        person.setHairColor((DataManager.getRandom().nextInt(101) > 50) ? person.getAncestry().getHairColor()[DataManager.getRandom().nextInt(person.getAncestry().getHairColor().length)] : person.getHeritage().getHairColor()[DataManager.getRandom().nextInt(person.getHeritage().getHairColor().length)]);
        person.setHairstyle((DataManager.getRandom().nextInt(101) > 50) ? person.getAncestry().getHairstyles()[DataManager.getRandom().nextInt(person.getAncestry().getHairstyles().length)] : person.getHeritage().getHairstyles()[DataManager.getRandom().nextInt(person.getHeritage().getHairstyles().length)]);
        person.setSkinColor((DataManager.getRandom().nextInt(101) > 50) ? person.getAncestry().getSkinColor()[DataManager.getRandom().nextInt(person.getAncestry().getSkinColor().length)] : person.getHeritage().getSkinColor()[DataManager.getRandom().nextInt(person.getHeritage().getSkinColor().length)]);

        List<String> traitList = new ArrayList<>();
        traitList.addAll(Arrays.asList(getSingleElementFromEachArray(person.getAncestry().getCertainSpecialTraits())));
        traitList.addAll(Arrays.asList(getSingleElementFromEachArray(person.getHeritage().getCertainSpecialTraits())));
        traitList.addAll(Arrays.asList(getSingleElementFromEachArrayOptional(person.getAncestry().getOptionalSpecialTraits())));
        traitList.addAll(Arrays.asList(getSingleElementFromEachArrayOptional(person.getHeritage().getOptionalSpecialTraits())));

        person.setSpecialTraits(traitList.toArray(new String[0]));
        person.setAge(DataManager.getRandom().nextInt(person.getAncestry().getMatureAge(), person.getAncestry().getMaxAge()));
        person.setSize(person.getAncestry().getSizeAvg() + DataManager.getRandom().nextInt((int) (person.getAncestry().getSizeDev() * 10)) / 10.0f);
    }

    public String[] getSingleElementFromEachArray(String[][] certainSpecialTraits) {
        return Arrays.stream(certainSpecialTraits)
                .filter(traitArray -> traitArray != null && traitArray.length > 0)
                .map(traitArray -> traitArray[DataManager.getRandom().nextInt(traitArray.length)])
                .toArray(String[]::new);
    }

    public String[] getSingleElementFromEachArrayOptional(String[][] optionalSpecialTraits) {
        return Arrays.stream(optionalSpecialTraits)
                .filter(traitArray -> traitArray != null && traitArray.length > 0 && DataManager.getRandom().nextInt(101) > 30)
                .map(traitArray -> traitArray[DataManager.getRandom().nextInt(traitArray.length)])
                .toArray(String[]::new);
    }

    public String generateName(Person person) {
        person.setName(UUID.randomUUID().toString() + UUID.randomUUID().toString() + UUID.randomUUID().toString());
        return person.getName();  // TODO: Replace with actual name generation
    }
}
