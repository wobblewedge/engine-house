package com.flow.enginehouse.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class ApplicationProcess {
@Column
@GeneratedValue(strategy = GenerationType.AUTO)
@Id
private String processId;
@OneToMany
@Column
private String loanDecision;
@Column
private double apr;
@Column
private String taskId;
@Column
private String loanDecisionBy;
@Column
private String loanDecisionDate;
@Column
private String comments;

public ApplicationProcess() {}
public ApplicationProcess(String processId) {
	this.processId = processId;
};
public String getProcessId() {
	return processId;
}
public void setProcessId(String processId) {
	this.processId = processId;
}
public String getLoanDecision() {
	return loanDecision;
}
public void setLoanDecision(String loanDecision) {
	this.loanDecision = loanDecision;
}
public double getApr() {
	return apr;
}
public void setApr(double apr) {
	this.apr = apr;
}
public String getTaskId() {
	return taskId;
}
public void setTaskId(String taskId) {
	this.taskId = taskId;
}
public String getLoanDecisionBy() {
	return loanDecisionBy;
}
public void setLoanDecisionBy(String loanDecisionBy) {
	this.loanDecisionBy = loanDecisionBy;
}
public String getLoanDecisionDate() {
	return loanDecisionDate;
}
public void setLoanDecisionDate(String loanDecisionDate) {
	this.loanDecisionDate = loanDecisionDate;
}
public String getComments() {
	return comments;
}
public void setComments(String comments) {
	this.comments = comments;
}

}
