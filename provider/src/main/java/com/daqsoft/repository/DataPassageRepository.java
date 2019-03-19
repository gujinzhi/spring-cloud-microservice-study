package com.daqsoft.repository;

import com.daqsoft.pojo.DataPassage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataPassageRepository extends JpaRepository<DataPassage, Long> {
}
