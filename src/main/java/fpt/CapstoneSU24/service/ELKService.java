package fpt.CapstoneSU24.service;

import com.tdunning.math.stats.Histogram;
import fpt.CapstoneSU24.dto.payload.SelectedTimeRequest;
import io.swagger.v3.core.util.Json;
import io.swagger.v3.core.util.Json31;
import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.BucketOrder;
import org.elasticsearch.search.aggregations.bucket.histogram.DateHistogramInterval;
import org.elasticsearch.search.aggregations.metrics.ValueCount;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class ELKService {
    RestHighLevelClient client = new RestHighLevelClient(
            RestClient.builder(
                    new HttpHost("34.150.104.5", 9200, "http")
            )
    );
    public ResponseEntity<?> getNumberVisitsAllTime() throws IOException {
        try {
            SearchRequest searchRequest = new SearchRequest("logs-generic-default");
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            searchSourceBuilder.query(QueryBuilders.matchQuery("message", "itemviewLineItem"));
            searchSourceBuilder.size(0);
            searchRequest.source(searchSourceBuilder);

            SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
            return ResponseEntity.status(200).body(searchResponse.getHits().getTotalHits().value);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.toString());
        }
    }
    public ResponseEntity<?> getHistoryUploadAI() throws IOException {
        try {
            SearchRequest searchRequest = new SearchRequest("logs-generic-default");
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            searchSourceBuilder.query(QueryBuilders.matchPhrasePrefixQuery("message", "uploadFileAISuccessful"));
//            searchSourceBuilder.size(0);
            searchSourceBuilder.fetchSource(new String[] {"@timestamp", "message"}, null);
            searchRequest.source(searchSourceBuilder);

            SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
            JSONObject jsonObject1 = new JSONObject(searchResponse.toString());

            SearchHits hits = searchResponse.getHits();
            JSONArray newJsonArray = new JSONArray();

            for (SearchHit hit : hits) {
                Map<String, Object> sourceAsMap = hit.getSourceAsMap();
                String timestamp = (String) sourceAsMap.get("@timestamp");
                String message = (String) sourceAsMap.get("message");

               JSONObject jsonObject = new JSONObject();
                jsonObject.put("timestamp", timestamp);
                jsonObject.put("message", message);

                newJsonArray.put(jsonObject);
            }

            return ResponseEntity.status(200).body(newJsonArray.toString());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.toString());
        }
    }
/* now-15m
*  now/h
*  now/d
*  now/w
*  now/m
* */
    public ResponseEntity<?> getNumberVisitsDiagram(SelectedTimeRequest req) throws IOException {
        SearchRequest searchRequest = new SearchRequest();
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder()
                .size(0)
                .query(QueryBuilders.boolQuery()
                        .filter(QueryBuilders.rangeQuery("@timestamp")
                                .gte(req.getType())
                                .lte("now")
                                .timeZone("GMT+7"))
                        .filter(QueryBuilders.matchQuery("message", "itemviewLineItem"))
                )
                .aggregation(AggregationBuilders.dateHistogram("hourly_counts")
                        .field("@timestamp")
                        .fixedInterval(req.getType().equals("now-15m")? new DateHistogramInterval("15m"): req.getType().equals("now/h")?  DateHistogramInterval.MINUTE: req.getType().equals("now/d")? DateHistogramInterval.HOUR : DateHistogramInterval.DAY )
                        .timeZone(ZoneId.of("GMT+7"))
                );

        searchRequest.indices("logs-generic-default").source(sourceBuilder);
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        JSONObject jsonObject = new JSONObject(searchResponse.toString());

        JSONObject aggregations = jsonObject.getJSONObject("aggregations");
        JSONObject dateHistogram = aggregations.getJSONObject("date_histogram#hourly_counts");
        JSONArray buckets = dateHistogram.getJSONArray("buckets");
        //convert date
        JSONArray newJsonArray = new JSONArray();
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        SimpleDateFormat outputFormat = new SimpleDateFormat("HH:mm");
//        req.getType().equals("15m") ? "now-15m" : req.getType().equals("1h")? "now/h" : "HH:mm"
        for (int i = 0; i < buckets.length(); i++) {
            JSONObject j = buckets.getJSONObject(i);
            String keyAsString = j.getString("key_as_string");
            try {
                Date date = inputFormat.parse(keyAsString);
                String formattedDate = outputFormat.format(date);

                JSONObject newJsonObject = new JSONObject();
                newJsonObject.put("formatted_date", formattedDate);
                newJsonObject.put("doc_count", j.getInt("doc_count"));
                newJsonObject.put("key", j.getLong("key"));

                newJsonArray.put(newJsonObject);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return ResponseEntity.status(200).body(newJsonArray.toString());
    }

    public int getNumberTraceAllTime() throws IOException {
        try {
            SearchRequest searchRequest = new SearchRequest("logs-generic-default");
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            searchSourceBuilder.query(QueryBuilders.termQuery("message", "itemviewLineItem"));
            searchSourceBuilder.size(0);
            searchRequest.source(searchSourceBuilder);

            SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
            return (int) searchResponse.getHits().getTotalHits().value;
        } catch (Exception e) {
            return 0;
        }
    }

    public ResponseEntity<?> getNumberTraceDiagram() throws IOException {
        SearchRequest searchRequest = new SearchRequest();
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder()
                .size(0)
                .query(QueryBuilders.boolQuery()
                        .filter(QueryBuilders.rangeQuery("@timestamp")
                                .gte("now/d")
                                .lte("now")
                                .timeZone("GMT+7"))
                        .filter(QueryBuilders.prefixQuery("message", "item"))
                )
                .aggregation(AggregationBuilders.dateHistogram("hourly_counts")
                        .field("@timestamp")
                        .fixedInterval(DateHistogramInterval.HOUR)
                        .timeZone(ZoneId.of("GMT+7"))
                );

        searchRequest.indices("logs-generic-default").source(sourceBuilder);
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        JSONObject jsonObject = new JSONObject(searchResponse.toString());

        JSONObject aggregations = jsonObject.getJSONObject("aggregations");
        JSONObject dateHistogram = aggregations.getJSONObject("date_histogram#hourly_counts");
        JSONArray buckets = dateHistogram.getJSONArray("buckets");
        //convert date
        JSONArray newJsonArray = new JSONArray();
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        SimpleDateFormat outputFormat = new SimpleDateFormat("HH:mm");

        for (int i = 0; i < buckets.length(); i++) {
            JSONObject j = buckets.getJSONObject(i);
            String keyAsString = j.getString("key_as_string");
            try {
                Date date = inputFormat.parse(keyAsString);
                String formattedDate = outputFormat.format(date);

                JSONObject newJsonObject = new JSONObject();
                newJsonObject.put("formatted_date", formattedDate);
                newJsonObject.put("doc_count", j.getInt("doc_count"));
                newJsonObject.put("key", j.getLong("key"));

                newJsonArray.put(newJsonObject);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return ResponseEntity.status(200).body(newJsonArray.toString());
    }

    public int getNumberTransport(int transId) {
        try {
            SearchRequest searchRequest = new SearchRequest("logs-generic-default");
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            searchSourceBuilder.query(QueryBuilders.matchQuery("message", "transport"+transId));
            searchSourceBuilder.size(0);
            searchRequest.source(searchSourceBuilder);

            SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
            return (int) searchResponse.getHits().getTotalHits().value;
        } catch (Exception e) {
            return 0;
        }
    }
}
