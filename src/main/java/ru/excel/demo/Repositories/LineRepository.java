package ru.excel.demo.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.excel.demo.Models.Line;

import java.util.List;

@Repository
public interface LineRepository extends JpaRepository<Line,Integer> {
    //дополнительный метод поиска в таблице с сортировкой по id
    List<Line> findByOrderByIdAsc();
}
