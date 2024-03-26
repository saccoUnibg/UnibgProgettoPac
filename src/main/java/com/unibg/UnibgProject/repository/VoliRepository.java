package com.unibg.UnibgProject.repository;

import com.unibg.UnibgProject.Entity.VoloEntity;
import com.unibg.UnibgProject.model.Volo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface VoliRepository extends CrudRepository<VoloEntity,Integer> {
    List<VoloEntity> findByPartenzaAndArrivoIgnoreCaseAndData(String partenza, String arrivo,String data);

}
