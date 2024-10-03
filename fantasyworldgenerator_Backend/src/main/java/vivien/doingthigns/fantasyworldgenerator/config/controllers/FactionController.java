package vivien.doingthigns.fantasyworldgenerator.config.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import vivien.doingthigns.fantasyworldgenerator.factiongenerator.Faction;
import vivien.doingthigns.fantasyworldgenerator.factiongenerator.FactionBuilder;
import vivien.doingthigns.fantasyworldgenerator.factiongenerator.FactionService;

import java.util.Optional;

@RestController
@RequestMapping("/api/faction")
@CrossOrigin(origins = "http://localhost:3000") // Adjust this if your React app runs on a different port
public class FactionController {

    private static final Logger logger = LoggerFactory.getLogger(FactionController.class);

    @Autowired
    private FactionService factionService;

    @Autowired
    private FactionBuilder factionBuilder;

    // Generate a new faction using request parameters
    @PostMapping("/generate")
    public ResponseEntity<String> generateFaction(
            @RequestParam String scale,
            @RequestParam String funds,
            @RequestParam String magic,
            @RequestParam String military,
            @RequestParam String religion,
            @RequestParam String reputation,
            @RequestParam String intensity,
            @RequestParam String primPercent,
            @RequestParam String secPercent,
            @RequestParam String primaryAncestry,
            @RequestParam String secondaryAncestry
    ) {
        try {
            // Prepare the input parts from the request parameters
            String[] inputParts = {
                    scale,
                    funds,
                    magic,
                    military,
                    religion,
                    reputation,
                    intensity,
                    primPercent,
                    secPercent,
                    primaryAncestry,
                    secondaryAncestry
            };

            // Generate the faction using FactionBuilder
            factionBuilder.generateFaction(inputParts);

            // Get faction details after generation
            String factionDetails = factionBuilder.getFactionDetails();

            // Return faction details in the response
            return ResponseEntity.ok(factionDetails);

        } catch (Exception e) {
            logger.error("Error generating faction", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error generating faction: " + e.getMessage());
        }
    }

    // Load a faction by ID from the database
    @GetMapping("/{id}")
    public ResponseEntity<Faction> getFactionById(@PathVariable int id) {
        try {
            Optional<Faction> faction = factionService.getFactionById(id);
            return faction.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        } catch (Exception e) {
            logger.error("Error retrieving faction with ID: " + id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    // Load a faction by name from the database
    @GetMapping("/name/{name}")
    public ResponseEntity<Faction> getFactionByName(@PathVariable String name) {
        try {
            Optional<Faction> faction = factionService.getFactionByName(name);
            return faction.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        } catch (Exception e) {
            logger.error("Error retrieving faction with name: " + name, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    // Save a faction to the database (if you want to persist generated factions)
    @PostMapping("/save")
    public ResponseEntity<Faction> saveFaction(@RequestBody Faction faction) {
        try {
            Faction savedFaction = factionService.saveFaction(faction);
            return ResponseEntity.ok(savedFaction);
        } catch (Exception e) {
            logger.error("Error saving faction", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }
}
