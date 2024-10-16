package vivien.doingthigns.fantasyworldgenerator.config.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vivien.doingthigns.fantasyworldgenerator.data.DataManager;
import vivien.doingthigns.fantasyworldgenerator.data.Heritage;

import java.util.List;

@RestController
@RequestMapping("/api/heritages")
@CrossOrigin(origins = "http://localhost:3000")
public class HeritageController {

    @GetMapping
    public List<Heritage> getHeritages() {
        return DataManager.getHeritages();
    }
}