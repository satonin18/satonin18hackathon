package ipr.web.repository;

import ipr.web.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository
        extends JpaRepository<Car, Long>
{
    //ATATION !!! NATIVE QUERY (USE TALES "cars" and FIELD "vendor")
    @Query(nativeQuery = true, value = "SELECT COUNT(DISTINCT LOWER(c.vendor)) FROM cars c")
    Long countDistinctVendorIgnorCase();
}