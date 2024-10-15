package vivien.doingthigns.fantasyworldgenerator.config.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import vivien.doingthigns.fantasyworldgenerator.factiongenerator.Faction;
import vivien.doingthigns.fantasyworldgenerator.people.Person;

public interface PersonRepository extends JpaRepository<Person, Integer> {
    Faction findByName(String name);
}