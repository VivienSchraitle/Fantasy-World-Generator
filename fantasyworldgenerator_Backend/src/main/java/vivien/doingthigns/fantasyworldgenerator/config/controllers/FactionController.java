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
    public ResponseEntity<Faction> generateFaction(@RequestBody FactionRequest request) {
        try {
            // Prepare the input parts from the request object
            String[] inputParts = {
                    request.getScale(),
                    request.getFunds(),
                    request.getMagic(),
                    request.getMilitary(),
                    request.getReligion(),
                    request.getReputation(),
                    request.getIntensity(),
                    request.getPrimPercent(),
                    request.getSecPercent(),
                    request.getPrimaryHeritage(),
                    request.getSecondaryHeritage()
            };

            // Generate the faction using FactionBuilder
            factionBuilder.generateFaction(inputParts);

            Faction faction = factionBuilder.getMyFaction();
            // Return faction details in the response
            return ResponseEntity.ok(faction);

        } catch (Exception e) {
            logger.error("Error generating faction", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
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

    @PostMapping("/generateField")
    public ResponseEntity<?> generateField(@RequestBody FieldUpdateRequest request) {
        try {
            // Call the FactionService to update the specific field based on the request
            // getFieldName
            System.out.println("request");
            Faction faction = factionService.getFactionById(request.getFactionId()).orElse(null);
            if (faction != null) {
                factionService.generateFieldForFaction(faction, request.getFieldName());
                return ResponseEntity.ok(faction);
            } else {
                // Handle the case where the Faction is not found
                throw new IllegalArgumentException("The faction doesn't exist");
            }

            // Faction updatedFaction =
            // factionService.generateFieldForFaction(request.getFieldName());

            //return ResponseEntity.internalServerError().body("unexplained error");

        } catch (IllegalArgumentException e) {
            logger.error("Error generating field for faction", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
