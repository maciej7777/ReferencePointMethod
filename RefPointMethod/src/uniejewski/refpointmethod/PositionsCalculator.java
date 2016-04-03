package uniejewski.refpointmethod;

import java.util.ArrayList;

public class PositionsCalculator {
	public ArrayList<Alternative> calcPositions(ArrayList<Alternative> inputAlternatives){	
		return sortFromPlace(inputAlternatives, 1, 0);
	}
	
	private ArrayList<Alternative> sortFromPlace(ArrayList<Alternative> inputAlternatives, 
			int firstPosition, int level){
		ArrayList<Alternative> sortedAlternatives = new ArrayList<Alternative>();
		Double lastMax = 2.0;
		
		while(sortedAlternatives.size() != inputAlternatives.size()){
			ArrayList<Alternative> maxAlternatives = new ArrayList<Alternative>();
			Double max = inputAlternatives.get(0).getMarks().get(level);
			maxAlternatives.add(inputAlternatives.get(0));
			
			for (int i = 1; i < inputAlternatives.size(); i++) {
			    if (inputAlternatives.get(i).getMarks().get(level) > max && 
			    		inputAlternatives.get(i).getMarks().get(level) < lastMax) {
			    	max = inputAlternatives.get(i).getMarks().get(level);
			    	maxAlternatives.clear();
			    	maxAlternatives.add(inputAlternatives.get(i));
			    }
			    else if (inputAlternatives.get(i).getMarks().get(level).equals(max)&& 
			    		inputAlternatives.get(i).getMarks().get(level) < lastMax){
			    	maxAlternatives.add(inputAlternatives.get(i));
			    }
			}
			
			lastMax = max;
			if(maxAlternatives.size() == 1){
				maxAlternatives.get(0).setPosition(firstPosition);
				sortedAlternatives.add(maxAlternatives.get(0));
				firstPosition++;
			}
			else if((level + 1) < inputAlternatives.get(0).getMarks().size()){
				sortedAlternatives.addAll(sortFromPlace(maxAlternatives, firstPosition, level+1));
				firstPosition+=maxAlternatives.size();
			}
			else{
				sortedAlternatives.addAll(maxAlternatives);
				for(Alternative alternative: maxAlternatives){
					alternative.setPosition(firstPosition);
				}
				firstPosition+=maxAlternatives.size();
			}
		}
		
		
		return sortedAlternatives;
	}
}
