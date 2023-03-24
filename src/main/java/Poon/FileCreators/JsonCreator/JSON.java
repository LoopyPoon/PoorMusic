package Poon.FileCreators.JsonCreator;

import Poon.Models.Track;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class JSON {
    public void createJson(List<Track> trackList) {
        JSONObject playlist = new JSONObject();
        JSONArray tracks = new JSONArray();

        for (Track track : trackList) {
            JSONObject trackNew = new JSONObject();
            trackNew.put("author", track.getTrackAuthor());
            trackNew.put("title", track.getTrackName());
            trackNew.put("url", track.getUrl());
            tracks.add(trackNew);
        }
        playlist.put("track", tracks);

        try (FileWriter file = new FileWriter("src/main/java/Poon/FileCreators/JsonCreator/playlist.json")) {
            file.write(playlist.toJSONString());
            file.flush();
            file.close();
            System.out.println("Json файл успешно создан!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void findTrack(String author) {
        try {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader("src/main/java/Poon/FileCreators/JsonCreator/playlist.json"));
            JSONObject jsonObject = (JSONObject) obj;
            System.out.println("Найденный элемент: " + jsonObject.keySet().iterator().next());
            JSONArray jsonArray = (JSONArray) jsonObject.get("track");
            for (Object object : jsonArray) {
                JSONObject track = (JSONObject) object;
                if (track.get("author").equals(author)) {
                    System.out.println(track.get("author"));
                    System.out.println(track.get("title"));
                    System.out.println(track.get("url"));
                }
            }
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public void addTrack(String author, String title, String url) {
        try {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader("src/main/java/Poon/FileCreators/JsonCreator/playlist.json"));
//            JSONObject jsonObject = (JSONObject) obj;
            JSONArray jsonArray = (JSONArray) obj;
            System.out.println("Добавление нового элемента...");
            JSONObject trackNew = new JSONObject();
            trackNew.put("author", author);
            trackNew.put("title", title);
            trackNew.put("url", url);
            jsonArray.add(trackNew);
            FileWriter fileWriter = new FileWriter("src/main/java/Poon/FileCreators/JsonCreator/playlist.json");
            fileWriter.write(jsonArray.toJSONString());
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteTrack(String title) {
        try {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader("src/main/java/Poon/FileCreators/JsonCreator/playlist.json"));
            JSONObject jsonObject = (JSONObject) obj;
            System.out.println("Удаление элемента...");
            JSONArray jsonArray = (JSONArray) jsonObject.get("track");
            for (Object object : jsonArray) {
                JSONObject track = (JSONObject) object;
                if (track.get("title").equals(title)) {
                    System.out.println(track.get("author"));
                    System.out.println(track.get("title"));
                    System.out.println(track.get("url"));
                    jsonArray.remove(object);
                    break;
                }
            }
            FileWriter fileWriter = new FileWriter("src/main/java/Poon/FileCreators/JsonCreator/playlist.json");
            fileWriter.write(jsonArray.toJSONString());
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
