package com.flow.service;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.flowable.engine.RuntimeService;
import org.flowable.engine.repository.ProcessDefinition;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;
import org.w3c.dom.Document;

@RestController
@RequestMapping("/processes")
public class ActiveProcessResource {

@Autowired private RestTemplate restTemplate;
@Autowired RuntimeService runtimeService;
@Autowired ProcessDefinition processDefinition;

//Mapping that gets a process key from an external service and uses it to start an
//instance of a process definition.


@SuppressWarnings("rawtypes")
@GetMapping("/{processName}")
public MediaType.TEXT_XML_VALUE fetchProcessDocument(@PathVariable String processName){
	
	//Make rest call to service... flowProcess.getId needs switched.
	ResponseEntity doc = restTemplate.getForEntity("http://localhost:8081/processes/"+processName, MediaType.TEXT_XML_VALUE.getClass());
	
	OutputStream output = new FileOutputStream("c:\\data\\output-text.txt");
	Document data = (Document) doc.getBody();

	while(doc.getStatusCode().value() <= 400) {
	  int data = getMoreData();
	  output.write(data);
	}
	output.close();
	//take that information and use it to start an instance of that process
	//perhaps save process to project root then dispose of it when finished	
	
	return MediaType.TEXT_XML_VALUE;
}

@PostMapping(path="/newProcess", consumes= "application/xml" , produces="aplication/xml")
public void saveNewProcessDefinition() {
	//This mapping will accept new xml over http and stream it into a file
	//within the processes folder for future use.
}
}