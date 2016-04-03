package uniejewski.refpointmethod;

import java.util.ArrayList;

public class RefPointMethod {

	public static void main(String[] args) {
			XMLConverter conv = new XMLConverter("Input.xml");
			ArrayList<Criterion> criteria = conv.getCriteria();
			ArrayList<RefPoint> refPoints = conv.getRefPoints(criteria);
			ArrayList<Alternative> alternatives = conv.getAlternatives(criteria, refPoints);
			
			PositionsCalculator calc = new PositionsCalculator();	
			ArrayList<Alternative> sortedAlternatives = calc.calcPositions(alternatives);
			
			for(Alternative alternative : sortedAlternatives){
				System.out.println(alternative.getName());
				System.out.println(alternative.getPosition());
				//for(int i =0; i < alternative.getCriteriaOriginalValues().size(); i++){
				//	System.out.println(alternative.getCriteriaOriginalValues().get(i) + " " + alternative.getCriteriaValues().get(i));
				//}
				for(int i =0; i < alternative.getMarks().size(); i++){
					System.out.println(alternative.getMarks().get(i));
				}
			}
			
	}

}
