package undo.impl;

import undo.Change;
import undo.Document;
import undo.dto.ChangeDto;
import undo.util.ChangeTypeEnum;

/**
 * Created by Thofiq.Khan on 8/22/2017.
 */
public class ChangeImpl implements Change{

	private ChangeDto changeDto;

	/**
	 * Change object creation using mandatory attributes of change dto
	 * @param changeDto
     */
	public ChangeImpl(ChangeDto changeDto) {
		this.changeDto = changeDto;
	}

	@Override
	public String getType() {
		return changeDto.getChangeTypeString();
	}

	@Override
	public void apply(Document doc) {
		try {
			int cursorPosition = changeDto.getCursorPosition();
			String changedString = changeDto.getChangedString();
			int newPosition = changeDto.getNewPosition();
			if (getType().equals(ChangeTypeEnum.INSERT.name())) {
				doc.insert(cursorPosition, changedString);
				doc.setDot(newPosition);
			} else {
				doc.delete(cursorPosition, changedString);
				doc.setDot(newPosition);
			}
		}catch (Exception e){
			throw new IllegalStateException();
		}
	}

	/**
	 * If insert performed delete the record and vice versa
	 * @param doc The document to revert the change in.
     */
	@Override
	public void revert(Document doc) {
		try {
			int cursorPosition = changeDto.getCursorPosition();
			String changedString = changeDto.getChangedString();
			int newPosition = changeDto.getNewPosition();
			if (getType().equals(ChangeTypeEnum.INSERT.name())) {
				doc.delete(cursorPosition, changedString);
				doc.setDot(newPosition);
			} else {
				doc.insert(cursorPosition, changedString);
				doc.setDot(newPosition);
			}
		}catch (Exception exception){
			throw new IllegalStateException(exception);
		}
	}

	public ChangeDto getChangeDto() {
		return changeDto;
	}
}
