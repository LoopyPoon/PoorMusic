package Poon.Models;

public class Track {

    private String url;
    private String trackName;
    private String trackAuthor;

    public Track() {

    }

    public Track(String url, String trackName, String trackAuthor) {
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