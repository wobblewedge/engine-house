package com.flow.enginehouse.dto;

import javax.persistence.Column;
import javax.persistence.OneToMany;

public class ApplicationProcessDto {
	String processId;
    String loanDecision;
	Double apr;
	String taskId;
	String loanDecisionBy;
	String loanDecisionDate;
	String comments;
	
	public ApplicationProcessDto() {}
	
	public ApplicationProcessDto(String processId, String loanDecision, Double apr, String taskId,
			String loanDecisionBy, String loanDecisionDate, String comments) {
		super();
		this.processId = processId;
		this.loanDecision = loanDecision;
		this.apr = apr;
		this.taskId = taskId;
		this.loanDecisionBy = loanDecisionBy;
		this.loanDecisionDate = loanDecisionDate;
		this.comments = comments;
	}
	
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
	public Double getApr() {
		return apr;
	}
	public void setApr(Double apr) {
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
