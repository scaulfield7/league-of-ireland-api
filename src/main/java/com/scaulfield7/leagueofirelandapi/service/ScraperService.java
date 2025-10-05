package com.scaulfield7.leagueofirelandapi.service;

import org.htmlunit.WebClient;
import org.htmlunit.html.HtmlAnchor;
import org.htmlunit.html.HtmlPage;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ScraperService {

    private final WebClient webClient;

    public ScraperService(WebClient webClient) {
        this.webClient = webClient;
    }

    public String scrapePage(String url) throws Exception {
        return webClient.getPage(url).getWebResponse().getContentAsString();
    }

    public WebClient getWebClient() {
        return webClient;
    }

    public List<String> scrapeTeamUrls() throws Exception {
        HtmlPage page = webClient.getPage("https://www.leagueofireland.ie/womens/premier-division/clubs/");
        List<HtmlAnchor> anchors = page.getByXPath("//a[contains(@href,'/clubs/')]");
        List<String> teamUrls = new ArrayList<>();
        for (HtmlAnchor anchor : anchors) {
            String href = anchor.getHrefAttribute();
            if (!href.startsWith("http")) {
                href = "https://www.leagueofireland.ie" + href;
            }
            teamUrls.add(href);
        }
        return teamUrls;
    }
}
