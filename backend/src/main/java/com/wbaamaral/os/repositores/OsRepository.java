package com.wbaamaral.os.repositores;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wbaamaral.os.domain.Os;

@Repository
public interface OsRepository extends JpaRepository<Os, Long>{

}
