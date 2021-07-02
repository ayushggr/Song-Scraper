package tech.codingclub.songfetcher;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import tech.codingclub.database.GenericDB;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import static tech.codingclub.songfetcher.FileUtility.readAndPrintFile;

//import static IO.FileUtility.readAndPrintFile;

public class SongsFetcher implements Runnable {
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public SongsFetcher(String url) {
        this.url = url;
    }

    @Override
    public void run() {
        Music music = new Music("", "", "", "", "", "", "", "", "", "", "");
        try {
            music.setParent_link(url);
            Document document = Jsoup.connect(url).get();
            Elements elements = document.select(".archive-body > figure h3");
            for (Element element : elements) {
//                System.out.println("https://songspk.mobi" + element.getElementsByTag("a").get(0).attr("href"));
                music.setTitle(element.getElementsByTag("a").get(0).text());
                music.setLink("https://songspk.mobi" + element.getElementsByTag("a").get(0).attr("href"));
                Document document1 = Jsoup.connect("https://songspk.mobi" + element.getElementsByTag("a").get(0).attr("href")).get();
                Elements elements1 = document1.select(".list-group.page-meta-body");
                for (Element element1 : elements1) {
                    int size = element1.select(".col-md-9.col-xs-6.text-left").size();
                    for (int i = 0; i <= size - 1; i++) {
                        String type = element1.select(".col-md-3").get(i).text();
                        switch (type) {
                            case "Album":
                                music.setAlbum(element1.select(".col-md-9.col-xs-6.text-left").get(i).text());
                                break;
                            case "Duration":
                                music.setDuration(element1.select(".col-md-9.col-xs-6.text-left").get(i).text());
                                break;
                            case "Singers":
                                music.setSingers(element1.select(".col-md-9.col-xs-6.text-left").get(i).text());
                                break;
                            case "Lyricist":
                                music.setLyricist(element1.select(".col-md-9.col-xs-6.text-left").get(i).text());
                                break;
                            case "Music Director":
                                music.setMusic_director(element1.select(".col-md-9.col-xs-6.text-left").get(i).text());
                        }
                    }
                }
                music.setDownload_128(document1.select(".col-sm-6.col-xs-12.text-center.page-down-btns").get(0).getElementsByTag("a").get(0).attr("href"));
                music.setDownload_320(document1.select(".col-sm-6.col-xs-12.text-center.page-down-btns").get(1).getElementsByTag("a").get(0).attr("href"));
                music.setImg_src(document1.select("img.thumbnail").get(0).attr("src"));
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                String json = gson.toJson(music);
                System.out.println(json);
                new GenericDB<Music>().addRow(tech.codingclub.tables.Music.MUSIC, music);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void songUrlFileBuilder() {
        String url = "https://songspk.mobi/browse/bollywood-singles/";
        char[] alphabets = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        File myObj = new File("D:\\JAVA\\Practice\\src\\main\\java\\ServersAndScrapers\\songCrawlerLinks.txt");
        try {
            if (!myObj.createNewFile()) {
                System.out.println("File already exists!");
            } else {
                System.out.println("File created : " + myObj.getName());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        FileWriter myWriter = null;
        try {
            myWriter = new FileWriter(myObj, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (char alphabet : alphabets) {
            try {
                Document document = Jsoup.connect(url + alphabet).get();
                int paginationLength = document.select(".pagination > li").size() - 1;
                if (paginationLength == -1) {
                    myWriter.append(url).append(String.valueOf(alphabet)).append("?page=1").append("\n");
                } else {
                    for (int i = 1; i <= paginationLength; i++) {
                        myWriter.append(url).append(String.valueOf(alphabet)).append("?page=").append(String.valueOf(i)).append("\n");
                        // System.out.println(url + alphabet + "?page=" + i);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        TaskManager taskManager = new TaskManager(20);
        ArrayList<String> allFileContent = readAndPrintFile("D:\\Song Crawler\\src\\main\\java\\tech\\codingclub\\songfetcher\\songCrawlerLinks.txt");
        for (String i : allFileContent) {
            taskManager.waitTillQueueIsFreeAndAddTask(new SongsFetcher(i));
        }
    }
}
