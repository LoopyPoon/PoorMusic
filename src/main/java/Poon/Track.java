package Poon;

import java.io.IOException;

public class Track {

//    public static Document getPage() throws IOException {
//        String url = "https://music.yandex.ru/users/ivanholin1999/playlists/1009";
//        Document page = Jsoup.parse(new URL(url), 6000);
//        return page;
//    }
//
//    public static Elements getTable() throws IOException {
//        Document page = getPage();
//        // css query language
//        Element tableMusic = page.select("div[class=lightlist__cont]").first();
//        Elements trackName = tableMusic.select("div[class=d-track__name]");
//        Elements trackArtists = tableMusic.select("span[class=d-track__artists]");
//        return trackName;
//    }

    private String url;
    private String trackName;
    private String trackAuthor;

    public Track() throws IOException {

    }

    public Track(String url, String trackName, String trackAuthor) throws IOException {
        this.url = url;
        this.trackName = trackName;
        this.trackAuthor = trackAuthor;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTrackName() {
        return trackName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    public String getTrackAuthor() {
        return trackAuthor;
    }

    public void setTrackAuthor(String trackAuthor) {
        this.trackAuthor = trackAuthor;
    }

    @Override
    public String toString() {
        return "Track{" +
                "url='" + url + '\'' +
                ", trackName='" + trackName + '\'' +
                ", trackAuthor='" + trackAuthor + '\'' +
                '}';
    }
}