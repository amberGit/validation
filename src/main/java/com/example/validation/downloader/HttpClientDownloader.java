package com.example.validation.downloader;

import com.example.validation.entity.Context;
import com.example.validation.entity.Request;
import com.example.validation.entity.Response;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * @author Amber [johnnyven@outlook.com]
 * @since 2016-11-30 0:27
 */
public class HttpClientDownloader implements Downloader {
    @Override
    public Response download(Request request, Context context) {
        CloseableHttpClient httpClient = getHttpClient(false);
        RequestBuilder requestBuilder = RequestBuilder.create(request.getMethod().name())
                .setUri(request.getUrl());

        request.getHeaders()
                .entrySet()
                .forEach(header -> requestBuilder.addHeader(header.getKey(), header.getValue()));

        request.getParams()
                .entrySet()
                .forEach(param -> requestBuilder.addParameter(param.getKey(), param.getValue()));

        HttpUriRequest uriRequest = requestBuilder.build();


        Response response = null;
        try (CloseableHttpResponse httpResponse = httpClient.execute(uriRequest)) {
            HttpEntity entity = httpResponse.getEntity();
            Header encodingHeader = entity.getContentEncoding();
            String charset = encodingHeader != null ? encodingHeader.getValue() : "";

            response = Response.builder()
                    .bytes(EntityUtils.toByteArray(entity))
//                    .content((InputStream) BeanUtils.cloneBean(entity.getContent()))
//                    .content(entity.getContent())
//                    .text(EntityUtils.toString(entity))
                    .encoding(charset)
                    .headers(
                            Stream.of(httpResponse.getAllHeaders())
                                .collect(Collectors.toMap(Header::getName, Header::getValue, (s, s2) -> s, HashMap::new))
                    ).setCookies(
                            Stream.of(httpResponse.getHeaders("set-cookie"))
                                .collect(Collectors.toMap(Header::getName, Header::getValue, (s, s2) -> s, HashMap::new))
                    ).statusCode(httpResponse.getStatusLine().getStatusCode())
                    .request(request)
                    .build();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return response;
    }

    @Override
    public Response download(Request request) {
        return download(request, null);
    }


    private CloseableHttpClient getHttpClient(boolean useProxy) {
//        HttpClientBuilder clientBuilder = HttpClients.custom();
//
//        if (useProxy) {
//            clientBuilder.setProxy(getProxy());
//        }
//
//
//        return clientBuilder.build();

        return HttpClients.createDefault();

    }

    private HttpHost getProxy() {
        return null;
    }
}
