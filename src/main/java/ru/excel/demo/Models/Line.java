package ru.excel.demo.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "line")
public class Line {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Pattern(regexp ="\\-?\\d+|(=\\-?[ABCD][1-4]|=\\-?\\d+)([\\+\\-\\*\\/][ABCD][1-4]|[\\+\\-\\*\\/]\\d+)*", message = "Expression Error")
    @Column(name = "a")
    private String a;

    //Пришлось отступить от принятой конвенции CamelCase т.к. Spring JPA по умолчанию использует другой формат поиска в базе
    @Column(name = "hidden_a")
    private String hidden_a;
    @Pattern(regexp ="\\-?\\d+|(=\\-?[ABCD][1-4]|=\\-?\\d+)([\\+\\-\\*\\/][ABCD][1-4]|[\\+\\-\\*\\/]\\d+)*", message = "Expression Error")
    @Column(name = "b")
    private String b;
    @Column(name = "hidden_b")
    private String hidden_b;
    @Pattern(regexp ="\\-?\\d+|(=\\-?[ABCD][1-4]|=\\-?\\d+)([\\+\\-\\*\\/][ABCD][1-4]|[\\+\\-\\*\\/]\\d+)*", message = "Expression Error")
    @Column(name = "c")
    private String c;
    @Column(name = "hidden_c")
    private String hidden_c;
    @Pattern(regexp ="\\-?\\d+|(=\\-?[ABCD][1-4]|=\\-?\\d+)([\\+\\-\\*\\/][ABCD][1-4]|[\\+\\-\\*\\/]\\d+)*", message = "Expression Error")
    @Column(name = "d")
    private String d;
    @Column(name = "hidden_d")
    private String hidden_d;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public String getD() {
        return d;
    }

    public void setD(String d) {
        this.d = d;
    }
}
