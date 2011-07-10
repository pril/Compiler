package base;

import base.exceptions.HashItemException;


public class HashItem<Key,Value> implements base.interfaces.HashItem<Key, Value>{

	Key key=null;
	Value value = null;
	
	public HashItem(Key key,Value value) throws HashItemException
	{
		setValue(value);
		setKey(key);
	}
	
	@Override
	public Key getKey() {
		return key;
	}

	@Override
	public Value getValue() {
		return value;
	}

	@Override
	public void setValue(Value value) throws HashItemException {
//		if (value== null) 
//		{
//					throw new HashItemException("Value darf nicht null sein");
//		}
//		doch darf es.
		this.value = value;
	}

	@Override
	public void setKey(Key key) throws HashItemException {
		if (key== null) throw new HashItemException("Key darf nicht null sein");
		this.key = key;
	}

	@Override
	public int compareTo(Value arg0) {
		if (value instanceof Comparable)
		{
			Comparable<Value> comparable = (Comparable)value;
			return comparable.compareTo(arg0);
		}
		return 0;
	}

}
