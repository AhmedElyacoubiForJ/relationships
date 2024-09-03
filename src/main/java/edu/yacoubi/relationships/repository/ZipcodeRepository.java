package edu.yacoubi.relationships.repository;

import edu.yacoubi.relationships.model.entity.Zipcode;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZipcodeRepository extends CrudRepository<Zipcode, Long> {
}
