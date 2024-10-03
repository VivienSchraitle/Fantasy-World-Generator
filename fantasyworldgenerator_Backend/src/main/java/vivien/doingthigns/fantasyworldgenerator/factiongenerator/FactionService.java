package vivien.doingthigns.fantasyworldgenerator.factiongenerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vivien.doingthigns.fantasyworldgenerator.config.repositories.FactionRepository;

import java.util.Optional;

@Service
public class FactionService {

    @Autowired
    private FactionRepository factionRepository;

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
}

