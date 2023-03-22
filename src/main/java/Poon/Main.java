package Poon;

import Poon.Models.Playlist;
import Poon.Models.Track;

import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException {
//        System.setProperty("webdriver.chrome.driver", "selenium\\chromedriver.exe");
//
//        Parser parser = new Parser();
//        parser.openURL();
//
//        List<Playlist> playlists = parser.getPlaylists();
//        for (int i = 0; i < playlists.size(); i++) {
//            System.out.println((i + 1) + ": " + playlists.get(i));
//        }
//
//        List<Track> playlist = parser.parserPosts(parser.getOnePlaylist(playlists));
//        for (Track track : playlist) {
//            System.out.println(track);
//        }

        XML xml = new XML();
//        xml.createXml(playlist);
        xml.findTrack("Kordhell");
        xml.findTrack("Hensonn");
//        xml.addXmlNote("Culture Shock", "Discotheque", "123");
//        xml.addXmlNote("Feint", "Homebound", "321");
    }
}