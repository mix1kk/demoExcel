package ru.excel.demo.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.excel.demo.Models.Line;


@Repository
public interface LineRepository extends JpaRepository<Line, Integer> {

}
