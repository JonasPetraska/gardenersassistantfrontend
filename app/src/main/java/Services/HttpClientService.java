package Services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import Models.HttpResult;
import Models.Owner;

public class HttpClientService<T, V> implements IHttpClientService<T, V>
{
    public static String AccessToken = "";
    private final String USER_AGENT = "Mozilla/5.0";


    @Override
    public HttpResult<T> Get(String url) {
        try
        {
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            // optional default is GET
            con.setRequestMethod("GET");

            //add request header
            con.setRequestProperty("User-Agent", USER_AGENT);

            int responseCode = con.getResponseCode();
            System.out.println("\nSending 'GET' request to URL : " + url);
            System.out.println("Response Code : " + responseCode);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            if(responseCode != HttpURLConnection.HTTP_OK)
            {
                return new HttpResult<T>(Integer.toString(responseCode) + ": " + response.toString());
            }

            Gson gson = new GsonBuilder().create();
            T deserialized = gson.fromJson(response.toString(), new TypeToken<T>(){}.getType());
            return new HttpResult<T>(deserialized);
        }
        catch(Exception ex)
        {
            return new HttpResult<T>(ex);
        }
    }

    @Override
    public HttpResult<T> Post(String url, V obj) {
        try
        {
            URL objs = new URL(url);
            HttpURLConnection con = (HttpURLConnection) objs.openConnection();
            Gson gson = new GsonBuilder().create();

            //add reuqest header
            con.setRequestMethod("POST");
            con.setRequestProperty("User-Agent", USER_AGENT);
            con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
            con.setRequestProperty("Content-Type", "application/json");
            con.setDoOutput(true); //allow parameters to be sent/appended

            // Send post request
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(gson.toJson(obj));
            wr.flush();
            wr.close();

            int responseCode = con.getResponseCode();
            System.out.println("\nSending 'POST' request to URL : " + url);
            System.out.println("Post parameters : " + obj.toString());
            System.out.println("Response Code : " + responseCode);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            if(responseCode != HttpURLConnection.HTTP_OK)
            {
                return new HttpResult<T>(Integer.toString(responseCode) + ": " + response.toString());
            }

            T deserialized = gson.fromJson(response.toString(), new TypeToken<T>(){}.getType());
            return new HttpResult<T>(deserialized);

        }catch(Exception ex)
        {
            return new HttpResult<T>(ex);
        }
    }

    @Override
    public HttpResult<T> Put(String url, V obj) {
        return null;
    }

    @Override
    public HttpResult<T> Delete(String url) {
        return null;
    }
}
