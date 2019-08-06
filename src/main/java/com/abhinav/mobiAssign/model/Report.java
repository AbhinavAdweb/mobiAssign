package com.abhinav.mobiAssign.model;

public class Report {

	private String timeFrame;
	private String groupBy;
	private String orderBy;

	public Report() {

	}

	public Report(String timeFrame, String groupBy, String orderBy) {
		super();
		this.timeFrame = timeFrame;
		this.groupBy = groupBy;
		this.orderBy = orderBy;
	}

	public String getTimeFrame() {
		return timeFrame;
	}

	public void setTimeFrame(String timeFrame) {
		this.timeFrame = timeFrame;
	}

	public String getGroupBy() {
		return groupBy;
	}

	public void setGroupBy(String groupBy) {
		this.groupBy = groupBy;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	@Override
	public String toString() {
		return "Report [timeFrame=" + timeFrame + ", groupBy=" + groupBy + ", orderBy=" + orderBy + "]";
	}
}
