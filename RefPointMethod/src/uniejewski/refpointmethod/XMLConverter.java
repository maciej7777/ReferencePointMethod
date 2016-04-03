package uniejewski.refpointmethod;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLConverter {
	
	File inputFile;
	Document doc;

	XMLConverter(String input){
		inputFile = new File(input);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
	        doc = dBuilder.parse(inputFile);
	        doc.getDocumentElement().normalize();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Criterion> getCriteria(){
		ArrayList<Criterion> criteria = new ArrayList<Criterion>();
		
        NodeList nList = doc.getElementsByTagName("criterion");
        for (int i = 0; i < nList.getLength(); i++) {
        	Node nNode = nList.item(i);
        	if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                 Element eElement = (Element) nNode;
        		 Criterion tmp = new Criterion();
        		 tmp.setWeight(Double.parseDouble(eElement.getElementsByTagName("weight").item(0).getTextContent()));
        		 
        		 String direction = eElement.getElementsByTagName("direction").item(0).getTextContent();
        		 if (direction.equals("+")){
        			 tmp.setDirection(1);
        		 }
        		 else if (direction.equals("-")){
        			 tmp.setDirection(-1);
        		 }
        		 else return null;
        	
        		 String type= eElement.getElementsByTagName("type").item(0).getTextContent();
        		 if(type.equals("numerical")){
        			 tmp.setType(true);
        		 }
        		 else if(type.equals("ordinal")){
        			 tmp.setType(false);
        			 NodeList values = eElement.getElementsByTagName("value");
        			 for(int j=0; j<values.getLength(); j++){
        				 Node valuesNode = values.item(j);
        				 if (valuesNode.getNodeType() == Node.ELEMENT_NODE) {
        					 String tmpValueName = valuesNode.getTextContent().trim();
        					 tmpValueName.replaceAll("\t", "");
        					 tmpValueName.replaceAll("\n", "");
        					 tmp.addValueName(tmpValueName);
        				 }
        			 }
        		 }
        		 else return null;    
        		 criteria.add(tmp);
        	}
        }

		return criteria;
	}
	
	public ArrayList<RefPoint> getRefPoints(ArrayList<Criterion> array){
		ArrayList<RefPoint> result = new ArrayList<RefPoint>();
		
		 NodeList nList = doc.getElementsByTagName("refpoint");
	        for (int i = 0; i < nList.getLength(); i++) {
	        	Node nNode = nList.item(i);
	        	if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	                 Element eElement = (Element) nNode;
	                 RefPoint tmp = new RefPoint();
        			 NodeList values = eElement.getElementsByTagName("criterionValue");
        			 for(int j=0; j<values.getLength(); j++){
        				 Node valuesNode = values.item(j);
        				 if (valuesNode.getNodeType() == Node.ELEMENT_NODE) {
        					 String tmpValueName = valuesNode.getTextContent().trim();
        					 tmpValueName.replaceAll("\t", "");
        					 tmpValueName.replaceAll("\n", "");
        					 tmp.addCriterionOriginalValue(tmpValueName);
        					 tmp.addCtiterionValue(array.get(j).getValue(tmp.getCriteriaOriginalValues().get(j)));
        				 }
        			 }
        			 result.add(tmp);
	        	}
	        }
		
		return result;
	}
	
	public ArrayList<Alternative> getAlternatives(ArrayList<Criterion> criteria, ArrayList<RefPoint> refPoints){
		ArrayList<Alternative> alternatives = new ArrayList<Alternative>();
		
        NodeList nList = doc.getElementsByTagName("alternative");
        for (int i = 0; i < nList.getLength(); i++) {
        	Node nNode = nList.item(i);
        	if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                 Element eElement = (Element) nNode;
        		 Alternative tmp = new Alternative();
        		 tmp.setName(eElement.getElementsByTagName("name").item(0).getTextContent());
    			 NodeList values = eElement.getElementsByTagName("criterionValue");
    			 for(int j=0; j<values.getLength(); j++){
    				 Node valuesNode = values.item(j);
    				 if (valuesNode.getNodeType() == Node.ELEMENT_NODE) {
    					 String tmpValueName = valuesNode.getTextContent().trim();
    					 tmpValueName.replaceAll("\t", "");
    					 tmpValueName.replaceAll("\n", "");
    					 tmp.addCriterionOriginalValue(tmpValueName);
    					 tmp.addCtiterionValue(criteria.get(j).getValue(tmp.getCriteriaOriginalValues().get(j)));
    				 }
    			 }
    			 tmp.countMarks(refPoints, criteria);
    			 alternatives.add(tmp);
        	}
        }
		
		return alternatives;
	}
}
