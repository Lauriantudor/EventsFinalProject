package ro.sda.eventsFinalProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.sda.eventsFinalProject.model.Category;
@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer> {
}
