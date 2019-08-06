package com.abhinav.mobiAssign.model;


// POJO class to map the returned response from the advertiser API
public class ReportResponse {

	private double adSpend;
	private double CTR;
	private String campaignId;
	private String campaignName;
	private int clicks;
	private int conversions;
	private double costPerClick;
	private String costPerConversion;
	private String date;
	private int impressions;
	
	public ReportResponse(double adSpend, double cTR, String campaignId, String campaignName, int clicks,
			int conversions, double costPerClick, String costPerConversion, String date, int impressions) {
		super();
		this.adSpend = adSpend;
		CTR = cTR;
		this.campaignId = campaignId;
		this.campaignName = campaignName;
		this.clicks = clicks;
		this.conversions = conversions;
		this.costPerClick = costPerClick;
		this.costPerConversion = costPerConversion;
		this.date = date;
		this.impressions = impressions;
	}

	public double getAdSpend() {
		return adSpend;
	}

	public void setAdSpend(double adSpend) {
		this.adSpend = adSpend;
	}

	public double getCTR() {
		return CTR;
	}

	public void setCTR(double cTR) {
		CTR = cTR;
	}

	public String getCampaignId() {
		return campaignId;
	}

	public void setCampaignId(String campaignId) {
		this.campaignId = campaignId;
	}

	public String getCampaignName() {
		return campaignName;
	}

	public void setCampaignName(String campaignName) {
		this.campaignName = campaignName;
	}

	public int getClicks() {
		return clicks;
	}

	public void setClicks(int clicks) {
		this.clicks = clicks;
	}

	public int getConversions() {
		return conversions;
	}

	public void setConversions(int conversions) {
		this.conversions = conversions;
	}

	public double getCostPerClick() {
		return costPerClick;
	}

	public void setCostPerClick(double costPerClick) {
		this.costPerClick = costPerClick;
	}

	public String getCostPerConversion() {
		return costPerConversion;
	}

	public void setCostPerConversion(String costPerConversion) {
		this.costPerConversion = costPerConversion;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getImpressions() {
		return impressions;
	}

	public void setImpressions(int impressions) {
		this.impressions = impressions;
	}

	@Override
	public String toString() {
		return "ReportResponse [adSpend=" + adSpend + ", CTR=" + CTR + ", campaignId=" + campaignId + ", campaignName="
				+ campaignName + ", clicks=" + clicks + ", conversions=" + conversions + ", costPerClick="
				+ costPerClick + ", costPerConversion=" + costPerConversion + ", date=" + date + ", impressions="
				+ impressions + "]";
	}
}
