package vivien.doingthigns.fantasyworldgenerator.factiongenerator;

import java.util.*;


//import org.hibernate.mapping.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.Getter;
import lombok.Setter;
import vivien.doingthigns.fantasyworldgenerator.data.DataManager;
import org.springframework.transaction.annotation.Transactional;

@Setter
@Getter
@Component
public class FactionBuilder {

    @Autowired
    @PersistenceContext
    private EntityManager entityManager;

    private String primeHeritage = "";
    private String secondHeritage = "";
    private String name = "";
    private String[] goals = new String[0];
    private String[] domains = new String[0];
    private String[] doctriens = new String[0];
    private java.util.Map.Entry<String, Integer> leadership;
    private String joinRitual = "";
    private Map<String, String> powerParameters;
    private String[] factionValues = {};
    private String[] parts = {};
    private Map<String, List<String>> moneySources;
    private final int numParameters = 11;
    @Getter
    private Faction myFaction;
    private FactionGenerator factionGenerator;

    // Generate a faction with input parts, dynamically filling in any missing parts
    @Transactional
    public void generateFaction(String[] inputParts) {
        // Validate and adjust input length, adding random values for missing parameters
        fillMissingParts(inputParts);

        // Extract heritages
        primeHeritage = inputParts[numParameters - 2]; // Primary Ancestry
        secondHeritage = inputParts[numParameters - 1]; // Secondary Ancestry
        parts = new String[inputParts.length-2];
        if (validateParameters(inputParts)) {
            for (int i = 0; i < inputParts.length-2; i++) {
                parts[i] = inputParts[i];
            }
        } else {
            throw new IllegalArgumentException("Invalid parameters provided.");
        }

        // Convert parts to integer parameters and create the Faction object
        int[] inParameters = Arrays.stream(parts).mapToInt(Integer::parseInt).toArray();
        myFaction = new Faction();
        myFaction.setSizeScale(inParameters[0]);
        myFaction.setFinanceScore(inParameters[1]);
        myFaction.setMagicScore(inParameters[2]);
        myFaction.setMilitaristicScore(inParameters[3]);
        myFaction.setReligionScore(inParameters[4]);
        myFaction.setReputationScore(inParameters[5]);
        myFaction.setIntensityScore(inParameters[6]);
        myFaction.setPrimPercent(inParameters[7]);
        myFaction.setSecPercent(inParameters[8]);
        myFaction.setPrimHeritageName(primeHeritage);
        myFaction.setSecHeritageName(secondHeritage);
        factionGenerator = new FactionGenerator(myFaction);
        updateFactionData();
        myFaction.setFacName(name);
        myFaction.setGoalsArray(goals);
        myFaction.setDoctrines(doctriens);
        myFaction.setLeadership(leadership.getKey());
        myFaction.setAssignedLeaders(leadership.getValue());
        myFaction.setJoinRitual(joinRitual);
        myFaction.setFactionValues(factionValues);
        myFaction.setDomainsArray(domains);

        myFaction.setMoneySources(moneySources);

        //tertiary thing to be set, requres leadership to be set
        powerParameters = factionGenerator.generatePowerParameters();
        myFaction.setVotingSystem(powerParameters.get("VotingSystem"));
        myFaction.setPowerType(powerParameters.get("PowerType"));

        entityManager.persist(myFaction);
    }

    // Fill missing input parts with random values
    private String[] fillMissingParts(String[] inputParts) {
        for (int i = 0; i < numParameters-2; i++) {
            if (inputParts[i] == null || inputParts[i].isEmpty()) {
                inputParts[i] = getRandomValueForParameter(i);
            }
        }
        return inputParts;
    }



    // Validate that input parameters are within range and can be parsed
    private boolean validateParameters(String[] inputParts) {
        // Iterate through the first numParameters elements, ignoring the last two (heritages)
        for (int i = 0; i < numParameters-2; i++) {
            String part = inputParts[i];
            try {
                int value = Integer.parseInt(part);
                if (value < 0 || value > 100) {
                    return false; // Invalid value range
                }
            } catch (NumberFormatException e) {
                System.out.println(e.toString());
                return false; // Non-numeric input
            }
        }
        return true; // All parameters are valid
    }

    // Generate random values for parts array
    private String getRandomValueForParameter(int index) {
        if (index < numParameters) {
            return String.valueOf(DataManager.getRandom().nextInt(1, 101)); // Generate between 1 and 100
        } else {
            return "Human"; // Default for Ancestry (primeHeritage and secondHeritage)
        }
    }

    // Update faction details based on faction data
    private void updateFactionData() {
        name = factionGenerator.generateName();
        goals = factionGenerator.generateGoals();
        domains = factionGenerator.generateDomains();
        leadership = factionGenerator.generateLeadership();
        joinRitual = factionGenerator.generateJoinRitual();
        factionValues = factionGenerator.generateValues();
        doctriens = factionGenerator.generateDoctrines();
        moneySources = factionGenerator.generateMoneySources();
        //standing = factionGenerator.generateStandings();
    }

    public void saveFactionToDatabase() {
        entityManager.persist(myFaction); // Save the faction to the database
    }

    
}
