package com.mastercard.fld.utility;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.logging.Logger;

import okhttp3.Response;

public class LoggerUtil {

	private static final Logger logger = Logger.getLogger(LoggerUtil.class.getName());
	
	private LoggerUtil() {
	}

	public static BufferedWriter getWriter() throws IOException {
		FileWriter fw = new FileWriter("confirmed-fraud-api-output-" + LocalDate.now() + ".log"); //NOSONAR
		return new BufferedWriter(fw);
	}

	public static void logResponse(String url, String method, Response response) {
		BufferedWriter bw;
		try {
			bw = getWriter();
			bw.write("Sending Request: [" + method + "] " + url);
			bw.newLine();
			bw.newLine();
			bw.write("Response: " + response.code() + " " + response.body().string());
            bw.newLine();
			bw.newLine();
			bw.close();
		} catch (IOException e) {
			logger.info(e.getMessage());
		}
	}
}
