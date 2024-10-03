package vivien.doingthigns.fantasyworldgenerator.data;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Paths;
import java.util.*;

public class DataManager {

    private static String[] attribute;
    private static String[] mainName;
    private static String[] factionDomainsMilitary;
    private static String[] factionDomainsMundane;
    private static String[] factionDomainsMagical;
    private static String[] factionDomainsReligious;
    private static String[] factionGoals;
    private static String[] virtues;
    private static Map<String, List<String>> superJobMappings;
    private static Map<String, List<String>> highJobMappings;
    private static Map<String, List<String>> midJobMappings;
    private static Map<String, List<String>> lowJobMappings;
    private static String[] doctrines;
    private static String[] factionEssence;
    private static String[] sourceOfPower;
    private static String[] votingType;
    private static String[] oliType;
    private static String[] oliDemoVotingResults;
    private static String[] autocracyType;
    private static String[] joinRitualSimple;
    private static String[] joinRitualMedium;
    private static String[] joinRitualHard;
    private static String[] reputation;
    private static String[] size;
    private static String[] wealth;
    private static String[] magicalInclination;
    private static String[] militaryInclination;
    private static String[] intensity;

    private static String[] defaultSkinColor;
    private static String[] defaultUndertones;
    private static String[] defaultHairColor;
    private static String[] defaultHairstyles;
    private static String[] defaultEyeColor;
    private static String[] adjectiveSizes;
    private static String[] classes;
    private static String[] success;
    private static Random random;

    private static List<Ancestry> ancestries = new ArrayList<>();
    private static List<Heritage> heritages = new ArrayList<>();

    static {
        random = new Random();
        int seed = random.nextInt();
        loadFactionData();
        loadPeopleData();
        random = new Random(Objects.hash(seed));
    }

    public static class Ancestry implements Serializable{
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
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public int getLh() {
            return lh;
        }
        public void setLh(int lh) {
            this.lh = lh;
        }
        public float getSizeAvg() {
            return sizeAvg;
        }
        public void setSizeAvg(float sizeAvg) {
            this.sizeAvg = sizeAvg;
        }
        public float getSizeDev() {
            return sizeDev;
        }
        public void setSizeDev(float sizeDev) {
            this.sizeDev = sizeDev;
        }
        public String[] getSkinColor() {
            return skinColor;
        }
        public void setSkinColor(String[] skinColor) {
            this.skinColor = skinColor;
        }
        public String[] getUndertones() {
            return undertones;
        }
        public void setUndertones(String[] undertones) {
            this.undertones = undertones;
        }
        public String[] getHairColor() {
            return hairColor;
        }
        public void setHairColor(String[] hairColor) {
            this.hairColor = hairColor;
        }
        public String[] getHairstyles() {
            return hairstyles;
        }
        public void setHairstyles(String[] hairstyles) {
            this.hairstyles = hairstyles;
        }
        public String[] getEyeColor() {
            return eyeColor;
        }
        public void setEyeColor(String[] eyeColor) {
            this.eyeColor = eyeColor;
        }
        public String[][] getOptionalSpecialTraits() {
            return optionalSpecialTraits;
        }
        public void setOptionalSpecialTraits(String[][] optionalSpecialTraits) {
            this.optionalSpecialTraits = optionalSpecialTraits;
        }
        public String[][] getCertainSpecialTraits() {
            return certainSpecialTraits;
        }
        public void setCertainSpecialTraits(String[][] certainSpecialTraits) {
            this.certainSpecialTraits = certainSpecialTraits;
        }
        public String[] getHeritage() {
            return heritage;
        }
        public void setHeritage(String[] heritage) {
            this.heritage = heritage;
        }
        public int getMaxAge() {
            return maxAge;
        }
        public void setMaxAge(int maxAge) {
            this.maxAge = maxAge;
        }
        public int getMatureAge() {
            return matureAge;
        }
        public void setMatureAge(int matureAge) {
            this.matureAge = matureAge;
        }
    }

