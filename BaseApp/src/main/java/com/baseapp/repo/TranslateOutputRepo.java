package com.baseapp.repo;

import com.baseapp.domain.TranslateOutput;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TranslateOutputRepo extends CrudRepository<TranslateOutput, Long> {
}
