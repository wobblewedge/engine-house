~~~Loan App Engine using Spring Boot, Flowable, JPA~~~

POST new application to "http://localhost:8080/api/bpm/loanApproval/process/startProcess in the shape of a json object containing:

	{
	"userId": "12345dd",
	"firstName": "Adam",
	"lastName": "Wagerman",
	"email": "bob@bobloblobslawblog.com",
	"address": "1 Massive Mansion",
	"age": 49,
	"loanAmount": 20000,
	"SSN": 124759755,
	"income": 30500,
	"creditScore": 800
	}

Other endpoints will be found in the com.flow.enginehouse.util file.

The applicant is used to start a simple flowable process that will determine the applicants eligibility using the credit score as a basis. Once the process concludes, the response returns with a few new properties tacked on:
	ID - the index in the db
	Approval - boolean representing the loan decision
	Process ID - Each deployment of a process definition (in this case, our process definition is "processes/loan-2-app.bpmn20.xml") is known as a process instance. Each instance is given a unique Id, which is listed here.
