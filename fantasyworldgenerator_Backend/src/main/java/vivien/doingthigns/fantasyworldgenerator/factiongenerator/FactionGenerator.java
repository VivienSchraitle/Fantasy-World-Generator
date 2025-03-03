package vivien.doingthigns.fantasyworldgenerator.factiongenerator;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
import vivien.doingthigns.fantasyworldgenerator.data.DataManager;

import java.util.*;

@Getter
@Setter
@Component
@NoArgsConstructor
public class FactionGenerator {

    private Faction factionEntity;

    public FactionGenerator(Faction factionEntity) {
        this.factionEntity = factionEntity;
    }

    public String generateName() {
        StringBuilder name = new StringBuilder();
        long helper = DataManager.getRandom().nextInt(100);
        if (helper > 70) {
            name.append("The ");
        }
        name.append(DataManager.getAttribute()[DataManager.getRandom().nextInt(DataManager.getAttribute().length)])
                .append(" ")
                .append(DataManager.getMainName()[DataManager.getRandom().nextInt(DataManager.getMainName().length)]);
        factionEntity.setFacName(name.toString());
        return factionEntity.getFacName();
    }

    public String[] generateDomains() {
        // Calculate total amount of domains based on size and intensity
        int amount = (factionEntity.getSizeScale() / 10) + (factionEntity.getIntensityScore() / 20) + 1;

        // Calculate distribution proportion based on magic, military, and religion
        // scores
        int radomInt = DataManager.getRandom().nextInt(50, 100);
        int distributer = Math.max(1, radomInt + factionEntity.getMagicScore() + factionEntity.getMilitaristicScore()
                + factionEntity.getReligionScore());

        // Calculate rounded domain amounts
        int magicAmount = (int) Math.round((double) amount * factionEntity.getMagicScore() / distributer);
        int militaryAmount = (int) Math.round((double) amount * factionEntity.getMilitaristicScore() / distributer);
        int religionAmount = (int) Math.round((double) amount * factionEntity.getReligionScore() / distributer);

        // Ensure the total equals the calculated amount, assigning the remainder to
        // mundane domains
        int totalSpecialDomains = magicAmount + militaryAmount + religionAmount;
        int mundaneAmount = Math.max(1, amount - totalSpecialDomains + 2);

        String[] domainsArray = new String[magicAmount + militaryAmount + religionAmount + mundaneAmount];
        int index = 0;

        // Assign mundane domains first
        for (int i = 0; i < mundaneAmount; i++) {
            domainsArray[index++] = DataManager.getFactionDomainsMundane()[DataManager.getRandom()
                    .nextInt(DataManager.getFactionDomainsMundane().length)];
        }
        // Assign magic domains
        for (int i = 0; i < magicAmount; i++) {
            domainsArray[index++] = DataManager.getFactionDomainsMagical()[DataManager.getRandom()
                    .nextInt(DataManager.getFactionDomainsMagical().length)];
        }
        // Assign military domains
        for (int i = 0; i < militaryAmount; i++) {
            domainsArray[index++] = DataManager.getFactionDomainsMilitary()[DataManager.getRandom()
                    .nextInt(DataManager.getFactionDomainsMilitary().length)];
        }
        // Assign religious domains
        for (int i = 0; i < religionAmount; i++) {
            domainsArray[index++] = DataManager.getFactionDomainsReligious()[DataManager.getRandom()
                    .nextInt(DataManager.getFactionDomainsReligious().length)];
        }

        return domainsArray;
    }

    public String[] generateGoals() {
        int amount = (factionEntity.getSizeScale() / 10) + (factionEntity.getIntensityScore() / 20);
        String[] goalsArray = new String[amount];
        for (int i = 0; i < amount; i++) {
            goalsArray[i] = DataManager.getFactionGoals()[DataManager.getRandom()
                    .nextInt(DataManager.getFactionGoals().length)];
        }
        return goalsArray;
    }

    /*
     * public String generateStyle() {
     * StringBuilder styleDescription = new
     * StringBuilder("Their organization style is: ");
     * factionEntity.setLeadership(generateLeadership());
     * factionEntity.setJoinRitual(generateJoinRitual());
     * factionEntity.setPowerParameters(generatePowerParameters());
     * return styleDescription.append(factionEntity.getLeadership()).append("\n")
     * .append(factionEntity.getJoinRitual()).append("\n")
     * .append(factionEntity.getPowerParameters()).toString();
     * }
     */

