package com;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;



















@Path("/controller")
public class RESTController {

	public RESTController() {

	// FIRST OF ALL, WE SHALL READ JSON DATABASE INTO LOCAL ARRAYLIST FOR RESTFUL SERVER
		readParentJsonToArrayList();
		readChildJsonToArrayList();
	}

	JSONArray jsonArrayGlobalParent;
	JSONObject jsonObjectGlobalParent;

	
	
	
	
	//  CLIENT SHALL TARGET THIS PATH FOR PARENT TABLE DATA
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/parentmainpage")
	public ArrayList<Parent> parentPageMain() {

		if (jsonReadCheckFlagParent == false) {
			return readParentJsonToArrayList();
		}

		else {
			return parentDataArrayList;
		}

	}

	
	
	 // CLIENT SHALL TARGET THIS PATH FOR CHILD TABLE DATA
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/childmainpage")
	public ArrayList<Child> childPageMain() {

		if (jsonReadCheckFlagChild == false) {
			return readChildJsonToArrayList();
		} else {
			return childDataArrayList;
		}
	}
	
	
	
	
	

	public JSONObject getParentDataJSONObject() {

		JSONParser parser = new JSONParser();
		Object object;

		try {
			object = parser.parse(new FileReader("Parent.json"));
			JSONObject jsonObject = (JSONObject) object;

			JSONArray data = (JSONArray) jsonObject.get("data");
			jsonArrayGlobalParent = data;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return jsonObjectGlobalParent;
	}

	public JSONArray getParentData() {

		JSONParser parser = new JSONParser();
		Object object;

		try {
			object = parser.parse(new FileReader("Parent.json"));
			JSONObject jsonObject = (JSONObject) object;
			JSONArray data = (JSONArray) jsonObject.get("data");
			jsonArrayGlobalParent = data;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return jsonArrayGlobalParent;
	}

	public long getTotalPaidAmountFromChildUsingParentId(long parentId) {

		ArrayList<Child> al;

		if (jsonReadCheckFlagChild) {
			al = childDataArrayList;
		} else {
			al = readChildJsonToArrayList();

		}

	

		long totalPaidAmount = 0;
		for (Child c : al) {
			if (c.getParentId() == parentId) {
				totalPaidAmount = totalPaidAmount + c.getPaidAmount();
			}
		}

		return totalPaidAmount;
	}

	public String getSenderNameFromParentByParentIdFromChild(long parentIdFromChild) {

		ArrayList<Parent> alp;

		if (jsonReadCheckFlagParent) {
			alp = parentDataArrayList;
		}

		else {
			alp = readParentJsonToArrayList();
		}

		String senderName = "not found";

		for (Parent p : alp) {
			if (p.getId() == parentIdFromChild) {
				senderName = p.getSender();
			}
		}

		return senderName;

	}

	public String getReceiverNameFromParentByParentIdFromChild(long parentIdFromChild) {

		ArrayList<Parent> alp;

		if (jsonReadCheckFlagParent) {
			alp = parentDataArrayList;
		}

		else {
			alp = readParentJsonToArrayList();
		}

		String receiverName = "not found";

		for (Parent p : alp) {
			if (p.getId() == parentIdFromChild) {
				receiverName = p.getReceiver();
			}
		}

		return receiverName;

	}

	public long getTotalAmountFromParentByParentIdFromChild(long parentIdFromChild) {

		ArrayList<Parent> alp;

		if (jsonReadCheckFlagParent) {
			alp = parentDataArrayList;
		}

		else {
			alp = readParentJsonToArrayList();
		}

		long totalAmount = 0;

		for (Parent p : alp) {
			if (p.getId() == parentIdFromChild) {
				totalAmount = p.getTotalAmount();
			}
		}

		return totalAmount;

	}

	ArrayList<Parent> parentDataArrayList;

	
	
	
	// THIS METHOD READS THE PARENT JSON DATA TO ARRAYLIST
	public ArrayList<Parent> readParentJsonToArrayList() {

		jsonReadCheckFlagParent = true;

		parentDataArrayList = new ArrayList<>();

		JSONParser parser = new JSONParser();
		Object object;
		try {
			object = parser.parse(new FileReader("Parent.json"));

			JSONObject jsonObject = (JSONObject) object;
			JSONArray data = (JSONArray) jsonObject.get("data");
			jsonArrayGlobalParent = data;
			@SuppressWarnings("rawtypes")
			Iterator iterator = data.iterator();
			while (iterator.hasNext()) {

				Object o = iterator.next();
				JSONObject js = (JSONObject) o;
				System.out.println(js.get("id"));
				Parent parent = new Parent();
				parent.setId((long) js.get("id"));
				parent.setSender((String) js.get("sender"));
				parent.setReceiver((String) js.get("receiver"));
				parent.setTotalAmount((long) js.get("totalAmount"));
				parent.setTotalPaidAmount(getTotalPaidAmountFromChildUsingParentId((long) js.get("id")));
				parentDataArrayList.add(parent);
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return parentDataArrayList;
	}

	ArrayList<Child> childDataArrayList;

	
	
	
	
	
	// THIS METHOD READS THE CHILD JSON DATA TO ARRAYLIST
	public ArrayList<Child> readChildJsonToArrayList() {

		jsonReadCheckFlagChild = true;

		childDataArrayList = new ArrayList<>();

		JSONParser parser = new JSONParser();
		Object object;
		try {
			object = parser.parse(new FileReader("Child.json"));

			JSONObject jsonObject = (JSONObject) object;
			JSONArray data = (JSONArray) jsonObject.get("data");
			@SuppressWarnings("rawtypes")
			Iterator iterator = data.iterator();
			while (iterator.hasNext()) {

				Object o = iterator.next();
				JSONObject js = (JSONObject) o;
				System.out.println(js.get("id"));
				Child child = new Child();
				child.setId((long) js.get("id"));
				child.setParentId((long) js.get("parentId"));
				child.setPaidAmount((long) js.get("paidAmount"));
				child.setSender(getSenderNameFromParentByParentIdFromChild((long) js.get("parentId")));
				child.setReceiver(getReceiverNameFromParentByParentIdFromChild((long) js.get("parentId")));
				child.setTotalAmount(getTotalAmountFromParentByParentIdFromChild((long) js.get("parentId")));

				childDataArrayList.add(child);
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return childDataArrayList;
	}

	Boolean jsonReadCheckFlagChild = false;
	Boolean jsonReadCheckFlagParent = false;

} // end of class
