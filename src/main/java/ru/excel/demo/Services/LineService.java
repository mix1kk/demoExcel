package ru.excel.demo.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.excel.demo.Models.Line;
import ru.excel.demo.Repositories.LineRepository;

import java.util.List;

@Service
@Transactional
public class LineService {
    //внедрение зависимости через конструктор
    private final LineRepository lineRepository;
    @Autowired
    public LineService(LineRepository lineRepository) {
        this.lineRepository = lineRepository;
    }
    //стандартные CRUD методы для базы данных

    public List<Line> findAll(){
        return lineRepository.findByOrderByIdAsc();
    }
    public Line findOne(Integer id){
        return lineRepository.findById(id).orElse(null);
    }
    public void save (Line line){
        lineRepository.save(line);
    }
    public void update(Integer id, Line updatedLine){
        updatedLine.setId(id);
        lineRepository.save(updatedLine);
    }
    public void delete (Integer id){
        lineRepository.deleteById(id);
    }
}


