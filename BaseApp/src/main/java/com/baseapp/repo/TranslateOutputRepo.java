package com.baseapp.repo;

import com.baseapp.domain.TranslateOutput;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface TranslateOutputRepo extends CrudRepository<TranslateOutput, Long> {

    @Modifying
    @Query("UPDATE TranslateOutput T " +
            "SET T.isSavedInFile=true " +
            "WHERE T.id LIKE :ID")
    void setSavedById(@Param("ID") Long id);
}
