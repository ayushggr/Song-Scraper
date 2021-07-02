package tech.codingclub.songfetcher;

public class WikiResult {
    private String query;
    private String txt_result;
    private String img_src;

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getTxt_result() {
        return txt_result;
    }

    public void setTxt_result(String txt_result) {
        this.txt_result = txt_result;
    }

    public String getImg_src() {
        return img_src;
    }

    public void setImg_src(String img_src) {
        this.img_src = img_src;
    }

    public WikiResult(String query, String txt_result, String img_src) {
        this.query = query;
        this.txt_result = txt_result;
        this.img_src = img_src;
    }
}
