package org.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.test.model.TodoEntity;

@Repository
public interface TodoRepository extends JpaRepository<TodoEntity, Long> {


}
