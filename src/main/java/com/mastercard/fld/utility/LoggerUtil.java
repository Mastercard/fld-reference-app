package com.mastercard.fld.utility;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.logging.Logger;

import com.google.gson.Gson;
import com.mastercard.fld.api.fld.ApiResponse;
import com.mastercard.fld.api.fld.model.Fraud;

public class LoggerUtil {

	private static final Logger logger = Logger.getLogger(LoggerUtil.class.getName());
	
	private LoggerUtil() {
	}

	public static BufferedWriter getWriter() throws IOException {
		FileWriter fw = new FileWriter("confirmed-fraud-api-output-" + LocalDate.now() + ".log"); //NOSONAR
		return new BufferedWriter(fw);
	}

	public static void logResponse(String url, String method, ApiResponse<Fraud> response) {
		BufferedWriter bw;
		Gson gson = new Gson();
		try {
			bw = getWriter();
			bw.write("Sending Request: [" + method + "] " + url);
			bw.newLine();
			bw.newLine();
			bw.write("Response: " + response.getStatusCode() + " " + gson.toJson(response.getData()));
			bw.newLine();
			bw.newLine();
			bw.close();
		} catch (IOException e) {
			logger.info(e.getMessage());
		}
	}
}
