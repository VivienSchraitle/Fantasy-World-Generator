package vivien.doingthigns.fantasyworldgenerator.data;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.fasterxml.jackson.core.type.TypeReference;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Paths;
import java.util.*;

@Setter
@Getter
public class DataManager {
    @Setter
    @Getter
    private static String[] attribute;
    @Setter
    @Getter
    private static String[] mainName;
    @Setter
    @Getter
    private static String[] factionDomainsMilitary;
    @Setter
    @Getter
    private static String[] factionDomainsMundane;
    @Setter
    @Getter
    private static String[] factionDomainsMagical;
    @Setter
    @Getter
    private static String[] factionDomainsReligious;
    @Setter
    @Getter
    private static String[] factionGoals;
    @Setter
    @Getter
    private static String[] virtues;
    @Setter
    @Getter
    private static Map<String, List<String>> superJobMappings;
    @Setter
    @Getter
    private static Map<String, List<String>> highJobMappings;
    @Setter
    @Getter
    private static Map<String, List<String>> midJobMappings;
    @Setter
    @Getter
    private static Map<String, List<String>> lowJobMappings;
    @Setter
    @Getter
    private static String[] doctrines;
    @Setter
    @Getter
    private static String[] factionEssence;
    @Setter
    @Getter
    private static String[] sourceOfPower;
    @Setter
    @Getter
    private static String[] votingType;
    @Setter
    @Getter
    private static String[] oliType;
    @Setter
    @Getter
    private static String[] oliDemoVotingResults;
    @Setter
    @Getter
    private static String[] autocracyType;
    @Setter
    @Getter
    private static String[] joinRitualSimple;
    @Setter
    @Getter
    private static String[] joinRitualMedium;
    @Setter
    @Getter
    private static String[] joinRitualHard;
    @Setter
    @Getter
    private static String[] reputation;
    @Setter
    @Getter
    private static String[] size;
    @Setter
    @Getter
    private static String[] wealth;
    @Setter
    @Getter
    private static String[] magicalInclination;
    @Setter
    @Getter
    private static String[] militaryInclination;
    @Setter
    @Getter
    private static String[] intensity;
    @Setter
    @Getter

    private static String[] defaultSkinColor;
    @Setter
    @Getter
    private static String[] defaultUndertones;
    @Setter
    @Getter
    private static String[] defaultHairColor;
    @Setter
    @Getter
    private static String[] defaultHairstyles;
    @Setter
    @Getter
    private static String[] defaultEyeColor;
    @Setter
    @Getter
    private static String[] adjectiveSizes;
    @Setter
    @Getter
    private static String[] classes;
    @Setter
    @Getter
    private static String[] success;
    @Setter
    @Getter
    private static Random random;
    @Setter
    @Getter
    private static List<Ancestry> ancestries = new ArrayList<>();
    @Setter
    @Getter
    private static List<Heritage> heritages = new ArrayList<>();

    static {
        random = new Random();
        int seed = random.nextInt();
        loadFactionData();
        loadPeopleData();
        random = new Random(Objects.hash(seed));
    }

    @Getter
    @Setter
    public static class Ancestry implements Serializable {
        private String name;
        private int lh;
        private float sizeAvg;
        public float sizeDev;
        private String[] skinColor;
        private String[] undertones;
        private String[] hairColor;
        private String[] hairstyles;
        private String[] eyeColor;
        private String[][] optionalSpecialTraits;
        private String[][] certainSpecialTraits;
        private String[] heritage;
        private int maxAge;
        private int matureAge;
    }

    @Getter
    @Setter
    public static class Heritage implements Serializable {
        private String name;
        private int lh;
        private String[] skinColor;
        private String[] undertones;
        private String[] hairColor;
        private String[] hairstyles;
        private String[] eyeColor;
        private String[][] optionalSpecialTraits;
        private String[][] certainSpecialTraits;
    }

