package ru.excel.demo.Controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.excel.demo.Models.Line;
import ru.excel.demo.Services.LineService;

@Controller
@RequestMapping("/table")
public class TableController {
    //внедрение зависимости через конструктор
    private final LineService lineService;
@Autowired
    public TableController(LineService lineService) {
        this.lineService = lineService;
    }
//гет запрос для отображения таблицы
    @GetMapping()
    public String excelTable(Model model){
    model.addAttribute("AllLines",lineService.findAll());
        return "excelTable";
    }
    @PatchMapping("/{id}")
    public String editTable(@ModelAttribute("line") @Valid Line line, BindingResult bindingResult, @PathVariable Integer id){
        line.setId(id);
        //заполняем все поля, которые не передались через ModelAttribute
        Line newLine = lineService.findOne(id);
        if (line.getA()==null)
            line.setA(newLine.getA());
        if (line.getB()==null)
            line.setB(newLine.getB());
        if (line.getC()==null)
            line.setC(newLine.getC());
        if (line.getD()==null)
            line.setD(newLine.getD());
        line.setHidden_a(newLine.getHidden_a());
        line.setHidden_b(newLine.getHidden_b());
        line.setHidden_c(newLine.getHidden_c());
        line.setHidden_d(newLine.getHidden_d());
        if(bindingResult.hasErrors()) {
            return "redirect:/table";
        }
        lineService.update(id,line);
    return "redirect:/table";
        }

}
