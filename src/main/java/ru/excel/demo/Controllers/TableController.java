package ru.excel.demo.Controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.excel.demo.Models.Line;
import ru.excel.demo.Services.LineService;

import java.util.List;

@Controller
@RequestMapping("/table")
public class TableController {
    //внедрение зависимости через конструктор
    private final LineService lineService;
@Autowired
    public TableController(LineService lineService) {
        this.lineService = lineService;
    }
//Get запрос для отображения таблицы
    @GetMapping()
    public String excelTable(Model model){
    model.addAttribute("AllLines",lineService.findAll());
        return "excelTable";
    }
    @PatchMapping("/{id}")
    public String editTable(@ModelAttribute("line") @Valid Line newLine, BindingResult bindingResult, @PathVariable Integer id){
        if(bindingResult.hasErrors())
            return "redirect:/table";

        newLine.setId(id);
        //заполняем все поля, которые не передались через ModelAttribute
        Line oldLine = lineService.findOne(id);
        if (newLine.getA()==null){
            newLine.setA(oldLine.getA());
            newLine.setHidden_a(oldLine.getHidden_a());
        }
          else
            newLine.setHidden_a(newLine.getA());

        if (newLine.getB()==null){
            newLine.setB(oldLine.getB());
            newLine.setHidden_b(oldLine.getHidden_b());
        }
        else newLine.setHidden_b(newLine.getB());
        if (newLine.getC()==null) {
            newLine.setC(oldLine.getC());
            newLine.setHidden_c(oldLine.getHidden_c());
        }
        else newLine.setHidden_c(newLine.getC());
        if (newLine.getD()==null) {
            newLine.setD(oldLine.getD());
            newLine.setHidden_d(oldLine.getHidden_d());
        }
        else newLine.setHidden_d(newLine.getD());
        lineService.update(id,newLine);
    return "redirect:/table";
        }

}
//todo: проверка на нуль при делении, проверка на ссылку на саму себя,проверка на циклические ссылки, тип числа, непарное число скобок
