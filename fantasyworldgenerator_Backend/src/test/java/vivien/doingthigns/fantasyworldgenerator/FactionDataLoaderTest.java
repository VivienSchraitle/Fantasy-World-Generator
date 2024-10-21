package vivien.doingthigns.fantasyworldgenerator;

import com.fasterxml.jackson.databind.ObjectMapper;

import vivien.doingthigns.fantasyworldgenerator.data.DataManager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.sun.net.httpserver.Authenticator;

@SpringBootTest
public class FactionDataLoaderTest {

    private ObjectMapper objectMapper;
    private String path;

    @BeforeEach
    public void setUp() {
        objectMapper = mock(ObjectMapper.class);
        path = Paths.get("").toAbsolutePath().normalize().toString();
    }

    @Test
    public void testLoadFactionData() {
        // Assuming loadFactionData() is a static method in your DataManager class
        try {
            DataManager.loadFactionData();

            // Now, verify if the JSON files are properly deserialized and values are not null
            assertNotNull(DataManager.getAttribute());
            assertNotNull(DataManager.getMainName());
            assertNotNull(DataManager.getFactionDomainsMilitary());
            assertNotNull(DataManager.getFactionDomainsMundane());
            assertNotNull(DataManager.getFactionDomainsReligious());
            assertNotNull(DataManager.getFactionDomainsMagical());
            assertNotNull(DataManager.getVirtues());
            assertNotNull(DataManager.getFactionGoals());
            assertNotNull(DataManager.getSuperJobMappings());
            assertNotNull(DataManager.getHighJobMappings());
            assertNotNull(DataManager.getMidJobMappings());
            assertNotNull(DataManager.getLowJobMappings());
            assertNotNull(DataManager.getDoctrines());
            assertNotNull(DataManager.getFactionEssence());
            assertNotNull(DataManager.getSourceOfPower());
            assertNotNull(DataManager.getVotingType());
            assertNotNull(DataManager.getOliType());
            assertNotNull(DataManager.getOliDemoVotingResults());
            assertNotNull(DataManager.getAutocracyType());
            assertNotNull(DataManager.getJoinRitualSimple());
            assertNotNull(DataManager.getJoinRitualMedium());
            assertNotNull(DataManager.getJoinRitualHard());
            assertNotNull(DataManager.getReputation());
            assertNotNull(DataManager.getSize());
            assertNotNull(DataManager.getWealth());
            assertNotNull(DataManager.getMagicalInclination());
            assertNotNull(DataManager.getMilitaryInclination());
            assertNotNull(DataManager.getIntensity());
            assertNotNull(DataManager.getWeather());
            assertNotNull(DataManager.getTemperature());
            assertNotNull(DataManager.getWind());
            assertNotNull(DataManager.getSmellMap());
            System.out.println("All faction data loaded successfully!");
        } catch (Exception e) {
            fail("Exception occurred: " + e.getMessage());
        }
    }
}