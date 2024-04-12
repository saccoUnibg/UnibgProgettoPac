package com.unibg.UnibgProject.repository;

import com.unibg.UnibgProject.Entity.CheckinEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@EnableJpaRepositories
@Repository
public interface CheckinRepository extends JpaRepository<CheckinEntity,Integer> {

    @Query(value="delete from check_in c where c.idPrenotazione=idPrenotazione")
    public Long deleteByIdPrenotazione(String idPrenotazione);

}