    public static void loadFactionData() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String path = Paths.get("").toAbsolutePath().normalize().toString();
            // path = new File(path).getParent();

            attribute = deserializeJsonArray(objectMapper,
                    new File(path, "fantasyworldgenerator_Backend/JSONs/Faction/FactionAttribute.JSON"));
            mainName = deserializeJsonArray(objectMapper,
                    new File(path, "fantasyworldgenerator_Backend/JSONs/Faction/FactionName.JSON"));
            factionDomainsMilitary = deserializeJsonArray(objectMapper,
                    new File(path, "fantasyworldgenerator_Backend/JSONs/Faction/factionDomainsMilitary.JSON"));
            factionDomainsMundane = deserializeJsonArray(objectMapper,
                    new File(path, "fantasyworldgenerator_Backend/JSONs/Faction/factionDomainsMundane.JSON"));
            factionDomainsReligious = deserializeJsonArray(objectMapper,
                    new File(path, "fantasyworldgenerator_Backend/JSONs/Faction/factionDomainsReligious.JSON"));
            factionDomainsMagical = deserializeJsonArray(objectMapper,
                    new File(path, "fantasyworldgenerator_Backend/JSONs/Faction/factionDomainsMagical.JSON"));
            virtues = deserializeJsonArray(objectMapper,
                    new File(path, "fantasyworldgenerator_Backend/JSONs/Faction/Virtues.JSON"));
            factionGoals = deserializeJsonArray(objectMapper,
                    new File(path, "fantasyworldgenerator_Backend/JSONs/Faction/Goals.JSON"));
            superJobMappings = deserializeJsonMap(objectMapper,
                    new File(path, "fantasyworldgenerator_Backend/JSONs/Faction/InsaneFinances.JSON"));
            highJobMappings = deserializeJsonMap(objectMapper,
                    new File(path, "fantasyworldgenerator_Backend/JSONs/Faction/HighFinances.JSON"));
            midJobMappings = deserializeJsonMap(objectMapper,
                    new File(path, "fantasyworldgenerator_Backend/JSONs/Faction/MidFinances.JSON"));
            lowJobMappings = deserializeJsonMap(objectMapper,
                    new File(path, "fantasyworldgenerator_Backend/JSONs/Faction/LowFinances.JSON"));
            doctrines = deserializeJsonArray(objectMapper,
                    new File(path, "fantasyworldgenerator_Backend/JSONs/Faction/Doctrines.JSON"));
            factionEssence = deserializeJsonArray(objectMapper,
                    new File(path, "fantasyworldgenerator_Backend/JSONs/Faction/FactionEssence.JSON"));
            sourceOfPower = deserializeJsonArray(objectMapper,
                    new File(path, "fantasyworldgenerator_Backend/JSONs/Faction/SourceOfPower.JSON"));
            votingType = deserializeJsonArray(objectMapper,
                    new File(path, "fantasyworldgenerator_Backend/JSONs/Faction/VotingType.JSON"));
            oliType = deserializeJsonArray(objectMapper,
                    new File(path, "fantasyworldgenerator_Backend/JSONs/Faction/OliType.JSON"));
            oliDemoVotingResults = deserializeJsonArray(objectMapper,
                    new File(path, "fantasyworldgenerator_Backend/JSONs/Faction/OliDemoVotingResults.JSON"));
            autocracyType = deserializeJsonArray(objectMapper,
                    new File(path, "fantasyworldgenerator_Backend/JSONs/Faction/AutocracyType.JSON"));
            joinRitualSimple = deserializeJsonArray(objectMapper,
                    new File(path, "fantasyworldgenerator_Backend/JSONs/Faction/JoinRitualSimple.JSON"));
            joinRitualMedium = deserializeJsonArray(objectMapper,
                    new File(path, "fantasyworldgenerator_Backend/JSONs/Faction/JoinRitualMedium.JSON"));
            joinRitualHard = deserializeJsonArray(objectMapper,
                    new File(path, "fantasyworldgenerator_Backend/JSONs/Faction/JoinRitualHard.JSON"));
            reputation = deserializeJsonArray(objectMapper,
                    new File(path, "fantasyworldgenerator_Backend/JSONs/Faction/Reputation.JSON"));
            size = deserializeJsonArray(objectMapper,
                    new File(path, "fantasyworldgenerator_Backend/JSONs/Faction/Size.JSON"));
            wealth = deserializeJsonArray(objectMapper,
                    new File(path, "fantasyworldgenerator_Backend/JSONs/Faction/Wealth.JSON"));
            magicalInclination = deserializeJsonArray(objectMapper,
                    new File(path, "fantasyworldgenerator_Backend/JSONs/Faction/MagicalInclination.JSON"));
            militaryInclination = deserializeJsonArray(objectMapper,
                    new File(path, "fantasyworldgenerator_Backend/JSONs/Faction/MilitaryInclination.JSON"));
            intensity = deserializeJsonArray(objectMapper,
                    new File(path, "fantasyworldgenerator_Backend/JSONs/Faction/Intensity.JSON"));
        } catch (JsonProcessingException e) {
            System.err.println("Error during JSON deserialization: " + e.getMessage());
            System.exit(0);
        } catch (IOException e) {
            System.err.println("File I/O error: " + e.getMessage());
            System.exit(0);
        }
    }

    public static void loadPeopleData() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String path = Paths.get("").toAbsolutePath().normalize().toString();
            // path = new File(path).getParent();

            loadAncestries(objectMapper, new File(path, "fantasyworldgenerator_Backend/JSONs/People/Ancestires.JSON"));
            loadHeritages(objectMapper, new File(path, "fantasyworldgenerator_Backend/JSONs/People/Heriatage.JSON"));
            classes = deserializeJsonArray(objectMapper,
                    new File(path, "fantasyworldgenerator_Backend/JSONs/People/Classes.JSON"));
            success = deserializeJsonArray(objectMapper,
                    new File(path, "fantasyworldgenerator_Backend/JSONs/People/Success.JSON"));
        } catch (JsonProcessingException e) {
            System.err.println("Error during JSON deserialization: " + e.getMessage());
            System.exit(0);
        } catch (IOException e) {
            System.err.println("File I/O error: " + e.getMessage());
            System.exit(0);
        }

        convertAncestriesToHeritages();
    }

    private static void loadAncestries(ObjectMapper objectMapper, File filePath) {
        try {
            if (filePath.exists()) {
                List<Map<String, JsonNode>> jsonObjects = objectMapper.readValue(filePath,
                        new TypeReference<List<Map<String, JsonNode>>>() {
                        });

                if (jsonObjects.isEmpty()) {
                    System.out.println("Ancestry data is empty or invalid.");
                    return;
                }

                Map<String, JsonNode> defaultValues = jsonObjects.get(0);

                defaultSkinColor = deserializeJsonArray(objectMapper, defaultValues.get("DefaultSkinColor"));
                defaultUndertones = deserializeJsonArray(objectMapper, defaultValues.get("DefaultUndertones"));
                defaultHairColor = deserializeJsonArray(objectMapper, defaultValues.get("DefaultHairColor"));
                defaultHairstyles = deserializeJsonArray(objectMapper, defaultValues.get("DefaultHairstyles"));
                defaultEyeColor = deserializeJsonArray(objectMapper, defaultValues.get("DefaultEyeColor"));
                adjectiveSizes = deserializeJsonArray(objectMapper, defaultValues.get("AdjectiveSizes"));

                for (int i = 1; i < jsonObjects.size(); i++) {
                    Map<String, JsonNode> ancestryJson = jsonObjects.get(i);
                    Ancestry ancestry = new Ancestry();

                    ancestry.name = ancestryJson.get("Name").asText("Unknown");
                    ancestry.lh = ancestryJson.get("LH").asInt(0);
                    ancestry.sizeAvg = ancestryJson.get("SizeAvg").floatValue();
                    ancestry.sizeDev = ancestryJson.get("SizeDev").floatValue();
                    ancestry.skinColor = resolveDefaults(
                            deserializeJsonArray(objectMapper, ancestryJson.get("SkinColor")), defaultSkinColor);
                    ancestry.undertones = resolveDefaults(
                            deserializeJsonArray(objectMapper, ancestryJson.get("Undertones")), defaultUndertones);
                    ancestry.hairColor = resolveDefaults(
                            deserializeJsonArray(objectMapper, ancestryJson.get("HairColor")), defaultHairColor);
                    ancestry.hairstyles = resolveDefaults(
                            deserializeJsonArray(objectMapper, ancestryJson.get("Hairstyles")), defaultHairstyles);
                    ancestry.eyeColor = resolveDefaults(
                            deserializeJsonArray(objectMapper, ancestryJson.get("EyeColor")), defaultEyeColor);
                    ancestry.optionalSpecialTraits = deserializeSpecialTraits(objectMapper,
                            ancestryJson.get("OptionalSpecialTraits"));
                    ancestry.certainSpecialTraits = deserializeSpecialTraits(objectMapper,
                            ancestryJson.get("CertainSpecialTraits"));
                    ancestry.heritage = deserializeJsonArray(objectMapper, ancestryJson.get("Heritage"));
                    ancestry.maxAge = ancestryJson.get("MaxAge").asInt(0);
                    ancestry.matureAge = ancestryJson.get("MatureAge").asInt(0);

                    expandSpecialTraits(ancestry.optionalSpecialTraits);
                    expandSpecialTraits(ancestry.certainSpecialTraits);

                    ancestries.add(ancestry);
                }
            } else {
                System.out.println("File " + filePath + " not found.");
            }
        } catch (IOException e) {
            System.err.println("Error reading or deserializing the JSON file: " + e.getMessage());
        }
    }

    private static void loadHeritages(ObjectMapper objectMapper, File filePath) {
        try {
            if (filePath.exists()) {
                List<Map<String, JsonNode>> jsonObjects = objectMapper.readValue(filePath,
                        new TypeReference<List<Map<String, JsonNode>>>() {
                        });

                if (jsonObjects.isEmpty()) {
                    System.out.println("Heritage data is empty or invalid.");
                    return;
                }

                Map<String, JsonNode> defaultValues = jsonObjects.get(0);

                defaultSkinColor = deserializeJsonArray(objectMapper, defaultValues.get("DefaultSkinColor"));
                defaultUndertones = deserializeJsonArray(objectMapper, defaultValues.get("DefaultUndertones"));
                defaultHairColor = deserializeJsonArray(objectMapper, defaultValues.get("DefaultHairColor"));
                defaultHairstyles = deserializeJsonArray(objectMapper, defaultValues.get("DefaultHairstyles"));
                defaultEyeColor = deserializeJsonArray(objectMapper, defaultValues.get("DefaultEyeColor"));
                adjectiveSizes = deserializeJsonArray(objectMapper, defaultValues.get("AdjectiveSizes"));

                for (int i = 1; i < jsonObjects.size(); i++) {
                    Map<String, JsonNode> heritageJson = jsonObjects.get(i);
                    Heritage heritage = new Heritage();

                    heritage.name = heritageJson.get("Name").asText("Unknown");
                    heritage.lh = heritageJson.get("LH").asInt(0);
                    heritage.skinColor = resolveDefaults(
                            deserializeJsonArray(objectMapper, heritageJson.get("SkinColor")), defaultSkinColor);
                    heritage.undertones = resolveDefaults(
                            deserializeJsonArray(objectMapper, heritageJson.get("Undertones")), defaultUndertones);
                    heritage.hairColor = resolveDefaults(
                            deserializeJsonArray(objectMapper, heritageJson.get("HairColor")), defaultHairColor);
                    heritage.hairstyles = resolveDefaults(
                            deserializeJsonArray(objectMapper, heritageJson.get("Hairstyles")), defaultHairstyles);
                    heritage.eyeColor = resolveDefaults(
                            deserializeJsonArray(objectMapper, heritageJson.get("EyeColor")), defaultEyeColor);
                    heritage.optionalSpecialTraits = deserializeSpecialTraits(objectMapper,
                            heritageJson.get("OptionalSpecialTraits"));
                    heritage.certainSpecialTraits = deserializeSpecialTraits(objectMapper,
                            heritageJson.get("CertainSpecialTraits"));

                    expandSpecialTraits(heritage.optionalSpecialTraits);
                    expandSpecialTraits(heritage.certainSpecialTraits);

                    heritages.add(heritage);
                }
            } else {
                System.out.println("File " + filePath + " not found.");
            }
        } catch (IOException e) {
            System.err.println("Error reading or deserializing the JSON file: " + e.getMessage());
        }
    }

    private static void convertAncestriesToHeritages() {
        for (Ancestry ances : ancestries) {
            Heritage heritage = new Heritage();
            heritage.name = ances.name;
            heritage.lh = ances.lh;
            heritage.certainSpecialTraits = ances.certainSpecialTraits;
            heritage.eyeColor = ances.eyeColor;
            heritage.hairColor = ances.hairColor;
            heritage.hairstyles = ances.hairstyles;
            heritage.optionalSpecialTraits = ances.optionalSpecialTraits;
            heritage.skinColor = ances.skinColor;
            heritage.undertones = ances.undertones;
            heritages.add(heritage);
        }
    }

    // Helper methods for deserialization and defaults

    private static String[] deserializeJsonArray(ObjectMapper objectMapper, File file) throws IOException {
        return objectMapper.readValue(file, String[].class);
    }

    private static String[] deserializeJsonArray(ObjectMapper objectMapper, JsonNode jsonNode) {
        if (jsonNode == null || !jsonNode.isArray()) {
            return new String[0];
        }
        List<String> result = new ArrayList<>();
        for (JsonNode node : jsonNode) {
            result.add(node.asText());
        }
        return result.toArray(new String[0]);
    }

    private static Map<String, List<String>> deserializeJsonMap(ObjectMapper objectMapper, File file)
            throws IOException {
        return objectMapper.readValue(file, new TypeReference<Map<String, List<String>>>() {
        });
    }

    private static String[][] deserializeSpecialTraits(ObjectMapper objectMapper, JsonNode jsonNode) {
        if (jsonNode == null || !jsonNode.isArray()) {
            return new String[0][];
        }
        List<String[]> result = new ArrayList<>();
        for (JsonNode arrayNode : jsonNode) {
            List<String> traitList = new ArrayList<>();
            for (JsonNode traitNode : arrayNode) {
                traitList.add(traitNode.asText());
            }
            result.add(traitList.toArray(new String[0]));
        }
        return result.toArray(new String[0][]);
    }

    private static void expandSpecialTraits(String[][] specialTraits) {
        for (int j = 0; j < specialTraits.length; j++) {
            for (String specialTrait : specialTraits[j]) {
                if (specialTrait.contains("{AdjectiveSizes}")) {
                    specialTraits[j] = Arrays.stream(adjectiveSizes)
                            .map(adj -> specialTrait.replace("{AdjectiveSizes}", adj)).toArray(String[]::new);
                }
            }
        }
    }

    private static String[] resolveDefaults(String[] values, String[] defaults) {
        if (values.length > 0 && "default".equalsIgnoreCase(values[0])) {
            return concatenate(defaults, Arrays.copyOfRange(values, 1, values.length));
        }
        return values;
    }

    private static String[] concatenate(String[] a, String[] b) {
        String[] result = new String[a.length + b.length];
        System.arraycopy(a, 0, result, 0, a.length);
        System.arraycopy(b, 0, result, a.length, b.length);
        return result;
    }
}
