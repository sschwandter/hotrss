package org.shockfrosted.hotrss.repository;

import org.shockfrosted.hotrss.model.Analysis;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AnalysisRepository extends CrudRepository<Analysis, Long> {

    Optional<Analysis> findById(long id);

}