package com.example.enaaskillsplatform.repository;

import com.example.enaaskillsplatform.entity.SousCompetence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SousCompetenceRepository extends JpaRepository<SousCompetence, Long> {

}
