package com.lanit.dcs.diss.aacs.satonin18.hackathon.web.repository;

import com.lanit.dcs.diss.aacs.satonin18.hackathon.web.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository//("carRepository")
public interface CarRepository
        extends JpaRepository<Car, Long>
{
//    long count(); //from CrudRepository

    @Query("SELECT COUNT(DISTINCT c.vendor) FROM Car c")
    Long countDistinctVendor();
}