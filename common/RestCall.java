package algo.navid.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RestCall {
	/*
	 * Complete the function below. Base query:
	 * https://jsonmock.hackerrank.com/api/stocks
	 */
	static void restCall(String firstDate, String lastDate) {
		try {
			URL url = new URL("https://jsonmock.hackerrank.com/api/stocks/?page=1");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

//			jsonConverterByJavax(firstDate, lastDate, url, conn);
			jsonConverterByJakson(firstDate, lastDate, url, conn);

		} catch (Exception ex) {
			System.out.println(ex.getMessage() + " - " + ex.toString());
		}

	}

	private static void jsonConverterByJakson(String firstDate, String lastDate, URL url, HttpURLConnection conn) throws IOException {
		InputStream is=conn.getInputStream();
		ObjectMapper om=new ObjectMapper();

//		String out=print(is);
//		System.out.println(out);

		JsonNode jn=om.readTree(is);
		JsonNode data=jn.get("data");
		Data[] datas=om.readValue(data.toString(),Data[].class);

		System.out.println(datas[0].getDate());

	}

	private static void jsonConverterByJavax(String firstDate, String lastDate, URL url, HttpURLConnection conn) throws IOException, ParseException {
		// JsonReader rdr = Json.createReader(br);
		// JsonReader rdr = Json.createReader(conn.getInputStream());
		JsonReader rdr = Json.createReader(url.openStream());

		JsonObject obj = rdr.readObject();
		JsonArray results = obj.getJsonArray("data");
		for (JsonObject result : results.getValuesAs(JsonObject.class)) {
			String date = result.getString("date");
			Date date1 = new SimpleDateFormat("d-MMM-yyyy").parse(date);
			Date first = new SimpleDateFormat("d-MMM-yyyy").parse(firstDate);
			Date last = new SimpleDateFormat("d-MMM-yyyy").parse(lastDate);

			if (date1.before(last) && date1.after(first)) {
				System.out.print(result.getString("date"));
				System.out.print(": ");
				System.out.println(result.getInt("open"));
				System.out.println("-----------");
			}
		}
	}

	public static void main(String[] args) {

		//restCall("1-January-2000", "11-January-2000");
		postCall();

	}

	private static void postCall() {
		try {
			URL url = new URL("https://jsonplaceholder.typicode.com/posts");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");
			String input = "{\"qty\":100,\"name\":\"iPad 4\"}";
			OutputStream os = conn.getOutputStream();
			os.write(input.getBytes());
			os.flush();
			System.out.println(conn.getResponseCode());
			BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));

			String output;
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				System.out.println(output);
			}

		}catch(Exception ex){
			System.out.println(ex.getMessage() + " - " + ex.toString());
		}
	}

	private static String print(InputStream is) throws IOException{

		byte[] b=new byte[2];
		StringBuilder sb=new StringBuilder();
		while(true){
			int i=is.read(b);
			if(i==-1){
				break;
			}else{
				sb.append(new String(b));
			}

		}

		System.out.println(sb.toString());
		return sb.toString();
	}
	private static class Data implements Serializable {
		private String date;
		private String open;
		private String close;
		private String high;
		private String low;

		public String getDate() {
			return date;
		}

		public void setDate(String date) {
			this.date = date;
		}

		public String getOpen() {
			return open;
		}

		public void setOpen(String open) {
			this.open = open;
		}

		public String getClose() {
			return close;
		}

		public void setClose(String close) {
			this.close = close;
		}

		public String getHigh() {
			return high;
		}

		public void setHigh(String high) {
			this.high = high;
		}

		public String getLow() {
			return low;
		}

		public void setLow(String low) {
			this.low = low;
		}

		public Data() {
			super();
			// TODO Auto-generated constructor stub
		}


	}
}

// You can query for the stock information using one of the following queries:
//
// https://jsonmock.hackerrank.com/api/stocks: This query returns all available
// stock information. The response is paginated, so you may need to query
// https://jsonmock.hackerrank.com/api/stocks/?page=pageNumber, where pageNumber
// is an integer that describes the page number to view (e.g., 1, 2, etc.).
// https://jsonmock.hackerrank.com/api/stocks/?key=value: This query returns all
// the results where the searched key exactly matches value. The response is
// paginated so you may need to query
// https://jsonmock.hackerrank.com/api/stocks/?key=value&page=pageNumber, where
// pageNumber is an integer that describes the page number you would like to
// view (e.g., 1, 2, etc.).
// https://jsonmock.hackerrank.com/api/stocks/search?key=value: This query
// returns all results where the searched key has values that contains value as
// a substring. The response is paginated so you may need to query
// https://jsonmock.hackerrank.com/api/stocks/search?key=value&page=pageNumber,
// where pageNumber is an integer that describes the page number to view (e.g.,
// 1, 2, etc.).
//
//
// All the queries return a JSON response with the following five fields:
//
// page: The current page.
// per_page: The maximum number of results per page.
// total: The total number of records found.
// total_pages: The total number of pages which must be queried to get all the
// results.
// data: An array of JSON objects that contain the stock information. The JSON
// contains the following five fields, each of which can be used as the key to
// query:
// date: A string that describes the date on which the stock information is
// provided. The date format is d-mmm-yyyy , where d describes a valid day of
// the month, mmm describes the complete name of the month (e.g. , January ,
// February , March , etc.), and yyyy describes the year. The date is in the
// range 5-January-2000 to 1-January-2014 inclusive. There may not be
// information for some of the dates, and the information is available for
// Monday through Friday only.
// open: A float value that describes the stock's open price on the given date.
// close: A float value that describes the stock's close price on the given
// date.
// high: A float value that describes the stock's highest price on the given
// date.
// low: A float value that describes the stock's lowest price on the given date.
//
//
// Function Description
//
//
//
// Complete the function openAndClosePrices in the editor below. It should print
// the available stock information for each stock within the given date range.
// The information printed should be three space separated values that describe
// the date, the open price, and the close price respectively. The order of the
// dates in the output does not matter and there is no return value.
//
//
//
// openAndClosePrices has the following parameters:
//
//
//
// firstDate: a string that represents the first date to report
//
// lastDate: a string that represents the last date to report
//
//
//
// Input Format For Custom Testing
// Input from stdin will be processed as follows and passed to the function.
//
//
//
// The first line contains a string, firstDate, that denotes the beginning of
// the period to report.
//
// The next line contains a string, endDate, that denotes the end of the period
// to report.
//
// Sample Case 0
// Sample Input
//
// 1-January-2000
// 11-January-2000
//
//
// Sample Output
//
// 5-January-2000 5265.09 5357.0
// 6-January-2000 5424.21 5421.53
// 7-January-2000 5358.28 5414.48
// 11-January-2000 5513.04 5296.3
//
//
// Explanation
//
// For the date range 1-January-2000 to 11-January-2000 stock information is
// available for the following four dates:
//
// [
// {
// "date": "5-January-2000",
// "open": 5265.09,
// "high": 5464.35,
// "low": 5184.48,
// "close": 5357.0
// },
// {
// "date": "6-January-2000",
// "open": 5424.21,
// "high": 5489.86,
// "low": 5391.33,
// "close": 5421.53
// },
// {
// "date": "7-January-2000",
// "open": 5358.28,
// "high": 5463.25,
// "low": 5330.58,
// "close": 5414.48
// },
// {
// "date": "11-January-2000",
// "open": 5513.04,
// "high": 5537.69,
// "low": 5221.28,
// "close": 5296.3
// }
// ]