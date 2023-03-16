package Poon;

public class Playlist {

    private String url;
    private String playlistTitle;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPlaylistTitle() {
        return playlistTitle;
    }

    public void setPlaylistTitle(String playlistTitle) {
        this.playlistTitle = playlistTitle;
    }

    public Playlist() {

    }

    public Playlist(String url, String playlistTitle) {
        this.url = url;
        this.playlistTitle = playlistTitle;
    }

    @Override
    public String toString() {
        return "Playlist{" +
                ", url='" + url + '\'' +
                ", playlistTitle='" + playlistTitle + '\'' +
                '}';
    }
}
