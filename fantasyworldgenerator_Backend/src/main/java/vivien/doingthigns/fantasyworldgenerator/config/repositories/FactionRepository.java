package vivien.doingthigns.fantasyworldgenerator.config.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vivien.doingthigns.fantasyworldgenerator.factiongenerator.Faction;

public interface FactionRepository extends JpaRepository<Faction, Integer> {
    // You can add custom query methods if needed, like finding by faction name
    Faction findByFacName(String facName);
}