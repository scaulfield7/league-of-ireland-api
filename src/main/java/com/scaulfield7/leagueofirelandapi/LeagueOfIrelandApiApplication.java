package com.scaulfield7.leagueofirelandapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LeagueOfIrelandApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(LeagueOfIrelandApiApplication.class, args);
    }

    private static WebClient createWebClient() {
        WebClient webClient = new WebClient(BrowserVersion.CHROME);
        webClient.getOptions().setThrowExceptionOnScriptError(false);
        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setJavaScriptEnabled(false);
        return webClient;
    }
}
