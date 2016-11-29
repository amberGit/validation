package com.example.validation.downloader;

;import com.example.validation.entity.Context;
import com.example.validation.entity.Request;
import com.example.validation.entity.Response;

/**
 * @author Amber [johnnyven@outlook.com]
 * @since 2016-11-30 0:26
 */
public interface Downloader {
    Response download(Request request, Context context);

    Response download(Request request);
}
