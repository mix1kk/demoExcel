package ru.excel.demo.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.excel.demo.Models.Line;
import ru.excel.demo.Repositories.LineRepository;
import ru.excel.demo.Utils.Calculator;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


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

//    public void save (Line line){
//        lineRepository.save(line);
//    }
    public void update(Integer id, Line updatedLine){
        List<Line> listLines = new ArrayList<>(lineRepository.findAll());
        updatedLine.setId(id);
        //проверка на обычное число
        Pattern digitsPattern = Pattern.compile(("\\-?(\\d*\\.)?\\d+"));
        String expression = updatedLine.getA();
        Matcher matcher = digitsPattern.matcher(expression);
       if(matcher.matches())
           updatedLine.setHidden_a(expression);
        Calculator.tokenizeAndMakePolishRevert(expression.replace("=",""),listLines);
//       else {
//           String [] mas = expression.split("[\\(\\)\\+\\-\\*\\/]");
//
//           for (int i=0; i<mas.length;i++)
//           System.out.println(mas[i]);
//       }

        lineRepository.save(updatedLine);
    }
//    public void delete (Integer id){
//        lineRepository.deleteById(id);
//    }

}