    public static class Heritage implements Serializable{
        private String name;
        private int lh;
        private String[] skinColor;
        private String[] undertones;
        private String[] hairColor;
        private String[] hairstyles;
        private String[] eyeColor;
        private String[][] optionalSpecialTraits;
        private String[][] certainSpecialTraits;
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public int getLh() {
            return lh;
        }
        public void setLh(int lh) {
            this.lh = lh;
        }
        public String[] getSkinColor() {
            return skinColor;
        }
        public void setSkinColor(String[] skinColor) {
            this.skinColor = skinColor;
        }
        public String[] getUndertones() {
            return undertones;
        }
        public void setUndertones(String[] undertones) {
            this.undertones = undertones;
        }
        public String[] getHairColor() {
            return hairColor;
        }
        public void setHairColor(String[] hairColor) {
            this.hairColor = hairColor;
        }
        public String[] getHairstyles() {
            return hairstyles;
        }
        public void setHairstyles(String[] hairstyles) {
            this.hairstyles = hairstyles;
        }
        public String[] getEyeColor() {
            return eyeColor;
        }
        public void setEyeColor(String[] eyeColor) {
            this.eyeColor = eyeColor;
        }
        public String[][] getOptionalSpecialTraits() {
            return optionalSpecialTraits;
        }
        public void setOptionalSpecialTraits(String[][] optionalSpecialTraits) {
            this.optionalSpecialTraits = optionalSpecialTraits;
        }
        public String[][] getCertainSpecialTraits() {
            return certainSpecialTraits;
        }
        public void setCertainSpecialTraits(String[][] certainSpecialTraits) {
            this.certainSpecialTraits = certainSpecialTraits;
        }
    }

    public static void loadFactionData() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String path = Paths.get("").toAbsolutePath().normalize().toString();
            //path = new File(path).getParent();

            attribute = deserializeJsonArray(objectMapper, new File(path, "JSONs/Faction/FactionAttribute.JSON"));
            mainName = deserializeJsonArray(objectMapper, new File(path, "JSONs/Faction/FactionName.JSON"));
            factionDomainsMilitary = deserializeJsonArray(objectMapper, new File(path, "JSONs/Faction/factionDomainsMilitary.JSON"));
            factionDomainsMundane = deserializeJsonArray(objectMapper, new File(path, "JSONs/Faction/factionDomainsMundane.JSON"));
            factionDomainsReligious = deserializeJsonArray(objectMapper, new File(path, "JSONs/Faction/factionDomainsReligious.JSON"));
            factionDomainsMagical = deserializeJsonArray(objectMapper, new File(path, "JSONs/Faction/factionDomainsMagical.JSON"));
            virtues = deserializeJsonArray(objectMapper, new File(path, "JSONs/Faction/Virtues.JSON"));
            factionGoals = deserializeJsonArray(objectMapper, new File(path, "JSONs/Faction/Goals.JSON"));
            superJobMappings = deserializeJsonMap(objectMapper, new File(path, "JSONs/Faction/InsaneFinances.JSON"));
            highJobMappings = deserializeJsonMap(objectMapper, new File(path, "JSONs/Faction/HighFinances.JSON"));
            midJobMappings = deserializeJsonMap(objectMapper, new File(path, "JSONs/Faction/MidFinances.JSON"));
            lowJobMappings = deserializeJsonMap(objectMapper, new File(path, "JSONs/Faction/LowFinances.JSON"));
            doctrines = deserializeJsonArray(objectMapper, new File(path, "JSONs/Faction/Doctrines.JSON"));
            factionEssence = deserializeJsonArray(objectMapper, new File(path, "JSONs/Faction/FactionEssence.JSON"));
            sourceOfPower = deserializeJsonArray(objectMapper, new File(path, "JSONs/Faction/SourceOfPower.JSON"));
            votingType = deserializeJsonArray(objectMapper, new File(path, "JSONs/Faction/VotingType.JSON"));
            oliType = deserializeJsonArray(objectMapper, new File(path, "JSONs/Faction/OliType.JSON"));
            oliDemoVotingResults = deserializeJsonArray(objectMapper, new File(path, "JSONs/Faction/OliDemoVotingResults.JSON"));
            autocracyType = deserializeJsonArray(objectMapper, new File(path, "JSONs/Faction/AutocracyType.JSON"));
            joinRitualSimple = deserializeJsonArray(objectMapper, new File(path, "JSONs/Faction/JoinRitualSimple.JSON"));
            joinRitualMedium = deserializeJsonArray(objectMapper, new File(path, "JSONs/Faction/JoinRitualMedium.JSON"));
            joinRitualHard = deserializeJsonArray(objectMapper, new File(path, "JSONs/Faction/JoinRitualHard.JSON"));
            reputation = deserializeJsonArray(objectMapper, new File(path, "JSONs/Faction/Reputation.JSON"));
            size = deserializeJsonArray(objectMapper, new File(path, "JSONs/Faction/Size.JSON"));
            wealth = deserializeJsonArray(objectMapper, new File(path, "JSONs/Faction/Wealth.JSON"));
            magicalInclination = deserializeJsonArray(objectMapper, new File(path, "JSONs/Faction/MagicalInclination.JSON"));
            militaryInclination = deserializeJsonArray(objectMapper, new File(path, "JSONs/Faction/MilitaryInclination.JSON"));
            intensity = deserializeJsonArray(objectMapper, new File(path, "JSONs/Faction/Intensity.JSON"));
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
            //path = new File(path).getParent();

