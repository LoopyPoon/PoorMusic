package Poon;

import Poon.Models.Playlist;
import Poon.Models.Track;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Parser {
    private static final Scanner scan = new Scanner(System.in);
    private static final ChromeOptions options = new ChromeOptions();
    private static final WebDriver webDriver = new ChromeDriver(options.addArguments("--remote-allow-origins=*"));
    protected void openURL() throws InterruptedException {

        System.out.println("Выберите ползьзователя или введите нового: ");
        System.out.println("Нажмите 1, чтобы выбрать пользователя");
        System.out.println("Нажмите 2, чтобы ввести нового");

        String login;

        int answer = scan.nextInt();
        if (answer == 1) {
            System.out.println("Выберите пользователя: ");
            System.out.println("1: ivanholin1999");
            int answer2 = scan.nextInt();
            if (answer2 == 1) {
                login = "ivanholin1999";
            } else {
                System.out.println("Такого пользователя нет в списке, введите нового: ");
                login = scan.nextLine();
            }
        } else if (answer == 2) {
            System.out.println("Введите логин: ");
            login = scan.nextLine();
        } else {
            login = "ivanholin1999";
        }



        String url = "https://music.yandex.ru/users/" + login + "/playlists";

        System.out.println("Открытие сайта " + url);
        webDriver.get(url);

        System.out.println("Закрытие рекламы...");
        WebElement closeButton = webDriver.findElement(By.className("pay-promo-close-btn"));
        closeButton.click();

        Thread.sleep(1000);
    }

    protected List<Playlist> getPlaylists() {

        List<Playlist> playlist = new ArrayList<>();
        Document doc = Jsoup.parse(webDriver.getPageSource());
        Elements playlistNames = doc.getElementsByAttributeValue("class", "playlist__title deco-typo typo-main");
        System.out.println("Количество плейлистов: " + playlistNames.size());
        playlistNames.forEach(playlistElement -> {
            String playlistTitle = playlistElement.attr("title");
            String playlistUrl = "https://music.yandex.ru" + playlistElement.child(0).child(0).attr("href");
            playlist.add(new Playlist(playlistUrl, playlistTitle));
        });
        return playlist;
    }

    protected String getOnePlaylist(List<Playlist> playlists) {
        System.out.print("Введите номер плейлиста для парсинга: ");
        int answer = scan.nextInt() - 1;
        Playlist onePlaylist = playlists.get(answer);
        System.out.println("Выбранный плейлист:\n" + onePlaylist);
        String playlistURL = onePlaylist.getUrl();
        System.out.println("Открытие плейлиста: " + playlistURL);
        return playlistURL;
    }

    protected List<Track> parserPosts(String playlistURL) {

        webDriver.get(playlistURL);

        List<Track> trackList = new ArrayList<>();

        JavascriptExecutor js = (JavascriptExecutor) webDriver;

        System.out.println("Сбор данных...");
        WebElement trackBlock = webDriver.findElement(By.className("lightlist__cont"));

        js.executeScript("arguments[0].scrollIntoView();", webDriver.findElement(By.className("d-track_selectable")));

        for (int i = 1; trackBlock.findElements(By.cssSelector("div[data-id='" + i + "']")).size() > 0; i++) {

            WebElement element = trackBlock.findElement(By.cssSelector("div[data-id='" + i + "']"));

            Track track = new Track();
            try {
                String url = element.findElement(By.className("d-track__title")).getAttribute("href");
                track.setUrl(url);
            } catch (NoSuchElementException e) {
                track.setUrl(null);
            }
            try {
                String title = element.findElement(By.className("d-track__name")).getAttribute("title");
                track.setTrackName(title);
            } catch (NoSuchElementException e) {
                track.setTrackName(null);
            }
            try {
                List<String> authorList = element.findElement(By.className("d-track__artists"))
                        .findElements(By.className("deco-link_muted"))
                        .stream()
                        .map(WebElement::getText)
                        .toList();
                String author = String.join(", ", authorList);
                track.setTrackAuthor(author);
            } catch (NoSuchElementException e) {
                track.setTrackAuthor(null);
            }
            trackList.add(track);

            js.executeScript("window.scrollBy(0, 68)");
        }
        System.out.println("Количество треков в плейлисте: " + trackList.size());

        return trackList;
    }
}
