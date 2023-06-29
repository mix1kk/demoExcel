package ru.excel.demo.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class TableController {
    @GetMapping()
    public String excelTable(){
        return "excelTable";
    }
}
