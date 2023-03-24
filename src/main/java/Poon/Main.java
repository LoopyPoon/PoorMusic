package Poon;

import Poon.FileCreators.ExcelCreator.EXCEL;
import Poon.FileCreators.JsonCreator.JSON;
import Poon.FileCreators.XmlCreator.XML;
import Poon.Models.Playlist;
import Poon.Models.Track;

import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "selenium\\chromedriver.exe");

        Parser parser = new Parser();
        parser.openURL();

        List<Playlist> playlists = parser.getPlaylists();
        for (int i = 0; i < playlists.size(); i++) {
            System.out.println((i + 1) + ": " + playlists.get(i));
        }

        List<Track> playlist = parser.parserPosts(parser.getOnePlaylist(playlists));
        for (Track track : playlist) {
            System.out.println(track);
        }

        // XML Block
//        {
//            XML xml = new XML();
//            xml.createXml(playlist);
//            xml.findTrack("Kordhell");
//            xml.findTrack("Hensonn");
//            xml.addXmlNote("Culture Shock", "Discotheque", "123");
//            xml.addXmlNote("Feint", "Homebound", "321");
//            xml.deleteTrack("RITUAL");
//        }

        // Json Block
//        {
//            JSON json = new JSON();
//            json.createJson(playlist);
//            json.findTrack("Zivert");
//            json.addTrack("МакSим", "Не отдам", "111");
//            json.deleteTrack("The Mother We Share");
//        }

        // Excel Block
        {
            EXCEL excel = new EXCEL();
            excel.creatExcel(playlist);
        }
    }
}