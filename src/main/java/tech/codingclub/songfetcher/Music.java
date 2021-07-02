package tech.codingclub.songfetcher;

public class Music {
    private String title;
    private String album;
    private String duration;
    private String singers;
    private String music_director;
    private String lyricist;
    private String parent_link;
    private String link;
    private String download_128;
    private String download_320;
    private String img_src;

    public Music(String title, String album, String duration, String singers, String music_director, String lyricist, String parent_link, String link, String download_128, String download_320, String img_src) {
        this.title = title;
        this.album = album;
        this.duration = duration;
        this.singers = singers;
        this.music_director = music_director;
        this.lyricist = lyricist;
        this.parent_link = parent_link;
        this.link = link;
        this.download_128 = download_128;
        this.download_320 = download_320;
        this.img_src = img_src;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getSingers() {
        return singers;
    }

    public void setSingers(String singers) {
        this.singers = singers;
    }

    public String getMusic_director() {
        return music_director;
    }

    public void setMusic_director(String music_director) {
        this.music_director = music_director;
    }

    public String getLyricist() {
        return lyricist;
    }

    public void setLyricist(String lyricist) {
        this.lyricist = lyricist;
    }

    public String getParent_link() {
        return parent_link;
    }

    public void setParent_link(String parent_link) {
        this.parent_link = parent_link;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDownload_128() {
        return download_128;
    }

    public void setDownload_128(String download_128) {
        this.download_128 = download_128;
    }

    public String getDownload_320() {
        return download_320;
    }

    public void setDownload_320(String download_320) {
        this.download_320 = download_320;
    }

    public String getImg_src() {
        return img_src;
    }

    public void setImg_src(String img_src) {
        this.img_src = img_src;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
