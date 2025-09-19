package com.scaulfield7.leagueofirelandapi.service;

import com.gargoylesoftware.htmlunit.WebClient;
import org.springframework.stereotype.Service;

@Service
public class ScraperService {

    private final WebClient webClient;

    public ScraperService(WebClient webClient) {
        this.webClient = webClient;
    }

    public String scrapePage(String url) throws Exception {
        return webClient.getPage(url).getWebResponse().getContentAsString();
    }
}
