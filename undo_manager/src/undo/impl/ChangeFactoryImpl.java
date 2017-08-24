package undo.impl;

import undo.Change;
import undo.ChangeFactory;
import undo.dto.ChangeDto;
import undo.util.ChangeTypeEnum;

/**
 * Its dummy implementation for testing
 * Created by Thofiq.Khan on 8/22/2017.
 *
 */
public class ChangeFactoryImpl implements ChangeFactory{

	@Override
	public Change createDeletion(int pos, String s, int oldDot, int newDot) {
		ChangeDto changeDto = new ChangeDto(ChangeTypeEnum.DELETE,pos, s, oldDot, newDot);
		return new ChangeImpl(changeDto);
	}

	@Override
	public Change createInsertion(int pos, String s, int oldDot, int newDot) {
		ChangeDto changeDto = new ChangeDto(ChangeTypeEnum.INSERT,pos, s, oldDot, newDot);
		return new ChangeImpl(changeDto);
	}

	/**
	 * Change factory singleton object for undo manager client UndoManagerClient
	 */
	private static ChangeFactory changeFactory;

	private ChangeFactoryImpl(){
	}

	public static ChangeFactory getChangeFactoryInstance(){
		if(null == changeFactory){
			changeFactory =  new ChangeFactoryImpl();
		}
		return changeFactory;
	}

}
