package com.unibg.UnibgProject.repository;

import com.unibg.UnibgProject.Entity.CheckinEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CheckinRepository extends JpaRepository<CheckinEntity,Integer> {

    @Query("delete from CheckinEntity c where c.id_prenotazione=idPrenotazione")
    void deleteByIdPrenotazione(String idPrenotazione);

}
