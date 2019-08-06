package com.abhinav.mobiAssign.model;

public class ReportResponse {

	private Integer adSpend;
	private Integer CTR;
	private Integer campaignId;
	private String campaignName;
	private Integer clicks;
	private Integer conversions;
	private Integer costPerClick;
	private String costPerConversion;
	private String date;
	private Integer impressions;
	
	
	public ReportResponse(Integer adSpend, Integer cTR, Integer campaignId, String campaignName, Integer clicks,
			Integer conversions, Integer costPerClick, String costPerConversion, String date, Integer impressions) {
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

	public Integer getAdSpend() {
		return adSpend;
	}
	
	public void setAdSpend(Integer adSpend) {
		this.adSpend = adSpend;
	}
	
	public Integer getCTR() {
		return CTR;
	}
	
	public void setCTR(Integer cTR) {
		CTR = cTR;
	}
	
	public Integer getCampaignId() {
		return campaignId;
	}
	
	public void setCampaignId(Integer campaignId) {
		this.campaignId = campaignId;
	}
	
	public String getCampaignName() {
		return campaignName;
	}
	
	public void setCampaignName(String campaignName) {
		this.campaignName = campaignName;
	}
	
	public Integer getClicks() {
		return clicks;
	}
	
	public void setClicks(Integer clicks) {
		this.clicks = clicks;
	}
	
	public Integer getConversions() {
		return conversions;
	}
	
	public void setConversions(Integer conversions) {
		this.conversions = conversions;
	}
	
	public Integer getCostPerClick() {
		return costPerClick;
	}
	
	public void setCostPerClick(Integer costPerClick) {
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
	
	public Integer getImpressions() {
		return impressions;
	}
	
	public void setImpressions(Integer impressions) {
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
