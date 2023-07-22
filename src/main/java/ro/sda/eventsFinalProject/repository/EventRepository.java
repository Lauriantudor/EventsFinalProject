package ro.sda.eventsFinalProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.sda.eventsFinalProject.model.Event;

@Repository
public interface EventRepository extends JpaRepository<Event,Integer> {
}
