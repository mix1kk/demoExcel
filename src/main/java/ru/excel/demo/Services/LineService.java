package ru.excel.demo.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.excel.demo.Models.Line;
import ru.excel.demo.Repositories.LineRepository;
import ru.excel.demo.Utils.Calculator;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


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

//    public void saveAll(List<Line> lines) {
//        lineRepository.saveAll(lines);
//    }
    public List<Line> findAll(){
        if(lineRepository.findAll().size()<4) {
            for (int i = 0; i < 4; i++) {
                Line line = new Line(i+1, "1", "1", "2", "2", "3", "3", "4", "4");
                lineRepository.save(line);
            }
        }
        return lineRepository.findAll().stream().sorted().collect(Collectors.toList());
    }
    public Line findOne(Integer id){
        return lineRepository.findById(id).orElse(null);
    }


    public void update(Integer id, Line updatedLine) throws Exception {
        List<Line> listLines = new ArrayList<>(lineRepository.findAll().stream().sorted().collect(Collectors.toList()));
        updatedLine.setId(id);
        Calculator.recalculateLine(updatedLine,listLines);
        lineRepository.save(updatedLine);
 //       listLines.set(id,updatedLine);
        for(Line line : listLines){
           // if(id!=line.getId())
            Calculator.recalculateLine(line,listLines);
        }
        lineRepository.saveAll(listLines);
    }

        public void save (Line line) {
            lineRepository.save(line);
        }
//    public void delete (Integer id){
//        lineRepository.deleteById(id);
//    }

}


