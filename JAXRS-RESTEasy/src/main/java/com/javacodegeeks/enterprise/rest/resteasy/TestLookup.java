package com.javacodegeeks.enterprise.rest.resteasy;

import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class TestLookup {

	    /*
	     * Your AWS Access Key ID, as taken from the AWS Your Account page.
	     */
	    private static final String AWS_ACCESS_KEY_ID = "AKIAIF55KUEZLEM4L7VA";

	    /*
	     * Your AWS Secret Key corresponding to the above ID, as taken from the AWS
	     * Your Account page.
	     */
	    private static final String AWS_SECRET_KEY = "GFWMs7etO8UpWzhgLsqBKMLO5fGantq/UCFdHn+G";

	    /*
	     * Use the end-point according to the region you are interested in.
	     */
	    private static final String ENDPOINT = "webservices.amazon.in";

	    public static void main(String[] args) {

	        /*
	         * Set up the signed requests helper.
	         */
	        SignedRequestsHelper helper;

	        try {
	            helper = SignedRequestsHelper.getInstance(ENDPOINT, AWS_ACCESS_KEY_ID, AWS_SECRET_KEY);
	        } catch (Exception e) {
	            e.printStackTrace();
	            return;
	        }

	        String requestUrl = null;

	        Map<String, String> params = new HashMap<String, String>();

	        params.put("Service", "AWSECommerceService");
	        params.put("Operation", "ItemSearch");
	        params.put("AWSAccessKeyId", "AKIAIF55KUEZLEM4L7VA");
	        params.put("AssociateTag", "testingyet-20");
	        params.put("SearchIndex", "Books");
	        params.put("ResponseGroup", "Images,ItemAttributes,Offers");
	        params.put("Sort", "salesrank");
	        params.put("Author", "Chetan Bhagat");

	        requestUrl = helper.sign(params);
	        System.out.println("Request is \"" + requestUrl + "\"");
	        String title = null;
	        
	        title = fetchTitle(requestUrl);
	        System.out.println("Signed Title is \"" + title + "\"");
	        System.out.println("Signed URL: \"" + requestUrl + "\"");
	    }
	    
	    /*
	     * Utility function to fetch the response from the service and extract the
	     * title from the XML.
	     */
	    private static String fetchTitle(String requestUrl) {
	        String title = null;
	        try {
	            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	            DocumentBuilder db = dbf.newDocumentBuilder();
	            Document doc = db.parse(requestUrl);
	            Node titleNode = doc.getElementsByTagName("Title").item(0);
	            title = titleNode.getTextContent();
	        } catch (Exception e) {
	            throw new RuntimeException(e);
	        }
	        return title;
	    }	    
	}


