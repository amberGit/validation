package com.example.validation.entity;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Amber [johnnyven@outlook.com]
 * @since 2016-11-30 0:09
 */
@Data
public class Request {
    @java.beans.ConstructorProperties({"method", "url", "params", "headers"})
    Request(HttpMethod method, String url, Map<String, String> params, Map<String, String> headers) {
        this.method = method;
        this.url = url;
        this.params = params;
        this.headers = headers;
    }

    public static RequestBuilder builder() {
        return new RequestBuilder();
    }

    public enum HttpMethod {
        GET,
        POST,
        PUT,
        DELETE;

    }

    private HttpMethod method;
    private String url;
    private Map<String, String> params;
    private Map<String, String > headers;

    public static class RequestBuilder {
        private HttpMethod method;
        private String url;
        private Map<String, String> params  = new HashMap<>();
        private Map<String, String> headers = new HashMap<>();

        RequestBuilder() {
        }

        public Request.RequestBuilder method(HttpMethod method) {
            this.method = method;
            return this;
        }

        public Request.RequestBuilder url(String url) {
            this.url = url;
            return this;
        }

        public Request.RequestBuilder params(Map<String, String> params) {
            this.params = params;
            return this;
        }

        public Request.RequestBuilder addParameter(String key, String value) {
            this.params.put(key, value);
            return this;
        }

        public Request.RequestBuilder headers(Map<String, String> headers) {
            this.headers = headers;
            return this;
        }

        public Request.RequestBuilder addHeader(String key, String value) {
            this.headers.put(key, value);
            return this;
        }

        public Request build() {
            return new Request(method, url, params, headers);
        }

        public String toString() {
            return "com.example.validation.entity.Request.RequestBuilder(method=" + this.method + ", url=" + this.url + ", params=" + this.params + ", headers=" + this.headers + ")";
        }
    }
}
