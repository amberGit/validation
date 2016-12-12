package com.example.validation.entity;

import lombok.Data;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

;

/**
 * @author Amber [johnnyven@outlook.com]
 * @since 2016-11-30 0:17
 */
@Data
public class Response {
    private int statusCode;
    private String text;
    private InputStream content;
    private byte[] bytes;
    private String encoding;
    private Map<String, String> headers;
    private Map<String, String> setCookies;
    private Request request;

    @java.beans.ConstructorProperties({"statusCode", "text", "content", "bytes", "encoding", "headers", "setCookies", "request"})
    Response(int statusCode, String text, InputStream content, byte[] bytes, String encoding, Map<String, String> headers, Map<String, String> setCookies, Request request) {
        this.statusCode = statusCode;
        this.text = text;
        this.content = content;
        this.bytes = bytes;
        this.encoding = encoding;
        this.headers = headers;
        this.setCookies = setCookies;
        this.request = request;
    }

    public static ResponseBuilder builder() {
        return new ResponseBuilder();
    }

    public static class ResponseBuilder {
        private int statusCode;
        private String text;
        private InputStream content;
        private byte[] bytes;
        private String encoding;
        private Map<String, String> headers = new HashMap<>();
        private Map<String, String> setCookies = new HashMap<>();
        private Request request;

        ResponseBuilder() {
        }

        public Response.ResponseBuilder statusCode(int statusCode) {
            this.statusCode = statusCode;
            return this;
        }

        public Response.ResponseBuilder text(String text) {
            this.text = text;
            return this;
        }

        public Response.ResponseBuilder content(InputStream content) {
            this.content = content;
            return this;
        }

        public Response.ResponseBuilder bytes(byte[] bytes) {
            this.bytes = bytes;
            return this;
        }

        public Response.ResponseBuilder encoding(String encoding) {
            this.encoding = encoding;
            return this;
        }

        public Response.ResponseBuilder headers(Map<String, String> headers) {
            this.headers = headers;
            return this;
        }

        public Response.ResponseBuilder setCookies(Map<String, String> setCookies) {
            this.setCookies = setCookies;
            return this;
        }

        public Response.ResponseBuilder request(Request request) {
            this.request = request;
            return this;
        }

        public Response build() {
            return new Response(statusCode, text, content, bytes, encoding, headers, setCookies, request);
        }

        public String toString() {
            return "com.example.validation.entity.Response.ResponseBuilder(statusCode=" + this.statusCode + ", text=" + this.text + ", content=" + this.content + ", bytes=" + java.util.Arrays.toString(this.bytes) + ", encoding=" + this.encoding + ", headers=" + this.headers + ", setCookies=" + this.setCookies + ", request=" + this.request + ")";
        }
    }
}