    public Map.Entry<String, Integer> generateLeadership() {
        int rndScore = DataManager.getRandom().nextInt(100);
        int intensityLevel = factionEntity.getIntensityScore() / 20;
        int assignedLeaders = 0; // Initialize number of leaders to return
        String leadershipType; // Initialize leadership type to return
        switch (intensityLevel) {
            case 1 -> {
                if (rndScore < 25) {
                    leadershipType = DataManager.getSourceOfPower()[0]; // Anarchistic
                } else if (rndScore <= 58) {
                    assignedLeaders = DataManager.getRandom().nextInt(5, factionEntity.getSizeScale() * 4 + 5);
                    leadershipType = DataManager.getSourceOfPower()[1]; // Democratic
                } else if (rndScore <= 90) {
                    assignedLeaders = DataManager.getRandom().nextInt(3, factionEntity.getSizeScale() / 5 + 3);
                    leadershipType = DataManager.getSourceOfPower()[2]; // Oligarchic
                } else {
                    assignedLeaders = 1;
                    leadershipType = DataManager.getSourceOfPower()[3]; // Autocratic
                }
            }
            case 2 -> {
                if (rndScore < 10) {
                    leadershipType = DataManager.getSourceOfPower()[0]; // Anarchistic
                } else if (rndScore <= 40) {
                    assignedLeaders = DataManager.getRandom().nextInt(5, factionEntity.getSizeScale() * 4 + 5);
                    leadershipType = DataManager.getSourceOfPower()[1]; // Democratic
                } else if (rndScore <= 80) {
                    assignedLeaders = DataManager.getRandom().nextInt(3, factionEntity.getSizeScale() / 5 + 3);
                    leadershipType = DataManager.getSourceOfPower()[2]; // Oligarchic
                } else {
                    assignedLeaders = 1;
                    leadershipType = DataManager.getSourceOfPower()[3]; // Autocratic
                }
            }
            default -> {
                if (rndScore < 5) {
                    leadershipType = DataManager.getSourceOfPower()[0]; // Anarchistic
                } else if (rndScore <= 30) {
                    assignedLeaders = DataManager.getRandom().nextInt(5, factionEntity.getSizeScale() * 4 + 5);
                    leadershipType = DataManager.getSourceOfPower()[1]; // Democratic
                } else if (rndScore <= 60) {
                    assignedLeaders = DataManager.getRandom().nextInt(3, factionEntity.getSizeScale() / 5 + 3);
                    leadershipType = DataManager.getSourceOfPower()[2]; // Oligarchic
                } else {
                    assignedLeaders = 1;
                    leadershipType = DataManager.getSourceOfPower()[3]; // Autocratic
                }
            }
        }

        // Return both the leadership type and the number of assigned leaders
        return new AbstractMap.SimpleEntry<>(leadershipType, assignedLeaders);
    }

    public String generateJoinRitual() {
        int rndScore = DataManager.getRandom().nextInt(400);
        int helper = switch (factionEntity.getLeadership()) {
            case "Anarchistic" -> -1;
            case "Democratic" -> 0;
            case "Oligarchic" -> 1;
            case "Autocratic" -> 2;
            default -> -300;
        };

        int dedicationValue = rndScore + factionEntity.getIntensityScore() * 5 + helper * 100;
        if (dedicationValue < 300)
            return DataManager.getJoinRitualSimple()[DataManager.getRandom()
                    .nextInt(DataManager.getJoinRitualSimple().length)];
        if (dedicationValue < 600)
            return DataManager.getJoinRitualMedium()[DataManager.getRandom()
                    .nextInt(DataManager.getJoinRitualMedium().length)];
        if (dedicationValue < 1101)
            return DataManager.getJoinRitualHard()[DataManager.getRandom()
                    .nextInt(DataManager.getJoinRitualHard().length)];

        System.out.println("UNDEFINED CONTROL SEQUENCE JOIN RITUAL VALUE = " + dedicationValue);
        System.exit(0);
        return null; // To satisfy compiler
    }

    public Map<String, String> generatePowerParameters() {
        Map<String, String> powerParameters = new HashMap<>();

        int helper = switch (factionEntity.getLeadership()) {
            case "Anarchistic" -> -1;
            case "Democratic" -> 0;
            case "Oligarchic" -> 1;
            case "Autocratic" -> 2;
            default -> -300;
        };

        switch (helper) {
            case 0 -> {
                // Generate democratic voting system details
                String powerType = DataManager.getVotingType()[DataManager.getRandom()
                        .nextInt(DataManager.getVotingType().length)];
                String votingSystem = DataManager.getOliDemoVotingResults()[DataManager.getRandom()
                        .nextInt(DataManager.getOliDemoVotingResults().length)];
                powerParameters.put("PowerType", powerType);
                powerParameters.put("VotingSystem", votingSystem);
            }
            case 1 -> {
                // Generate oligarchic voting system details
                String powerType = DataManager.getOliType()[DataManager.getRandom()
                        .nextInt(DataManager.getOliType().length)];
                String votingSystem = DataManager.getOliDemoVotingResults()[DataManager.getRandom()
                        .nextInt(DataManager.getOliDemoVotingResults().length)];
                powerParameters.put("PowerType", powerType);
                powerParameters.put("VotingSystem", votingSystem);
            }
            case 2 -> {
                // Generate autocratic leadership details
                String powerType = DataManager.getAutocracyType()[DataManager.getRandom()
                        .nextInt(DataManager.getAutocracyType().length)];
                powerParameters.put("PowerType", powerType);
                powerParameters.put("VotingSystem", "N/A"); // Autocracy doesn't have voting
            }
            case -1 -> {
                // Handle anarchistic system
                powerParameters.put("PowerType", "Anarchistic communion");
                powerParameters.put("VotingSystem", "N/A");
            }
            default -> {
            }
        }

        return powerParameters;
    }

