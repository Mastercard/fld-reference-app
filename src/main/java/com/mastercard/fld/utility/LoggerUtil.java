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

    public static BufferedWriter getWriter(String api) throws IOException {

        FileWriter fw = new FileWriter(api + "-fraud-api-output-" + LocalDate.now() + ".log"); // NOSONAR
        return new BufferedWriter(fw);
    }

    public static void logResponse(String api, String url, String method, Response response) {

        BufferedWriter bw;
        try {
            bw = getWriter(api);
            bw.write("Sending Request: [" + method + "] " + url);
            bw.newLine();
            bw.newLine();
            bw.write("\tHttp Response - ");
            bw.write("\t" + response.code());
            bw.newLine();
            bw.write("\tLocation Header - " + response.header("Location", "Not present"));
            bw.newLine();
            bw.write("\tBody - " + response.body().string());
            bw.close();
        } catch (IOException e) {
            logger.info(e.getMessage());
        }
    }

    public static void logResponse(String api, String url, String method, int httpCode, String locationHeader, String body) {

        BufferedWriter bw;
        try {
            bw = getWriter(api);
            bw.write("Sending Request: [" + method + "] " + url);
            bw.newLine();
            bw.newLine();
            bw.write("\tHttp Response - ");
            bw.write("\t" + httpCode);
            bw.newLine();
            bw.write("\tLocation Header - " + locationHeader);
            bw.newLine();
            bw.write("\tBody - " + body);
            bw.close();
        } catch (IOException e) {
            logger.info(e.getMessage());
        }
    }
}
