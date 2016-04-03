package uniejewski.refpointmethod;

import java.util.ArrayList;

public class MethodObject {
	private ArrayList<String> criteriaOriginalValues = new ArrayList<String>();
	private ArrayList<Integer> criteriaValues = new ArrayList<Integer>();
	
	public ArrayList<String> getCriteriaOriginalValues() {
		return criteriaOriginalValues;
	}
	public void setCriteriaOriginalValues(ArrayList<String> criteriaOriginalValues) {
		this.criteriaOriginalValues = criteriaOriginalValues;
	}
	public ArrayList<Integer> getCriteriaValues() {
		return criteriaValues;
	}
	public void setCriteriaValues(ArrayList<Integer> criteriaValues) {
		this.criteriaValues = criteriaValues;
	}
	public void addCriterionOriginalValue(String value){
		criteriaOriginalValues.add(value);
	}
	public void addCtiterionValue(int value){
		criteriaValues.add(Integer.valueOf(value));
	}
	
}
