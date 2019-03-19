package com.daqsoft.repository;

import com.daqsoft.pojo.DataLogin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataLoginRepository extends JpaRepository<DataLogin, Long> {



}
