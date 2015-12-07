package local.openweather.util;

import android.net.Uri;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;

/**
 * Created by brian on 10/18/2015.
 */
public class RestClient {

    private final String LOG_TAG = "Open_RestClient";

    private String BASE_URL;
    private ArrayList<KeyValue> mHeaders;
    private ArrayList<KeyValue> mParams;

    private int mResponseCode;
    private String mResponse;
    private String mResponseMessage;

    public RestClient() {
        mHeaders = new ArrayList<KeyValue>();
        mParams = new ArrayList<KeyValue>();
    }

    public void getStringFromUrl() {

        HttpURLConnection localConnection = null;
        try {
            //create URL
            String url = createUrl();

            LogUtils.LOGD(String.format("Url Created %s", url));

            //open create the data
            localConnection = (HttpURLConnection) new URL(url).openConnection();
            localConnection.setInstanceFollowRedirects(true);
            localConnection.setDoInput(true);
            localConnection.setDoOutput(true);

            //open connection
            localConnection.connect();

            mResponseCode = localConnection.getResponseCode();
            mResponseMessage = localConnection.getResponseMessage();
            //convert stream to string
            mResponse = convertStreamToString(localConnection.getInputStream());

            LogUtils.LOGE(mResponse);
        } catch (URISyntaxException localURIException) {
            LogUtils.LOGE(LOG_TAG, "Error Creating Url from data", localURIException);
        } catch (MalformedURLException localMalformedException) {
            LogUtils.LOGE(LOG_TAG, "Error Malformed url for connection", localMalformedException);
        } catch (UnknownHostException ex) {
            LogUtils.LOGE(LOG_TAG, "Host Unknown", ex);
        } catch (IOException localIOException) {
            LogUtils.LOGE(LOG_TAG, "Error Input Exception", localIOException);
        } finally {
            if (localConnection != null) {
                try {
                    localConnection.disconnect();
                } catch (Throwable t) {
                }
            }
        }
    }

    private String createUrl() throws URISyntaxException {
        if (BASE_URL == null) {
            throw new URISyntaxException("The Base URL not Defined",
                    "Please Set up new Base Url via setBaseUrl(String url)");
        }

        Uri.Builder localUri = Uri.parse(this.BASE_URL).buildUpon();

        //params set, add to the url
        if (mParams != null) {
            for (KeyValue localKeyValue : mParams) {
                localUri.appendQueryParameter(localKeyValue.key, localKeyValue.value);
            }
        }

        return localUri.build().toString();
    }

    public void addHeader(String paramString1, String paramString2) {
        mHeaders.add(new KeyValue(paramString1, paramString2));
    }

    public void addParam(String paramString1, String paramString2) {
        mParams.add(new KeyValue(paramString1, paramString2));
    }

    public void resetRestClient() {
        this.setBaseUrl(null);
        mParams = new ArrayList<>();
        mHeaders = new ArrayList<>();
    }

    public void setBaseUrl(String paramString) {
        this.BASE_URL = paramString;
    }

    public int getResponseCode() {
        return this.mResponseCode;
    }

    public String getResponseMessage() {
        return this.mResponseMessage;
    }

    public String getResponse() {
        return this.mResponse;
    }

    private String convertStreamToString(InputStream paramInputStream)
            throws IOException {
        BufferedReader localStreamReader = new BufferedReader(new InputStreamReader(paramInputStream));
        StringBuffer localBuffer = new StringBuffer();

        String line;

        for (; ; ) {
            line = localStreamReader.readLine();

            if (line == null) {
                break;
            }

            localBuffer.append(String.format("%s\n", line));
        }

        return localBuffer.toString();
    }

    private class KeyValue {
        public String key;
        public String value;

        public KeyValue(String paramString1, String paramString2) {
            this.key = paramString1;
            this.value = paramString2;
        }
    }
}
