package undo.dto;

import undo.util.ChangeTypeEnum;

/**
 * Created by Thofiq.Khan on 8/23/2017.
 */
public class ChangeDto {

    private String changeTypeString;
    private int cursorPosition;
    private String changedString;
    private int oldPosition;
    private int newPosition;

    /**
     * Constructor to create change object with required fields
     * @param changeType
     * @param cursorPosition
     * @param changedString
     * @param oldPosition
     * @param newPosition
     */
    public ChangeDto(ChangeTypeEnum changeType, int cursorPosition, String changedString,
                     int oldPosition, int newPosition){
        this.changeTypeString = changeType.toString();
        this.cursorPosition = cursorPosition;
        this.changedString = changedString;
        this.oldPosition = oldPosition;
        this.newPosition = newPosition;
    }

    public String getChangeTypeString() {
        return changeTypeString;
    }

    public int getCursorPosition() {
        return cursorPosition;
    }

    public String getChangedString() {
        return changedString;
    }

    public int getOldPosition() {
        return oldPosition;
    }

    public int getNewPosition() {
        return newPosition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ChangeDto changeDto = (ChangeDto) o;

        if (cursorPosition != changeDto.cursorPosition) return false;
        if (oldPosition != changeDto.oldPosition) return false;
        if (newPosition != changeDto.newPosition) return false;
        if (!changeTypeString.equals(changeDto.changeTypeString)) return false;
        return changedString.equals(changeDto.changedString);

    }

    @Override
    public int hashCode() {
        int result = changeTypeString.hashCode();
        result = 31 * result + cursorPosition;
        result = 31 * result + changedString.hashCode();
        result = 31 * result + oldPosition;
        result = 31 * result + newPosition;
        return result;
    }
}
