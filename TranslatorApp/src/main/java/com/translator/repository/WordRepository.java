package com.translator.repository;

import com.translator.domain.Language;
import com.translator.domain.Word;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WordRepository extends CrudRepository<Word, Long> {

    Optional<Word> findByLanguageAndWord(Language language, String word);

    Optional<Word> findWordByMeaningIdAndLanguage(Long meaningId, Language language);

}
