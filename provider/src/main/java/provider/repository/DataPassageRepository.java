package provider.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import provider.pojo.DataPassage;

@Repository
public interface DataPassageRepository extends JpaRepository<DataPassage, Long> {
}
