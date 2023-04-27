package org.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Field {
    private boolean hasMine;
    private boolean hasFlag;
    private boolean isRevealed;

    public void setAll(boolean hasMine, boolean hasFlag, boolean isRevealed){
        this.hasMine = hasMine;
        this.hasFlag = hasFlag;
        this.isRevealed = isRevealed;
    }

    public String printBareField(){
        String[] field = new String[5];
        field[0] = "[";
        field[field.length - 1] = "]";
        field[1] = field[2] = field[3] = ".";
        StringBuilder result = new StringBuilder();
        for (String s : field) {
            result.append(s);
        }
        return result.toString();
    }

    public String printFlagField(){
        String[] field = new String[5];
        field[0] = "[";
        field[field.length - 1] = "]";
        field[1] = field[2] = ".";
        field[3] = "f";
        StringBuilder result = new StringBuilder();
        for (String s : field) {
            result.append(s);
        }
        return result.toString();
    }


    @Override
    public String toString() {
        String[] field = new String[5];
        field[0] = "[";
        field[field.length - 1] = "]";
        field[1] = field[2] = field[3] = ".";
        if (hasMine){
            field[1] = "M";
        }
        if (isRevealed){
            field[2] = "o";
        }
        if(hasFlag){
            field[3] = "f";
        }
        StringBuilder result = new StringBuilder();
        for (String s : field) {
            result.append(s);
        }
        return result.toString();
    }
}
