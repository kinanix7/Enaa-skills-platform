package com.example.enaaskillsplatform.repository;

import com.example.enaaskillsplatform.entity.Competence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompetenceRepository extends JpaRepository<Competence, Long> {

}
