package com.daqsoft.repository;

import com.daqsoft.pojo.DataData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataDataRepository extends JpaRepository<DataData, Long> {



}
