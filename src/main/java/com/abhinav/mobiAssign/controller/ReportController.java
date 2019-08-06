package com.abhinav.mobiAssign.controller;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.abhinav.mobiAssign.API.AdvertiserAPI;
import com.abhinav.mobiAssign.model.Report;
import com.abhinav.mobiAssign.model.ReportResponse;
import com.google.gson.Gson;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;

@Controller
public class ReportController {

	@GetMapping("/report")
	public String reportForm(Report report) {
		return "gen-rep";
	}

	@PostMapping("/generateReport")
	public String GenerateReport(@Valid Report report, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "gen-rep";
		}

		model.addAttribute("report", report);

		JSONObject jsonObj = new JSONObject();
		jsonObj.put("timeFrame", report.getTimeFrame());
		jsonObj.put("groupBy", new JSONArray(report.getGroupBy().split(",\\s*")));
		jsonObj.put("orderBy", new JSONArray(report.getOrderBy().split(",\\s*")));

		JSONObject reportRequest = new JSONObject();
		reportRequest.put("reportRequest", jsonObj);

		String getReportJson = reportRequest.toString();
		System.out.println("getReportJson : " + getReportJson);

		String resData = AdvertiserAPI.getAdvReport(getReportJson);

		System.out.println("resData : " + resData);

		JSONObject resJSON = new JSONObject(resData);
		Gson gson = new Gson();

		if (resJSON != null) {
			if (!resJSON.optBoolean("error")) {

				JSONArray resList = resJSON.getJSONArray("respList");
				List<ReportResponse> reportResponses = new ArrayList<>();

				if (resList != null) {
					for (int i = 0; i < resList.length(); i++) {
						JSONObject resObj = resList.getJSONObject(i);

						ReportResponse reportResponse = gson.fromJson(resObj.toString(), ReportResponse.class);
						reportResponses.add(reportResponse);
					}

					generateCSV(reportResponses);
					
				} else {
					System.out.println("No data recieved!");
				}

			}
		}
		
		return "gen-rep";
	}

	private void generateCSV(List<ReportResponse> reportResponses) {
		// name of generated csv 
		final String CSV_LOCATION = "Report.csv "; 

		try { 

			// Creating writer class to generate 
			// csv file 
			FileWriter writer = new FileWriter(CSV_LOCATION);

			// Create Mapping Strategy to arrange the  
			// column name in order 
			ColumnPositionMappingStrategy mappingStrategy= new ColumnPositionMappingStrategy(); 
			mappingStrategy.setType(ReportResponse.class); 

			// Arrange column name as provided in below array. 
			String[] columns = new String[] 
					{ "adSpend", "CTR", "campaignId", "campaignName", 
							"clicks", "conversions", "costPerClick", 
							"costPerConversion", "date", "impressions" };

			mappingStrategy.setColumnMapping(columns); 

			// Createing StatefulBeanToCsv object 
			StatefulBeanToCsvBuilder<ReportResponse> builder = new StatefulBeanToCsvBuilder(writer); 
			StatefulBeanToCsv beanWriter = builder.withMappingStrategy(mappingStrategy).build(); 

			// Write list to StatefulBeanToCsv object 
			beanWriter.write(reportResponses); 

			// closing the writer object 
			writer.close();
			
		} catch (Exception e) { 
			e.printStackTrace(); 
		} 
	}

}