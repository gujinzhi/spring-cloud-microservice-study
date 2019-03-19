package com.daqsoft.repository;

import com.daqsoft.pojo.DataLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataLogRepository extends JpaRepository<DataLog, Long> {


}
