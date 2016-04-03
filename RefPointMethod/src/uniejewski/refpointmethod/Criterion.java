package uniejewski.refpointmethod;

import java.util.ArrayList;

public class Criterion {
	private int direction;
	private boolean type;
	private ArrayList<String> valueNames = new ArrayList<String>();
	private Double weight;
	
	public int getDirection() {
		return direction;
	}
	public void setDirection(int direction) {
		this.direction = direction;
	}
	
	public boolean getType() {
		return type;
	}
	public void setType(boolean type) {
		this.type = type;
	}
	
	public ArrayList<String> getValueNames() {
		return valueNames;
	}
	public void addValueName(String name) {
		this.valueNames.add(name);
	}
	public Double getWeight() {
		return weight;
	}
	public void setWeight(Double weight) {
		this.weight = weight;
	}
	public int getValue(String valueName){
		if(!type){
			for(int i = 0; i < valueNames.size(); i++){
				if (valueNames.get(i).equals(valueName)){
					return i*direction;
				}
			}
			return 0;
		}
		else{
			return Integer.parseInt(valueName)*direction;
		}
	}
}
