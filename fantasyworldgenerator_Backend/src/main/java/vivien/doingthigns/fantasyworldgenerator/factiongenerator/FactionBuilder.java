package vivien.doingthigns.fantasyworldgenerator.factiongenerator;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.*;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import vivien.doingthigns.fantasyworldgenerator.data.DataManager;
import vivien.doingthigns.fantasyworldgenerator.people.People;
import org.springframework.transaction.annotation.Transactional;

@Component
public class FactionBuilder {

    @Autowired
    @PersistenceContext
    private EntityManager entityManager;
    private String primeHeritage = "";
    private String secondHeritage = "";
    private String name = "";
    private String goals = "";
    private String domains = "";
    private String style = "";
    private String parameters = "";
    private String standing = "";
    private String[] parts = {};
    private final int numParameters = 9;
    private Faction myFaction;

    // Generate a faction with input parts, dynamically filling in any missing parts
    @Transactional
    public void generateFaction(String[] inputParts) {
        // Validate and adjust input length, adding random values for missing parameters
        int totalLength = numParameters + 2; // 9 for faction + 2 for heritages
        inputParts = Arrays.copyOf(inputParts, totalLength);
        fillMissingParts(inputParts);

        // Extract heritages
        primeHeritage = inputParts[numParameters]; // Primary Ancestry
        secondHeritage = inputParts[numParameters + 1]; // Secondary Ancestry

        // Validate the parameters and update the faction data
        String[] factionParts = Arrays.copyOf(inputParts, numParameters);
        if (validateParameters(factionParts)) {
            parts = factionParts;
        } else {
            throw new IllegalArgumentException("Invalid parameters provided.");
        }

        // Convert parts to integer parameters and create the Faction object
        int[] inParameters = Arrays.stream(parts).mapToInt(Integer::parseInt).toArray();
        myFaction = new Faction(inParameters[0], inParameters[1], inParameters[2], inParameters[3], inParameters[4],
                inParameters[5], inParameters[6], inParameters[7], inParameters[8], primeHeritage, secondHeritage);

        updateFactionData();
        entityManager.persist(myFaction);
    }

    // Fill missing input parts with random values
    private String[] fillMissingParts(String[] inputParts) {
        for (int i = 0; i < numParameters + 2; i++) {
            if (inputParts[i] == null || inputParts[i].isEmpty()) {
                inputParts[i] = getRandomValueForParameter(i);
            }
        }
        return inputParts;
    }

    // Return faction details as a string
    public String getFactionDetails() {
        return "Name: " + name + "\nStanding: " + standing + "\nGoals: " + goals + "\nDomains: " + domains +
                "\nStyle: " + style + "\nParameters: " + parameters;
    }

    // Save faction details to a file
    public void saveFaction() {
        try {
            Path fullPath = Paths.get("Generated Factions", name);
            Files.createDirectories(fullPath);

            try (BufferedWriter outputFile = Files.newBufferedWriter(fullPath.resolve(name + ".md"))) {
                outputFile.write("\n" + standing + "\n");
                outputFile.write(goals + "\n");
                outputFile.write(domains + "\n");
                outputFile.write(style + "\n");
                outputFile.write(parameters + "\n");
            }

            // Populate faction
            People populator = new People();
            populator.populateFaction(myFaction);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Validate that input parameters are within range and can be parsed
    private boolean validateParameters(String[] inputParts) {
        for (String part : inputParts) {
            try {
                int value = Integer.parseInt(part);
                if (value < 0 || value > 100) {
                    return false;
                }
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return true;
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
        name = myFaction.generateName() != null ? myFaction.generateName() : "Unknown Name";
        goals = myFaction.generateGoals() != null ? myFaction.generateGoals() : "Unknown Goals";
        domains = myFaction.generateDomains() != null ? myFaction.generateDomains() : "Unknown Domains";
        style = myFaction.generateStyle() != null ? myFaction.generateStyle() : "Unknown Style";
        parameters = myFaction.generateParameters() != null ? myFaction.generateParameters() : "Unknown Parameters";
        standing = myFaction.generateStandings() != null ? myFaction.generateStandings() : "Unknown Standing";
    }

    // Print faction details to console
    public void printFaction() {
        System.out.println("Faction Details:");
        System.out.println(getFactionDetails());
    }
    public void saveFactionToDatabase() {
        entityManager.persist(myFaction);  // Save the faction to the database
    }
}
