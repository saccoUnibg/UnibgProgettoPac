package com.unibg.UnibgProject.repository;

import com.unibg.UnibgProject.entity.VoloEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
@Repository
public interface VoliRepository extends CrudRepository<VoloEntity,Integer> {

    // Metodo senza scali
    List<VoloEntity> findByPartenzaAndArrivoIgnoreCaseAndData(String partenza, String arrivo,String data);

    // Metodi con scali
    List<VoloEntity> findByPartenzaIgnoreCaseAndData(String partenza, String data);

    List<VoloEntity> findByArrivoIgnoreCaseAndData(String arrivo, String data);
    List<VoloEntity> findByIdIn(Collection<Long> idList);
    VoloEntity findById(Long id);
}
