package com.example.validation.downloader;

import com.example.validation.entity.Context;
import com.example.validation.entity.Request;
import com.example.validation.entity.Response;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

;

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


        try (CloseableHttpResponse httpResponse = httpClient.execute(uriRequest)) {

            Header encodingHeader = httpResponse.getEntity().getContentEncoding();
            String charset = encodingHeader != null ? encodingHeader.getValue() : "";

            return Response.builder()
                    .bytes(EntityUtils.toByteArray(httpResponse.getEntity()))
                    .content((InputStream) BeanUtils.cloneBean(httpResponse.getEntity().getContent()))
                    .text(EntityUtils.toString(httpResponse.getEntity()))
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
        } catch (IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException | IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Response download(Request request) {
        return download(request, null);
    }


    private CloseableHttpClient getHttpClient(boolean useProxy) {
        HttpClientBuilder clientBuilder = HttpClients.custom();

        if (useProxy) {
            clientBuilder.setProxy(getProxy());
        }


        return clientBuilder.build();

    }

    private HttpHost getProxy() {
        return null;
    }
}
