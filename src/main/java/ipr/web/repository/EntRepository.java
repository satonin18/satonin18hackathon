package ipr.web.repository;

import ipr.web.entity.Car;
import ipr.web.entity.Ent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EntRepository
        extends JpaRepository<Ent, Long>
{
}