    /*
     * public String generateParameters() {
     * int valuesCount = Math.max(1,
     * Math.min(factionEntity.getSizeScale() / 10 + (int)
     * (factionEntity.getIntensityScore() / 20), 7));
     * int moneyCount = Math.max(1,
     * Math.min((factionEntity.getSizeScale() + factionEntity.getFinanceScore()) /
     * 20, 7));
     * int doctrineCount = Math.max(1, Math.min((int)
     * (factionEntity.getIntensityScore() / 20), 5));
     *
     * String[] factionValues = new String[valuesCount];
     * factionEntity.setFactionValues(factionValues);
     * factionEntity.getMoneySources().clear();
     * String[] doctrines = new String[doctrineCount];
     * factionEntity.setDoctrines(doctrines);
     *
     * factionEntity.getMoneySources().put("Low", new ArrayList<>());
     * factionEntity.getMoneySources().put("Mid", new ArrayList<>());
     * factionEntity.getMoneySources().put("High", new ArrayList<>());
     * factionEntity.getMoneySources().put("Insane", new ArrayList<>());
     *
     * StringBuilder valuesStr = new StringBuilder("Their values are: \n");
     * StringBuilder moneyStr = new StringBuilder("They finance themselves by: \n");
     * StringBuilder doctrineStr = new StringBuilder("They teach: \n");
     *
     * for (int i = 0; i < valuesCount; i++) {
     * factionValues[i] = DataManager.getVirtues()[DataManager.getRandom()
     * .nextInt(DataManager.getVirtues().length)];
     * valuesStr.append(factionValues[i]).append(", ");
     * }
     *
     * int reducer = 0;
     * List<String> list = new ArrayList<>();
     * for (int i = 0; i < moneyCount; i++) {
     * int rndScore = DataManager.getRandom().nextInt(101);
     * int finances = rndScore + 3 * factionEntity.getFinanceScore() - reducer * 7;
     *
     * String jobCategory;
     * String randomJob;
     *
     * if (finances < 70) {
     * jobCategory = "Low";
     * randomJob =
     * generateWeightedRandomGeneralJob(DataManager.getLowJobMappings());
     * } else if (finances < 200) {
     * jobCategory = "Mid";
     * randomJob =
     * generateWeightedRandomGeneralJob(DataManager.getMidJobMappings());
     * } else if (finances < 350) {
     * jobCategory = "High";
     * randomJob =
     * generateWeightedRandomGeneralJob(DataManager.getHighJobMappings());
     * reducer += 1;
     * } else {
     * jobCategory = "Insane";
     * randomJob =
     * generateWeightedRandomGeneralJob(DataManager.getSuperJobMappings());
     * reducer += 5;
     * i += 2;
     * }
     *
     * factionEntity.getMoneySources().get(jobCategory).add(randomJob);
     * list.add(randomJob);
     * }
     *
     * for (int i = 0; i < doctrineCount; i++) {
     * doctrines[i] = DataManager.getDoctrines()[DataManager.getRandom()
     * .nextInt(DataManager.getDoctrines().length)];
     * doctrineStr.append(doctrines[i]).append(", ");
     * }
     *
     * return valuesStr.toString() + "\n" + moneyStr.toString() + String.join(", ",
     * list) + "\n"
     * + doctrineStr.toString();
     * }
     */

    public String[] generateValues() {
        int valuesCount = Math.max(1,
                Math.min(factionEntity.getSizeScale() / 10 + (factionEntity.getIntensityScore() / 20), 7));
        String[] factionValues = new String[valuesCount];

        for (int i = 0; i < valuesCount; i++) {
            factionValues[i] = DataManager.getVirtues()[DataManager.getRandom()
                    .nextInt(DataManager.getVirtues().length)];
        }
        return factionValues; // Returns an array of generated values
    }

