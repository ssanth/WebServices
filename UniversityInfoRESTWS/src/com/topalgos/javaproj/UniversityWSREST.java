package com.topalgos.javaproj;
/* REST: Representation State Transfer
	data/functionality is referred as Resource
	accessed using URI
	HTTP based , stateless communication
	good videos:
	   https://www.youtube.com/watch?v=Fc-H0hDi73M&index=8&list=PLBgMUB7xGcO0m3XXwhHO76E876qIJ8JAd
	   https://www.youtube.com/playlist?list=PLqq-6Pq4lTTZh5U8RbdXq0WaYvZBz2rbn
	   https://javabrains.io/courses/javaee_jaxrs/lessons/Sending-Status-Codes-and-Location-Headers
	download: https://jersey.java.net/download.html
	
	jaxp converts message to xml , MediaType.APPLICATION_XML
	to convert the message to json, one has to do the conversion ; MediaType.APPLICATION_JSON
	add jersey-media-moxy in pom file for it
	instanceURIs vs collectionURIs
	3 ways to access parms
	1. using Param, example
		@HeaderParam for authsessionid
		@CookieParam for cookie
	2.Using @Context for UriInfo, HttpHeaders
		uriInfo.getAbsolutePath().toString(); headers.getCookies()
	3. Using Bean Parm @BeanParam
		javax.ws.rs.QueryParam
		create a class for each queryparm and have its member ( with setters n getters) and use the instance
		@BeanParam classname objectname
		
	@XMLTransient, use it ignore 
	
	Implementing subresources class, see the image 
	
	Sending status codes and location Headers
	
	sample:
		http://localhost:8080/CalculatorWSRest/rest/university
		http://localhost:8080/CalculatorWSRest/rest/university/abc
 */
import javax.ws.rs.*;

import java.io.File;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

@Path("/university")
public class UniversityWSREST {
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String getHTMLUnivesityInfo(){
		return " <html> <title> REST webservice call for university name </title></html>";
	}
	
	//@GET
	//@Produces(MediaType.TEXT_PLAIN)
	//public String getTextUnivesityInfo(){
	//	return " REST webservice call for university name IN TEXT PLAIN";
	//}
	
	@PUT
	@Path("{value1}")
	@Produces(MediaType.TEXT_PLAIN)
	public String setHTMLUnivesityInfo(@PathParam("value1") String value1){
		return " setting the new value to " + value1 ;
	}
	
	// @Path("{value1}/{subvalue1}")
	
	// separating parms using & and order does matter
	@Produces(MediaType.TEXT_PLAIN)
	public String get2RollNumbers(
			@QueryParam("student1") int rollno1,
			@QueryParam("student2") int rollno2
			) {
		return (" we got 2 roll no.s using query string param" + rollno1 + " and " + rollno1);
	}
	
	// separating parms using semicolon and order does not matter
	@Produces(MediaType.TEXT_PLAIN)
	public String get2RollNumbersUsingMatrixParam(
			@MatrixParam("student1") int rollno1,
			@MatrixParam("student2") int rollno2
			) {
		return (" we got 2 roll no.s using matrix string param" + rollno1 + " and " + rollno1);
	}
	
	// calling this path from form action, post, inside the form have 2 html elements
	@POST
	@Path("/add")
	public String add(
			@FormParam("value1") int value1,
			@FormParam("value2") int value2
			) {
		return (" we got 2 values from html form " + value1 + " and " + value2);
	}
	
	// Use Response Object for status code
	@GET
	@Path("/getStudentInfo")
	@Produces("application/pdf") // "image/png" "text/plain")
	public Response getStudentInfoPDF() {
		File file = new File("c:\\somefile.pdf");
		
		// return file;
		// save or open.. sometime we can also add status code with the file
		ResponseBuilder response = Response.ok((Object) file);
		response.header("Content-Disposition", "attachment;filename=DisplayName-somefile.pdf");
		return response.build();
		//response.status(Status.CREATED).build();
		//response.created(URI)
		
	}
	
	// for upload it multipart, see the image attached for the code
	
	
}
