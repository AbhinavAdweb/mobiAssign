package com.abhinav.mobiAssign.controller;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Date;
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
import com.abhinav.mobiAssign.model.ResponseError;
import com.google.gson.Gson;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;

@Controller
public class ReportController {

	// This will load the Index UI
	@GetMapping("/")
	public String reportIndex(Report report) {
		return "index";
	}

	// This will load the UI where report request items will be entered
	@GetMapping("/report")
	public String reportForm(Report report) {
		return "gen-rep";
	}

	// the form on gen-rep page will fire this method
	@PostMapping("/generateReport")
	public String GenerateReport(@Valid Report report, BindingResult result, Model model) {
		// Error should take us back to the request UI
		if (result.hasErrors()) {
			return "gen-rep";
		}

		JSONObject jsonObj = new JSONObject();
		jsonObj.put("timeFrame", report.getTimeFrame());
		jsonObj.put("groupBy", new JSONArray(report.getGroupBy().split(",\\s*")));
		jsonObj.put("orderBy", new JSONArray(report.getOrderBy().split(",\\s*")));

		JSONObject reportRequest = new JSONObject();

		// Create the report request JSON object with the data entered in the form
		reportRequest.put("reportRequest", jsonObj);

		String getReportJson = reportRequest.toString();
		System.out.println("getReportJson : " + getReportJson);

		// Get the Advertiser API response JSON string for further processing
		String resData = AdvertiserAPI.getAdvReport(getReportJson);

		System.out.println("resData : " + resData);

		// Convert the response string to the JSON object to further use for generating CSV
		JSONObject resJSON = new JSONObject(resData);
		Gson gson = new Gson();

		// This list will hold the mapped JSON response data of the API to use for generating CSV
		List<ReportResponse> reportResponses = new ArrayList<>();

		// Add test data to report response for demo
		ReportResponse r1 = new ReportResponse(1.033, 6.25, "abc", "campaign-1", 1, 0, 0.03, "0.00", "2012-05-01 00:00:00", 16);
		reportResponses.add(r1);

		ReportResponse r2 = new ReportResponse(0.033, 6.25, "xyz", "campaign-1", 1, 0, 0.03, "0.00", "2012-05-01 00:00:00", 16);
		reportResponses.add(r2);
		// Generate test data CSV
		generateCSV(reportResponses, "test");

		if (resJSON != null) {
			if (!resJSON.optBoolean("error")) {

				JSONArray resList = resJSON.optJSONArray("respList");

				if (resList != null) {
					for (int i = 0; i < resList.length(); i++) {
						JSONObject resObj = resList.getJSONObject(i);

						// Using google gson convert the JSON string to the POJO class ReportResponse
						ReportResponse reportResponse = gson.fromJson(resObj.toString(), ReportResponse.class);
						reportResponses.add(reportResponse);
					}

					if (!reportResponses.isEmpty())
						generateCSV(reportResponses, "ReportAt_" + new Date().getTime());

				} else {
					System.out.println("No data recieved!");
				}

			} else if (resJSON.optJSONArray("errorList") != null) {

				List<ResponseError> errors = new ArrayList<>();
				JSONArray errorList = resJSON.optJSONArray("errorList");

				for (int j = 0; j < errorList.length(); j++) {
					JSONObject errObj = errorList.getJSONObject(j);

					// Using google gson convert the JSON string to the POJO class ResponseError
					ResponseError error = gson.fromJson(errObj.toString(), ResponseError.class);
					errors.add(error);
				}

				model.addAttribute("errorList", errors);
				return "resError";
			}
		}

		// Add this to the model attribute to display the generated data on the index page
		if (!reportResponses.isEmpty()) {
			model.addAttribute("reportResponses", reportResponses);
		}

		return "index";
	}

	private void generateCSV(List<ReportResponse> reportResponses, String fileName) {

		// name of generated csv (will be created at the top level of the project folder
		final String CSV_LOCATION = fileName + ".csv "; 

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