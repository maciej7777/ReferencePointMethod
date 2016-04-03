package uniejewski.refpointmethod;

import java.util.ArrayList;

public class Alternative extends MethodObject{
	private String name;
	private int position;
	private ArrayList<Double> marks = new ArrayList<Double>();
	//private ArrayList<Double> weights;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	public ArrayList<Double> getMarks() {
		return marks;
	}
	public void setMarks(ArrayList<Double> marks) {
		this.marks = marks;
	}
	/*
	public ArrayList<Double> getWeights() {
		return weights;
	}
	public void setWeights(ArrayList<Double> weights) {
		this.weights = weights;
	}
	*/
	public String toString(){
		String returnedValue = name+": ";
		for(int i=0; i<getCriteriaOriginalValues().size(); i++){
			returnedValue += getCriteriaOriginalValues().get(i)+ " ";
		}		
		return returnedValue;
	}
	public String resultToString(){
		String returnedValue = null;
		if(position!=0){
			returnedValue = Integer.toString(position) + ": ";
			for(int i=0; i<getMarks().size(); i++){
				returnedValue += getMarks().get(i)+ " ";
			}
		}
		return returnedValue;
	}
	public void countMarks(ArrayList<RefPoint> refPointsList, ArrayList<Criterion> criteria){
		for(int i=0; i<refPointsList.size(); i++){
			Double mark = 0.0;
			for(int j=0; j<getCriteriaValues().size(); j++){
				if(refPointsList.get(i).getCriteriaValues().get(j) <= getCriteriaValues().get(j)){
					mark+=criteria.get(j).getWeight();
				}
			}
			marks.add(mark);
		}
	}
}
