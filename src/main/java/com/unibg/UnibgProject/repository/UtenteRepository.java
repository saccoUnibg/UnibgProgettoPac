package com.unibg.UnibgProject.repository;

import com.unibg.UnibgProject.Entity.UtenteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtenteRepository extends JpaRepository<UtenteEntity, Integer> {

//    @Query(
//            value = "SELECT id FROM Utenti u WHERE u.mail = mail",
//            nativeQuery = true)
//    Double findUtenteByMail(String mail);
public UtenteEntity findByMail(String mail);
}
