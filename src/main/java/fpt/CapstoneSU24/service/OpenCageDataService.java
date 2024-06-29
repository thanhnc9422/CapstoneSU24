package fpt.CapstoneSU24.service;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URLEncoder;

@Service
public class OpenCageDataService {
    @Value("${key.open.cage.data}")
    private String apiKey;
    @Value("${url.open.cage.data}")
    private String urlVerify;
    public JSONArray verifyLocation(String address) {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            String url = String.format("%s/geocode/v1/json?q=%s&key=%s&limit=1", urlVerify, URLEncoder.encode(address, "UTF-8"), apiKey);
            HttpGet httpGet = new HttpGet(url);
            System.out.println(url + " hehe");
            httpGet.addHeader("Content-Type", "application/json");
            HttpResponse response = httpClient.execute(httpGet);
            String responseBody = EntityUtils.toString(response.getEntity());
            JSONObject jsonObject = new JSONObject(responseBody);
            JSONArray jsonResults = jsonObject.getJSONArray("results");
            JSONArray jsonArrayResponse = new JSONArray();

            for (Object i : jsonResults) {
                JSONObject jsonResponse = new JSONObject();
                JSONObject element = (JSONObject) i;
                try {
                    jsonResponse.put("geometry", element.getJSONObject("geometry"));
                    jsonResponse.put("formatted", element.getString("formatted"));
                } catch (Exception e) {
                    continue;
                }
                jsonArrayResponse.put(jsonResponse);
            }
            return jsonArrayResponse;

        } catch (IOException e) {
            System.out.println("Error code when fetching api");
            throw new RuntimeException(e);
        }
    }
    public JSONArray verifyCoordinates(double lat, double lng) {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            String url = String.format("%s/geocode/v1/json?key=%s&q=%s+%s&prety=1&limit=1", urlVerify, apiKey,lat, lng);
            HttpGet httpGet = new HttpGet(url);
            httpGet.addHeader("Content-Type", "application/json");
            HttpResponse response = httpClient.execute(httpGet);
            String responseBody = EntityUtils.toString(response.getEntity());
            JSONObject jsonObject = new JSONObject(responseBody);
            JSONArray jsonResults = jsonObject.getJSONArray("results");
            JSONArray jsonArrayResponse = new JSONArray();

            for (Object i : jsonResults) {
                JSONObject jsonResponse = new JSONObject();
                JSONObject element = (JSONObject) i;
                try {
                    jsonResponse.put("geometry", element.getJSONObject("geometry"));
                    jsonResponse.put("formatted", element.getString("formatted"));
                } catch (Exception e) {
                    continue;
                }
                jsonArrayResponse.put(jsonResponse);
            }
            return jsonArrayResponse;

        } catch (IOException e) {
            System.out.println("Error code when fetching api");
            throw new RuntimeException(e);
        }
    }
}
