package org.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/**

 A class representing a single field on a MineSweeperBoard.

 Each Field can have a mine, a flag, and be revealed or not.
 */
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
    /**

     Returns a string representation of a field with no flag and no mine.
     @return the string representation of the field
     */
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
    /**

     Returns a string representation of a field with a flag.
     @return the string representation of the field with a flag
     */
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

/**

 Returns a string representation of the field, taking into account the presence of a mine, a flag, and whether it is revealed.
*/
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