            loadAncestries(objectMapper, new File(path, "JSONs/People/Ancestires.JSON"));
            loadHeritages(objectMapper, new File(path, "JSONs/People/Heriatage.JSON"));
            classes = deserializeJsonArray(objectMapper, new File(path, "JSONs/People/Classes.JSON"));
            success = deserializeJsonArray(objectMapper, new File(path, "JSONs/People/Success.JSON"));
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
                List<Map<String, JsonNode>> jsonObjects = objectMapper.readValue(filePath, new TypeReference<List<Map<String, JsonNode>>>() {});

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
                    ancestry.skinColor = resolveDefaults(deserializeJsonArray(objectMapper, ancestryJson.get("SkinColor")), defaultSkinColor);
                    ancestry.undertones = resolveDefaults(deserializeJsonArray(objectMapper, ancestryJson.get("Undertones")), defaultUndertones);
                    ancestry.hairColor = resolveDefaults(deserializeJsonArray(objectMapper, ancestryJson.get("HairColor")), defaultHairColor);
                    ancestry.hairstyles = resolveDefaults(deserializeJsonArray(objectMapper, ancestryJson.get("Hairstyles")), defaultHairstyles);
                    ancestry.eyeColor = resolveDefaults(deserializeJsonArray(objectMapper, ancestryJson.get("EyeColor")), defaultEyeColor);
                    ancestry.optionalSpecialTraits = deserializeSpecialTraits(objectMapper, ancestryJson.get("OptionalSpecialTraits"));
                    ancestry.certainSpecialTraits = deserializeSpecialTraits(objectMapper, ancestryJson.get("CertainSpecialTraits"));
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
                List<Map<String, JsonNode>> jsonObjects = objectMapper.readValue(filePath, new TypeReference<List<Map<String, JsonNode>>>() {});

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
                    heritage.skinColor = resolveDefaults(deserializeJsonArray(objectMapper, heritageJson.get("SkinColor")), defaultSkinColor);
                    heritage.undertones = resolveDefaults(deserializeJsonArray(objectMapper, heritageJson.get("Undertones")), defaultUndertones);
                    heritage.hairColor = resolveDefaults(deserializeJsonArray(objectMapper, heritageJson.get("HairColor")), defaultHairColor);
                    heritage.hairstyles = resolveDefaults(deserializeJsonArray(objectMapper, heritageJson.get("Hairstyles")), defaultHairstyles);
                    heritage.eyeColor = resolveDefaults(deserializeJsonArray(objectMapper, heritageJson.get("EyeColor")), defaultEyeColor);
                    heritage.optionalSpecialTraits = deserializeSpecialTraits(objectMapper, heritageJson.get("OptionalSpecialTraits"));
                    heritage.certainSpecialTraits = deserializeSpecialTraits(objectMapper, heritageJson.get("CertainSpecialTraits"));

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

