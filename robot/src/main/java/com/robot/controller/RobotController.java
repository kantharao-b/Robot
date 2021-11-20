package com.robot.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.robot.survivor.Survivor;
import com.robot.survivor.SurvivorDataRepository;

@RestController
@RequestMapping("/api/")
public class RobotController {
	@Autowired
    private SurvivorDataRepository survivorDataRepository;

    @GetMapping("/survivors")
    public List < Survivor > getAllEmployees() {
        return survivorDataRepository.findAll();
    }
    
    @PostMapping("/addsurvivor")
    public ResponseEntity<Survivor> addSurvivor(@RequestBody Survivor survivor) {
        return new ResponseEntity<>(survivorDataRepository.save(survivor),HttpStatus.CREATED);
    }
    
    @PostMapping("/addsurvivors")
    public List<Survivor> addSurvivors(@RequestBody List<Survivor> survivors) {
        return survivorDataRepository.saveAll(survivors);
    }
    
    @PutMapping("/updatelocation/{id}")
	public ResponseEntity<Survivor> updateTutorial(@PathVariable("id") long id, @RequestBody Survivor survivor) {
		Optional<Survivor> survivorData = survivorDataRepository.findById(id);

		if (survivorData.isPresent()) {
			Survivor survivorFinal = survivorData.get();
			survivorFinal.setLatitude(survivor.getLatitude());
			survivorFinal.setLongitude(survivor.getLongitude());
			return new ResponseEntity<>(survivorDataRepository.save(survivorFinal), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