    public Map<String, List<String>> generateMoneySources() {
        int moneyCount = Math.max(1,
                Math.min((factionEntity.getSizeScale() + factionEntity.getFinanceScore()) / 20, 7));
        Map<String, List<String>> moneySources = new HashMap<>();
        moneySources.put("Low", new ArrayList<>());
        moneySources.put("Mid", new ArrayList<>());
        moneySources.put("High", new ArrayList<>());
        moneySources.put("Insane", new ArrayList<>());

        int reducer = 0;
        for (int i = 0; i < moneyCount; i++) {
            int rndScore = DataManager.getRandom().nextInt(101);
            int finances = rndScore + 3 * factionEntity.getFinanceScore() - reducer * 7;

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
                i += 2; // Skip the next two money sources
            }

            moneySources.get(jobCategory).add(randomJob);
        }

        return moneySources; // Returns a map of money sources
    }

    public String[] generateDoctrines() {
        int doctrineCount = Math.max(1, Math.min(factionEntity.getIntensityScore() / 20, 5));
        String[] doctrines = new String[doctrineCount];

        for (int i = 0; i < doctrineCount; i++) {
            doctrines[i] = DataManager.getDoctrines()[DataManager.getRandom()
                    .nextInt(DataManager.getDoctrines().length)];
        }
        return doctrines; // Returns an array of generated doctrines
    }

    /*
     * public String generateStandings() {
     * adjustScores();
     * int magicIndex = Math.min(Math.max(0,
     * (int) Math.round((double) factionEntity.getMagicScore() / 10) +
     * DataManager.getRandom().nextInt(-1, 2)),
     * DataManager.getWealth().length - 1);
     * int intensityIndex = Math.min(
     * Math.max(0,
     * (int) (factionEntity.getIntensityScore() / 20) - 1 +
     * DataManager.getRandom().nextInt(-1, 2)),
     * DataManager.getIntensity().length - 1);
     * int scaleIndex = Math.min(Math.max(0,
     * (int) Math.round((double) factionEntity.getSizeScale() / 5) +
     * DataManager.getRandom().nextInt(-1, 2)),
     * DataManager.getSize().length - 1);
     * int militaryIndex = Math.min(
     * Math.max(0,
     * (int) Math.round((double) factionEntity.getMilitaristicScore() / 10)
     * + DataManager.getRandom().nextInt(-1, 2)),
     * DataManager.getMilitaryInclination().length - 1);
     * int financeIndex = Math.min(Math.max(0, (int) Math.round((double)
     * factionEntity.getFinanceScore() / 10)
     * + DataManager.getRandom().nextInt(-1, 2)), DataManager.getWealth().length -
     * 1);
     * int reputationIndex = Math.min(Math.max(0, (int) Math.round((double)
     * factionEntity.getReputationScore() / 10)
     * + DataManager.getRandom().nextInt(-1, 2)), DataManager.getReputation().length
     * - 1);
     *
     * String[] standingsArray = new String[6];
     * factionEntity.setStandingsArray(standingsArray);
     * standingsArray[0] = DataManager.getMagicalInclination()[magicIndex];
     * standingsArray[1] = DataManager.getMilitaryInclination()[militaryIndex];
     * standingsArray[2] = DataManager.getWealth()[financeIndex];
     * standingsArray[3] = DataManager.getSize()[scaleIndex];
     * standingsArray[4] = DataManager.getIntensity()[intensityIndex];
     * standingsArray[5] = DataManager.getReputation()[reputationIndex];
     *
     * return String.join("\n", standingsArray);
     * }
     */

    private void adjustScores() {
        int highestScore = Math.max(factionEntity.getMagicScore(), Math.max(factionEntity.getSizeScale(),
                Math.max(factionEntity.getMilitaristicScore(), factionEntity.getFinanceScore())));

        if (highestScore >= 70) {
            if (factionEntity.getMagicScore() != highestScore && highestScore - 30 > factionEntity.getMagicScore())
                factionEntity.setMagicScore(factionEntity.getMagicScore() + 15);
            if (factionEntity.getSizeScale() != highestScore && highestScore - 30 > factionEntity.getSizeScale())
                factionEntity.setSizeScale(factionEntity.getSizeScale() + 15);
            if (factionEntity.getMilitaristicScore() != highestScore
                    && highestScore - 20 > factionEntity.getMilitaristicScore())
                factionEntity.setMilitaristicScore(factionEntity.getMilitaristicScore() + 15);
            if (factionEntity.getFinanceScore() != highestScore && highestScore - 20 > factionEntity.getFinanceScore())
                factionEntity.setFinanceScore(factionEntity.getFinanceScore() + 15);

            factionEntity.setMagicScore(Math.min(factionEntity.getMagicScore(), 100));
            factionEntity.setSizeScale(Math.min(factionEntity.getSizeScale(), 100));
            factionEntity.setMilitaristicScore(Math.min(factionEntity.getMilitaristicScore(), 100));
            factionEntity.setFinanceScore(Math.min(factionEntity.getFinanceScore(), 100));
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

    public Faction getFactionEntity() {
        return factionEntity;
    }
}