    private static Map<String, List<String>> deserializeJsonMap(ObjectMapper objectMapper, File file) throws IOException {
        return objectMapper.readValue(file, new TypeReference<Map<String, List<String>>>() {});
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
                    specialTraits[j] = Arrays.stream(adjectiveSizes).map(adj -> specialTrait.replace("{AdjectiveSizes}", adj)).toArray(String[]::new);
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

    public static String[] getAttribute() {
        return attribute;
    }

    public static void setAttribute(String[] attribute) {
        DataManager.attribute = attribute;
    }

    public static String[] getMainName() {
        return mainName;
    }

    public static void setMainName(String[] mainName) {
        DataManager.mainName = mainName;
    }

    public static String[] getFactionDomainsMilitary() {
        return factionDomainsMilitary;
    }

    public static void setFactionDomainsMilitary(String[] factionDomainsMilitary) {
        DataManager.factionDomainsMilitary = factionDomainsMilitary;
    }

    public static String[] getFactionDomainsMundane() {
        return factionDomainsMundane;
    }

    public static void setFactionDomainsMundane(String[] factionDomainsMundane) {
        DataManager.factionDomainsMundane = factionDomainsMundane;
    }

    public static String[] getFactionDomainsMagical() {
        return factionDomainsMagical;
    }

    public static void setFactionDomainsMagical(String[] factionDomainsMagical) {
        DataManager.factionDomainsMagical = factionDomainsMagical;
    }

    public static String[] getFactionDomainsReligious() {
        return factionDomainsReligious;
    }

    public static void setFactionDomainsReligious(String[] factionDomainsReligious) {
        DataManager.factionDomainsReligious = factionDomainsReligious;
    }

    public static String[] getFactionGoals() {
        return factionGoals;
    }

    public static void setFactionGoals(String[] factionGoals) {
        DataManager.factionGoals = factionGoals;
    }

    public static String[] getVirtues() {
        return virtues;
    }

    public static void setVirtues(String[] virtues) {
        DataManager.virtues = virtues;
    }

    public static Map<String, List<String>> getSuperJobMappings() {
        return superJobMappings;
    }

    public static void setSuperJobMappings(Map<String, List<String>> superJobMappings) {
        DataManager.superJobMappings = superJobMappings;
    }

    public static Map<String, List<String>> getHighJobMappings() {
        return highJobMappings;
    }

    public static void setHighJobMappings(Map<String, List<String>> highJobMappings) {
        DataManager.highJobMappings = highJobMappings;
    }

    public static Map<String, List<String>> getMidJobMappings() {
        return midJobMappings;
    }

    public static void setMidJobMappings(Map<String, List<String>> midJobMappings) {
        DataManager.midJobMappings = midJobMappings;
    }

    public static Map<String, List<String>> getLowJobMappings() {
        return lowJobMappings;
    }

    public static void setLowJobMappings(Map<String, List<String>> lowJobMappings) {
        DataManager.lowJobMappings = lowJobMappings;
    }

    public static String[] getDoctrines() {
        return doctrines;
    }

    public static void setDoctrines(String[] doctrines) {
        DataManager.doctrines = doctrines;
    }

    public static String[] getFactionEssence() {
        return factionEssence;
    }

    public static void setFactionEssence(String[] factionEssence) {
        DataManager.factionEssence = factionEssence;
    }

    public static String[] getSourceOfPower() {
        return sourceOfPower;
    }

    public static void setSourceOfPower(String[] sourceOfPower) {
        DataManager.sourceOfPower = sourceOfPower;
    }

    public static String[] getVotingType() {
        return votingType;
    }

    public static void setVotingType(String[] votingType) {
        DataManager.votingType = votingType;
    }

    public static String[] getOliType() {
        return oliType;
    }

    public static void setOliType(String[] oliType) {
        DataManager.oliType = oliType;
    }

    public static String[] getOliDemoVotingResults() {
        return oliDemoVotingResults;
    }

    public static void setOliDemoVotingResults(String[] oliDemoVotingResults) {
        DataManager.oliDemoVotingResults = oliDemoVotingResults;
    }

    public static String[] getAutocracyType() {
        return autocracyType;
    }

    public static void setAutocracyType(String[] autocracyType) {
        DataManager.autocracyType = autocracyType;
    }

    public static String[] getJoinRitualSimple() {
        return joinRitualSimple;
    }

    public static void setJoinRitualSimple(String[] joinRitualSimple) {
        DataManager.joinRitualSimple = joinRitualSimple;
    }

    public static String[] getJoinRitualMedium() {
        return joinRitualMedium;
    }

    public static void setJoinRitualMedium(String[] joinRitualMedium) {
        DataManager.joinRitualMedium = joinRitualMedium;
    }

    public static String[] getJoinRitualHard() {
        return joinRitualHard;
    }

    public static void setJoinRitualHard(String[] joinRitualHard) {
        DataManager.joinRitualHard = joinRitualHard;
    }

    public static String[] getReputation() {
        return reputation;
    }

    public static void setReputation(String[] reputation) {
        DataManager.reputation = reputation;
    }

    public static String[] getSize() {
        return size;
    }

    public static void setSize(String[] size) {
        DataManager.size = size;
    }

    public static String[] getWealth() {
        return wealth;
    }

    public static void setWealth(String[] wealth) {
        DataManager.wealth = wealth;
    }

    public static String[] getMagicalInclination() {
        return magicalInclination;
    }

    public static void setMagicalInclination(String[] magicalInclination) {
        DataManager.magicalInclination = magicalInclination;
    }

    public static String[] getMilitaryInclination() {
        return militaryInclination;
    }

    public static void setMilitaryInclination(String[] militaryInclination) {
        DataManager.militaryInclination = militaryInclination;
    }

    public static String[] getIntensity() {
        return intensity;
    }

    public static void setIntensity(String[] intensity) {
        DataManager.intensity = intensity;
    }

    public static String[] getDefaultSkinColor() {
        return defaultSkinColor;
    }

    public static void setDefaultSkinColor(String[] defaultSkinColor) {
        DataManager.defaultSkinColor = defaultSkinColor;
    }

    public static String[] getDefaultUndertones() {
        return defaultUndertones;
    }

    public static void setDefaultUndertones(String[] defaultUndertones) {
        DataManager.defaultUndertones = defaultUndertones;
    }

    public static String[] getDefaultHairColor() {
        return defaultHairColor;
    }

    public static void setDefaultHairColor(String[] defaultHairColor) {
        DataManager.defaultHairColor = defaultHairColor;
    }

    public static String[] getDefaultHairstyles() {
        return defaultHairstyles;
    }

    public static void setDefaultHairstyles(String[] defaultHairstyles) {
        DataManager.defaultHairstyles = defaultHairstyles;
    }

    public static String[] getDefaultEyeColor() {
        return defaultEyeColor;
    }

    public static void setDefaultEyeColor(String[] defaultEyeColor) {
        DataManager.defaultEyeColor = defaultEyeColor;
    }

    public static String[] getAdjectiveSizes() {
        return adjectiveSizes;
    }

    public static void setAdjectiveSizes(String[] adjectiveSizes) {
        DataManager.adjectiveSizes = adjectiveSizes;
    }

    public static String[] getClasses() {
        return classes;
    }

    public static void setClasses(String[] classes) {
        DataManager.classes = classes;
    }

    public static String[] getSuccess() {
        return success;
    }

    public static void setSuccess(String[] success) {
        DataManager.success = success;
    }

    public static Random getRandom() {
        return random;
    }

    public static void setRandom(Random random) {
        DataManager.random = random;
    }

    public static List<Ancestry> getAncestries() {
        return ancestries;
    }

    public static void setAncestries(List<Ancestry> ancestries) {
        DataManager.ancestries = ancestries;
    }

    public static List<Heritage> getHeritages() {
        return heritages;
    }

    public static void setHeritages(List<Heritage> heritages) {
        DataManager.heritages = heritages;
    }
}
