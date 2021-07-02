package tech.codingclub.songfetcher;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class WikiFetcher {
    public WikiFetcher(String keyword) {
        this.keyword = keyword;
    }

    String keyword;
    public WikiResult Fetch(){
        this.keyword = this.keyword.trim().replace(" ", "_");
        String wikiUrl = "https://en.wikipedia.org/wiki/" + keyword;
        String response = null;
        String img_src = null;
        try {
            String wikipediaResponseHTML = HttpUrlConnectionExample.sendGet(wikiUrl);
            Document document = Jsoup.parse(wikipediaResponseHTML, "https://en.wikipedia.org");
            Elements childElements = document.body().select(".infobox").next();
            response = childElements.text();
//            Element imgElements = document.body().select("img").get(0);
            img_src = document.body().select(".infobox img").get(0).attr("src");
        } catch (Exception e) {
            e.printStackTrace();
        }
        WikiResult wikiResult = new WikiResult(this.keyword, response, img_src);
        return wikiResult;
    }
}
