package ru.excel.demo.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "line")
public class Line  implements Comparable <Line>{
    static final String regex = "\\-?(\\d*\\.)?\\d+|(=\\(*\\-?[ABCD][1-4]|=\\(*\\-?(\\d*\\.)?\\d+)([\\+\\-\\*\\/]\\(*[ABCD][1-4]\\)*|[\\+\\-\\*\\/]\\(*(\\d*\\.)?\\d+\\)*)*";
    @Id
    @Column(name = "id")
    private int id;
    @Pattern(regexp = regex, message = "Expression Error")
    @Column(name = "a")
    private String a;

    //Пришлось отступить от принятой конвенции CamelCase т.к. Spring JPA по умолчанию использует другой формат поиска в базе
    @Column(name = "hidden_a")
    private String hidden_a;
    @Pattern(regexp = regex, message = "Expression Error")
    @Column(name = "b")
    private String b;
    @Column(name = "hidden_b")
    private String hidden_b;
    @Pattern(regexp = regex, message = "Expression Error")
    @Column(name = "c")
    private String c;
    @Column(name = "hidden_c")
    private String hidden_c;
    @Pattern(regexp = regex, message = "Expression Error")
    @Column(name = "d")
    private String d;
    @Column(name = "hidden_d")
    private String hidden_d;

    public Line(int id, String a, String hidden_a, String b, String hidden_b, String c, String hidden_c, String d, String hidden_d) {
        this.id = id;
        this.a = a;
        this.hidden_a = hidden_a;
        this.b = b;
        this.hidden_b = hidden_b;
        this.c = c;
        this.hidden_c = hidden_c;
        this.d = d;
        this.hidden_d = hidden_d;
    }

    public Line() {

    }


    public int getId() {
        return id;
    }

    public String getHidden_a() {
        return hidden_a;
    }

    public void setHidden_a(String hidden_a) {
        this.hidden_a = hidden_a;
    }

    public String getHidden_b() {
        return hidden_b;
    }

    public void setHidden_b(String hidden_b) {
        this.hidden_b = hidden_b;
    }

    public String getHidden_c() {
        return hidden_c;
    }

    public void setHidden_c(String hidden_c) {
        this.hidden_c = hidden_c;
    }

    public String getHidden_d() {
        return hidden_d;
    }

    public void setHidden_d(String hidden_d) {
        this.hidden_d = hidden_d;
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

    @Override
    public int compareTo(Line o) {
        if (id == o.id)
        return 0;
        else if (id<o.id)
            return -1;
        else return 1;
    }
}
