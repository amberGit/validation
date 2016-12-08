package com.example.validation.downloader;

import com.example.validation.entity.Request;
import com.example.validation.entity.Response;
import org.junit.Before;
import org.junit.Test;


/**
 * @author Wen Jiao [jiao_wen@kingdee.com]
 * @since 2016-12-01 11:26
 */
public class HttpClientDownloaderTest {
    private HttpClientDownloader downloader;
    @Before
    public void setUp() throws Exception {
        downloader = new HttpClientDownloader();

    }

    @Test
    public void download() throws Exception {
        Request request = Request.builder()
                .url("http://cn.bing.com/search?q=dadf&qs=n&form=QBLH&pq=dadf&sc=8-4&sp=-1&sk=&cvid=0666FF66B3F044F8B7004EC449AE31A4")
                .method(Request.HttpMethod.GET)
                .build();

        Response resp = downloader.download(request);


    }

}