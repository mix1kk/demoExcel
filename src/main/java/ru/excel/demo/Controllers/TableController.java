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

    //Get запрос для отображения таблицы
    @GetMapping()
    public String excelTable(Model model) {
        model.addAttribute("AllLines", lineService.findAll());
        return "excelTable";
    }

    @PatchMapping("/{id}")
    public String editTable(@ModelAttribute("line") @Valid Line newLine, BindingResult bindingResult, @PathVariable Integer id) throws Exception {
        if (bindingResult.hasErrors())
            return "redirect:/table";

        newLine.setId(id);
        //заполняем все поля, которые не передались через ModelAttribute
        Line oldLine = lineService.findOne(id);
        if (newLine.getFieldA() == null) {
            newLine.setFieldA(oldLine.getFieldA());
            newLine.setHiddenFieldA(oldLine.getHiddenFieldA());
        } else
            newLine.setHiddenFieldA(newLine.getFieldA());

        if (newLine.getFieldB() == null) {
            newLine.setFieldB(oldLine.getFieldB());
            newLine.setHiddenFieldB(oldLine.getHiddenFieldB());
        } else newLine.setHiddenFieldB(newLine.getFieldB());
        if (newLine.getFieldC() == null) {
            newLine.setFieldC(oldLine.getFieldC());
            newLine.setHiddenFieldC(oldLine.getHiddenFieldC());
        } else newLine.setHiddenFieldC(newLine.getFieldC());
        if (newLine.getFieldD() == null) {
            newLine.setFieldD(oldLine.getFieldD());
            newLine.setHiddenFieldD(oldLine.getHiddenFieldD());
        } else newLine.setHiddenFieldD(newLine.getFieldD());
        lineService.update(id, newLine);
        return "redirect:/table";
    }

}
//todo: проверка на нуль при делении, проверка на ссылку на саму себя,проверка на циклические ссылки,
