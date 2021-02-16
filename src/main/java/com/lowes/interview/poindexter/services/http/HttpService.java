package com.lowes.interview.poindexter.services.http;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class HttpService {

    public String get(String urlStr) throws IOException {
        return getResponseString(urlStr);
    }

    private String getResponseString(String urlStr) throws IOException {
        URL url = new URL(urlStr);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.connect();
        return handleResponse(con);
    }

    private String handleResponse(HttpURLConnection con) throws IOException {
        int status = con.getResponseCode();

        if (status == 200) {
            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
            br.close();
            return sb.toString();
        } else {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "");
            return null;
        }
    }
}
