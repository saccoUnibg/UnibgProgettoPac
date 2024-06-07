package com.unibg.UnibgProject.repository;

import com.unibg.UnibgProject.entity.CheckinEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@EnableJpaRepositories
@Repository
public interface CheckinRepository extends JpaRepository<CheckinEntity,Integer> {
    @Transactional
    public void deleteByIdPrenotazione(String idPrenotazione);


}
