package com.unibg.UnibgProject.repository;

import com.unibg.UnibgProject.Entity.PrenotazioneEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrenotazioneRepository extends JpaRepository<PrenotazioneEntity,Integer> {
        public List<PrenotazioneEntity> findByMail(String mail);
}
