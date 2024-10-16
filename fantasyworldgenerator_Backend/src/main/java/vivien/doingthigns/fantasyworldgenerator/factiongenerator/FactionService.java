package vivien.doingthigns.fantasyworldgenerator.factiongenerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import vivien.doingthigns.fantasyworldgenerator.config.repositories.FactionRepository;

import java.util.Optional;


@Service
public class FactionService {

    @Autowired
    private FactionRepository factionRepository;
    
    @Autowired
    private FactionGenerator factionGenerator;

    // Get a faction by ID
    public Optional<Faction> getFactionById(int id) {
        return factionRepository.findById(id);
    }

    // Get a faction by name
    public Optional<Faction> getFactionByName(String name) {
        return Optional.ofNullable(factionRepository.findByFacName(name));
    }

    // Save a new faction
    public Faction saveFaction(Faction faction) {
        return factionRepository.save(faction);
    }
    
    public Faction generateFieldForFaction(Faction faction, String fieldName) {

        factionGenerator.setFactionEntity(faction);
        // Update or generate the requested field
        switch (fieldName) {
            case "facName":
                faction.setFacName(factionGenerator.generateName());
                break;
                case "leadership":
                var leadership = factionGenerator.generateLeadership();
                faction.setLeadership(leadership.getKey());
                faction.setAssignedLeaders(leadership.getValue());
            case "powerType", "votingSystem":
                var powerParameters = factionGenerator.generatePowerParameters();
                faction.setVotingSystem(powerParameters.get("VotingSystem"));
                faction.setPowerType(powerParameters.get("PowerType"));
            case "goalsArray":
                faction.setGoalsArray(factionGenerator.generateGoals());
                break;
            case "domainsArray":
                faction.setDomainsArray(factionGenerator.generateDomains());
                break;
            
            case "joinRitual":
                faction.setJoinRitual(factionGenerator.generateJoinRitual());
                break;
            case "factionValues":
                faction.setFactionValues(factionGenerator.generateValues());
                break;
            case "moneySources":
                faction.setMoneySources(factionGenerator.generateMoneySources());
                break;
            case "doctrines":
                faction.setDoctrines(factionGenerator.generateDoctrines());
                break;
            default:
                throw new IllegalArgumentException("Field not recognized");
        }
        factionRepository.save(faction); 
        return faction;
    }

    
}

