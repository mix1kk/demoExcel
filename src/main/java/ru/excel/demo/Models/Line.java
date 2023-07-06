package ru.excel.demo.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@Entity
@Table(name = "line")
public class Line implements Comparable<Line> {
    static final String REGEX = "\\-?(\\d*\\.)?\\d+|(=\\-?\\(*\\-?[ABCD][1-4]\\)*|=\\-?\\(*\\-?(\\d*\\.)?\\d+\\)*)([\\+\\-\\*\\/]\\(*\\-?[ABCD][1-4]\\)*|[\\+\\-\\*\\/]\\(*\\-?(\\d*\\.)?\\d+\\)*)*";
    @Id
    @Column(name = "id")
    private int id;
    @Pattern(regexp = REGEX, message = "Expression Error")
    @Column(name = "a")
    public String fieldA;
    @Column(name = "hidden_a")
    private String hiddenFieldA;
    @Pattern(regexp = REGEX, message = "Expression Error")
    @Column(name = "b")
    private String fieldB;
    @Column(name = "hidden_b")
    private String hiddenFieldB;
    @Pattern(regexp = REGEX, message = "Expression Error")
    @Column(name = "c")
    private String fieldC;
    @Column(name = "hidden_c")
    private String hiddenFieldC;
    @Pattern(regexp = REGEX, message = "Expression Error")
    @Column(name = "d")
    private String fieldD;
    @Column(name = "hidden_d")
    private String hiddenFieldD;

    public Line(int id, String FieldA, String hiddenFieldA, String fieldB, String hiddenFieldB, String fieldC, String hiddenFieldC, String fieldD, String hiddenFieldD) {
        this.id = id;
        this.fieldA = FieldA;
        this.hiddenFieldA = hiddenFieldA;
        this.fieldB = fieldB;
        this.hiddenFieldB = hiddenFieldB;
        this.fieldC = fieldC;
        this.hiddenFieldC = hiddenFieldC;
        this.fieldD = fieldD;
        this.hiddenFieldD = hiddenFieldD;
    }

    public Line() {

    }


    @Override
    public int compareTo(Line o) {
        if (id == o.id)
            return 0;
        else if (id < o.id)
            return -1;
        else return 1;
    }
}
