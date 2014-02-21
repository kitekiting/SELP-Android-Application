package com.example.selp;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;


import android.os.AsyncTask;
import android.util.Log;



public class Downloader extends AsyncTask<String, Void, ArrayList<String>> {
	private static final String TAG = "Downloader";
	//nodes for courses.xml	
	static final String NODE_COURSE = "course";
	static final String NODE_URL = "url";
	static final String NODE_NAME = "name";
	static final String NODE_DRPS = "drps";
	static final String NODE_EUCLID = "euclid";
	static final String NODE_ACRONYM = "acronym";
	static final String NODE_LEVEL = "level";
	static final String NODE_POINTS = "points";
	static final String NODE_YEAR = "year";
	static final String NODE_DEV_PER = "deliveryperiod";
	static final String NODE_LECTURER = "lecturer";
	static final String NODE_AI = "ai";
	static final String NODE_CG = "cg";
	static final String NODE_CS = "cs";
	static final String NODE_SE = "se";
	
	static ArrayList<Course> courseNames;
	
	//nodes and attributes for timetable.xml
	static final String NODE_SEMESTER = "semester";
	static final String ATTR_NUMBER = "number";
	static final String NODE_DAY = "day";
	static final String ATTR_NAME = "name";
	static final String NODE_TIME = "time";
	static final String ATTR_START = "start";
	static final String ATTR_END = "finish";
	static final String NODE_LECTURE = "lecture";
	static final String NODE_COURSEID = "course";
	static final String NODE_YEARS = "years";
	static final String NODE_ROOM = "room";
	static final String NODE_BUILDING = "building";
	static final String NODE_COMMENT = "comment";
	
	static ArrayList<Times> timesList;
	
	//nodes for venues.xml
	//static final String NODE_BUILDING = "";
	static final String NODE_BUILDID = "name";
	static final String NODE_DESCRIPTION = "description";
	static final String NODE_MAP = "map";
	
	static ArrayList<Building> buildingList;
	static ArrayList<Room> roomsList;
	
	static ArrayList<String> xmlList;
	
	
		
