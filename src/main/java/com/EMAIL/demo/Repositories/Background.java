package com.EMAIL.demo.Repositories;

import com.EMAIL.demo.Models.Response;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface Background extends JpaRepository<Response, UUID> {
    @Query(value = "SELECT EXISTS (SELECT 1 FROM information_schema.tables WHERE table_schema = 'public' AND table_name = 'Registrations')", nativeQuery = true)
    boolean tableExists();
    @Query(value = "select * from Registrations",nativeQuery = true)
    List<Response> getResponses();
//    @Query(value = "select r.Amount from Registrations r where r.UserId LIKE %:UserId%")
//    Optional<Float> findAmount(String UserId);
}
