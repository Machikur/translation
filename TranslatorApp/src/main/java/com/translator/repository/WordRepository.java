package com.translator.repository;

import com.translator.domain.Word;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WordRepository extends CrudRepository<Word, Long> {

    @Query("select w.polish from Word w WHERE w.english=:word")
    Optional<String> englishToPolish(@Param("word") String word);

    @Query("select w.english from Word w WHERE w.polish=:word")
    Optional<String> polishToEnglish(@Param("word") String word);

}
