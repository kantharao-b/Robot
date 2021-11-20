package com.robot.survivor;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SurvivorDataRepository extends JpaRepository<Survivor, Long> {

	List<Survivor> findByName(String name);
}
