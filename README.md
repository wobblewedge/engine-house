~~~Loan App Engine using Spring Boot, Flowable, JPA~~~

Endpoints found at com.flow.enginehouse.util.Constants

Accepts a json object in the form of ApplicantDto :

{
	"firstName": "first",
	"lastName":  "last",
	"userId":	   0,
	"SSN":	   013549999,
	"address":   "1 Raspberry Lane",
	"age":	   55,
	"income":	   44000,
	"loanAmount": 19000,
	"creditScore": 900,
	"email":	"bob@bob.com"
	}
	
	And uses that to start a process which invokes java code that decides upon the initial status of a loan.