	    @Override
	    protected ArrayList<String> doInBackground(String... urls) {
	        String courseXml = null;
	        ArrayList<String> xmls = new ArrayList<String>();
	        for (String url : urls) {
	        	courseXml = getXmlFromUrl(url);
	        	xmls.add(courseXml);
	        }
	        return xmls;
	    }
	    
	    
	    
	    
	    @Override
        protected void onPostExecute(ArrayList<String> xmls) {
	        setXMLs(xmls);
	    	Log.d(TAG,"Background Done");
	        
	       
	       
	        //building the doc for courses
	        String courseXML = xmls.get(0);
	    	XmlParser parser = new XmlParser();
	        InputStream courseStreams = new ByteArrayInputStream(courseXML.getBytes());
	        Document courseDoc = parser.getDocument(courseStreams);
	        NodeList courseNodeList = courseDoc.getElementsByTagName(NODE_COURSE);
	        ArrayList<Course> courses = new ArrayList<Course>();
	        Course course = null;
	        
	        //building the doc for times
	        String timesXML = xmls.get(1);
	        XmlParser parserT = new XmlParser();
	        InputStream timeStreams = new ByteArrayInputStream(timesXML.getBytes());
	        Document timeDoc = parser.getDocument(timeStreams);
	        NodeList timesNodeList = timeDoc.getElementsByTagName(NODE_LECTURE);
	        ArrayList<Times> times = new ArrayList<Times>();
	        Times time = null;
	        
	      //building the doc for venues
	        String venuesXML = xmls.get(2);
	        
	        InputStream venueStreams = new ByteArrayInputStream(venuesXML.getBytes());
	        
	        Document buildingDoc = parser.getDocument(venueStreams);
	       
	        
	        NodeList buildingNodeList = buildingDoc.getElementsByTagName(NODE_BUILDING);
	        NodeList roomsNodeList = buildingDoc.getElementsByTagName(NODE_ROOM);
	        
	        ArrayList<Building> buildings = new ArrayList<Building>();
	        ArrayList<Room> rooms = new ArrayList<Room>();
	        
	        Building building = null;
	        Room room = null;
	      
	      //building the course list  
	        for (int i = 0; i < courseNodeList.getLength(); i++) {
	            course = new Course();
	            Element e = (Element) courseNodeList.item(i);
	            course.setUrl(parser.getValue(e, NODE_URL));
	            course.setName(parser.getValue(e, NODE_NAME));
	            course.setDrps(parser.getValue(e, NODE_DRPS));
	            course.setEuclid(parser.getValue(e, NODE_EUCLID));
	            course.setAcronym(parser.getValue(e, NODE_ACRONYM));
	            course.setLevel(parser.getValue(e, NODE_LEVEL));
	            course.setPoints(parser.getValue(e, NODE_POINTS));
	            course.setYear(parser.getValue(e, NODE_YEAR));
	            course.setDevPer(parser.getValue(e, NODE_DEV_PER));
	            course.setLecturer(parser.getValue(e, NODE_LECTURER));
	            course.setAI(parser.getValue(e, NODE_AI));
	            course.setCG(parser.getValue(e, NODE_CG));
	            course.setCS(parser.getValue(e, NODE_CS));
	            course.setSE(parser.getValue(e, NODE_SE));
	            courses.add(course);
	            
	        }
	        
	        Downloader.setCourseList(courses);
	        Log.d(TAG,"Length of courselist outside the loop: " + courseNames.size());
	        
	        //building the times list
	        
	        for (int i = 0; i < timesNodeList.getLength(); i++) {
	            time = new Times();
	            Element e = (Element) timesNodeList.item(i);
	            time.setSemester((e.getParentNode().getParentNode().getParentNode().getParentNode()).getAttributes().getNamedItem(ATTR_NUMBER).getTextContent());
	            time.setDay((e.getParentNode().getParentNode()).getAttributes().getNamedItem(ATTR_NAME).getTextContent());
	            time.setStartTime(e.getParentNode().getAttributes().getNamedItem(ATTR_START).getTextContent());
	            time.setEndTime(e.getParentNode().getAttributes().getNamedItem(ATTR_END).getTextContent());
	            time.setCourseID(parserT.getValue(e, NODE_COURSEID));
	           
	            //checking for the multiple years in the XML file
	            time.setYears(parserT.getValue(e, NODE_YEARS));
	           
	            time.setRoom(parserT.getValue(e, NODE_ROOM));
	            time.setBuilding(parserT.getValue(e, NODE_BUILDING));
	            time.setComment(parserT.getValue(e, NODE_COMMENT));
	            times.add(time);
	            
	        }
	        
	        Downloader.setTimesList(times);
	       
	      //building the Buildings list
	        for (int i = 0; i < buildingNodeList.getLength(); i++) {
	            building = new Building();
	            Element e = (Element) buildingNodeList.item(i);
	            building.setName(parser.getValue(e, NODE_BUILDID));
	            building.setDescription(parser.getValue(e, NODE_DESCRIPTION));
	            building.setMap(parser.getValue(e, NODE_MAP));
	            buildings.add(building);
	            
	        }
	        Downloader.setBuildings(buildings);
	        Log.d(TAG,"Length of Buildings list outside the loop: " + buildingList.size());
	        
	        
	      //building the rooms list
	        for (int i = 0; i < roomsNodeList.getLength(); i++) {
	            room = new Room();
	            Element e = (Element) roomsNodeList.item(i);
	            room.setName(parser.getValue(e, NODE_NAME));
	            room.setDescription(parser.getValue(e, NODE_DESCRIPTION));
	            rooms.add(room);
	            
	        }
	        Downloader.setRooms(rooms);
	        Log.d(TAG,"Length of rooms list outside the loop: " + roomsList.size());
	        
	    }
	

	//methods for getting the various lists for use in different UI's
	
	public static void setCourseList(ArrayList<Course> list){
    	courseNames = list;
	}
	
	public ArrayList<Course> getList(){
		return courseNames;
	}
	public static void setTimesList(ArrayList<Times> listTimes){
    	timesList = listTimes;
	}
	
	public ArrayList<Times> getTimes(){
		return timesList;
	}
	public static void setBuildings(ArrayList<Building> listBuilding){
    	buildingList = listBuilding;
	}
	
	public ArrayList<Building> getBuildings(){
		return buildingList;
	}
	public static void setRooms(ArrayList<Room> listRoom){
    	roomsList = listRoom;
	}
	
	public ArrayList<Room> getRooms(){
		return roomsList;
	}
	
	public static void setXMLs(ArrayList<String> XMLs){
    	xmlList = XMLs;
	}
	
	public ArrayList<String> getXMLs(){
		return xmlList;
	}
	    
	  //Getting the XML from the URL method which returns a string
	 //Used in DoInBackground inside the async task
	    
	    private String getXmlFromUrl(String urlString) {
	    	StringBuffer output = new StringBuffer("");

	        InputStream stream = null;
	        URL url;
	        try {
	            url = new URL(urlString);
	            URLConnection connection = url.openConnection();

	            HttpURLConnection httpConnection = (HttpURLConnection) connection;
	            httpConnection.setRequestMethod("GET");
	            httpConnection.connect();

	            if (httpConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
	                stream = httpConnection.getInputStream();
	                BufferedReader buffer = new BufferedReader(
	                        new InputStreamReader(stream));
	                String s = "";
	                while ((s = buffer.readLine()) != null)
	                    output.append(s);
	            }
	        } catch (MalformedURLException e) {
	            Log.e(TAG, "Unable to parse URL", e);
	        } catch (IOException e) {
	            Log.e(TAG, "IO Exception", e);
	        }

	        return output.toString();
	    }
	

		
}
