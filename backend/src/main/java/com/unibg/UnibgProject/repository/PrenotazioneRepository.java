package com.unibg.UnibgProject.repository;

import com.unibg.UnibgProject.entity.PrenotazioneEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrenotazioneRepository extends JpaRepository<PrenotazioneEntity,Integer> {

        public PrenotazioneEntity findByMailAndIdVolo(String idVolo,String mail);
        public List<PrenotazioneEntity> findByMail(String mail);

//        @Query("delete from PrenotazioneEntity p where p.")
//        void delete

}
