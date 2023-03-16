package Poon;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static final Scanner scan = new Scanner(System.in);

    public static final ChromeOptions options = new ChromeOptions();
    public static final WebDriver webDriver = new ChromeDriver(options.addArguments("--remote-allow-origins=*"));

//    public static final String LOGIN = "ivanholin1999";
//    public static final String PASSWORD = ""

//    public static final String URL = "https://music.yandex.ru/users/" + LOGIN + "/playlists"; // Добавить выбор плейлиста

    public static void main(String[] args) throws IOException, InterruptedException {
        System.setProperty("webdriver.chrome.driver", "selenium\\chromedriver.exe");

//        System.setProperty("webdriver.http.factory", "jdk-http-client");
//        options.addArguments("--remote-allow-origins=*")
        openURL();
//        getPlaylists().forEach(System.out::println);
        List<Playlist> playlists = getPlaylists();
        for (int i = 0; i < playlists.size(); i++) {
            System.out.println((i + 1) + ": " + playlists.get(i));
        }



        parserPosts(getOnePlaylist(playlists)).forEach(System.out::println);
    }

    private static void openURL() throws InterruptedException {

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
            System.out.print("Введите логин: ");
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

    private static List<Playlist> getPlaylists() {

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

    private static String getOnePlaylist(List<Playlist> playlists) {
        System.out.print("Введите номер плейлиста для парсинга: ");
        int answer = scan.nextInt() - 1;
        Playlist onePlaylist = playlists.get(answer);
        System.out.println("Выбранный плейлист:\n" + onePlaylist);
        String playlistURL = onePlaylist.getUrl();
        System.out.println("Открытие плейлиста: " + playlistURL);
        return playlistURL;
    }

    private static List<Track> parserPosts(String playlistURL) throws IOException {

        webDriver.get(playlistURL);

        List<Track> trackList = new ArrayList<>();

//        System.out.println("Открытие сайта " + URL);
//        webDriver.get(URL);
//
//        System.out.println("Закрытие рекламы...");
//        WebElement closeButton = webDriver.findElement(By.className("pay-promo-close-btn"));
//        closeButton.click();
//
//        Thread.sleep(1000);

        JavascriptExecutor js = (JavascriptExecutor) webDriver;

        //        Document doc = Jsoup.connect("https://music.yandex.ru/users/ivanholin1999/playlists/1009").get();

//        Elements trackNames = doc.getElementsByAttributeValue("class", "d-track__overflowable-wrapper deco-typo-secondary block-layout");

//        Elements trackNames = null;
//
//        for (int i = 0; i < 2; i++) {
//            Document doc = Jsoup.connect("https://music.yandex.ru/users/ivanholin1999/playlists/1009").get();
//            trackNames = doc.getElementsByAttributeValue("class", "d-track__overflowable-wrapper deco-typo-secondary block-layout");
//        }
//
//            trackNames.forEach(trackName -> {
//
//                Element urlElement = trackName.child(0);
//                String title = urlElement.attr("title");
//                String url = urlElement.child(0).attr("href");
//                urlElement = trackName.child(1);
//                List<String> authorList = urlElement.child(0).children().eachAttr("title");
//                String author = String.join(", ", authorList);
//
//                try {
//                    trackList.add(new Parser(url, title, author));
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
//
//                js.executeScript("window.scrollBy(0, 100);");
//
//            });




//        Document doc = Jsoup.parse(webDriver.getPageSource());
//        Elements trackNames = doc.getElementsByAttributeValue("class", "d-track__overflowable-wrapper deco-typo-secondary block-layout");

//        WebElement tr = webDriver.findElement(By.xpath("/html/body/div[1]/div[11]/div[2]/div/div/div[4]/div/div/div/div[1]/div[1]"));


//            WebElement tracks = webDriver.findElement(By.cssSelector("div[data-id]"));
//            WebElement tracks1 = webDriver.findElement(By.className("lightlist__cont"));
//            WebElement tracks2 = webDriver.findElement(By.className("d-track_selectable"));
//            Document document = Jsoup.parse(webDriver.getPageSource());

//        List<WebElement> tracks = webDriver.findElements(By.className("d-track_selectable"));
//
//        List<String> result = new ArrayList<>();
//        for (int i = 0; i < tracks.size(); i++) {
//            List<String> trr = tracks.get(i).findElements(By.className("d-track__name")).stream().map(WebElement::getText).toList();
//            String st = new String(tracks.get(i).getText());
//            result.add(st);
//        }


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
//            List<String> authorList = element.findElements(By.className("d-track__name")).stream().findAny

                List<String> authorList = element.findElement(By.className("d-track__artists"))
                        .findElements(By.className("deco-link_muted"))
                        .stream()
                        .map(WebElement::getText)
                        .toList();
                String author = String.join(", ", authorList);
//                String author = element.findElement(By.className("deco-link_muted")).getAttribute("title");
                track.setTrackAuthor(author);
            } catch (NoSuchElementException e) {
                track.setTrackAuthor(null);
            }
            trackList.add(track);

            js.executeScript("window.scrollBy(0, 68)");
        }
        System.out.println(trackList.size());



//[data-id='" + i + "']
//        webDriver.findElement(By.cssSelector("div[class='d-track_selectable'][data-id='" + i + "']")).isDisplayed()
//            Elements trackNames = document.getElementsByAttributeValue("data-id", String.valueOf());

//
//
//
//            js.executeScript("window.scrollBy(0, 250);");
//        }

        // Длина страницы
//        long height = (long) ((JavascriptExecutor) webDriver).executeScript("return document.body.scrollHeight");
//        System.out.println(height);

//        trackNames.forEach(trackName -> {
//            Element urlElement = trackName.child(0);
//            String title = urlElement.attr("title");
//            String url = urlElement.child(0).attr("href");
//            urlElement = trackName.child(1);
//            List<String> authorList = urlElement.child(0).children().eachAttr("title");
//            String author = String.join(", ", authorList);
//
//            try {
//                trackList.add(new Parser(url, title, author));
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        });

//        List<WebElement> elements = webDriver.findElements(By.id("class"));
//
//        for (WebElement element : elements) {
//            System.out.println(webDriver.findElement(By.xpath("/html/body/div[1]/div[11]/div[2]/div/div/div[4]/div/div/div/div[1]/div[" + element + "]")));
//        }

//        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");

//        Document doc = Jsoup.parse(webDriver.getPageSource());
//


//        Document doc = Jsoup.connect("https://music.yandex.ru/users/ivanholin1999/playlists/1009").get();

//        Elements trackNames = doc.getElementsByAttributeValue("class", "d-track__overflowable-wrapper deco-typo-secondary block-layout");



//        trackNames.forEach(trackName -> {
//            Element urlElement = trackName.child(0);
//            String title = urlElement.attr("title");
//            String url = urlElement.child(0).attr("href");
//            urlElement = trackName.child(1);
//            List<String> authorList = urlElement.child(0).children().eachAttr("title");
//            String author = String.join(", ", authorList);
//
//            try {
//                trackList.add(new Parser(url, title, author));
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//
//            js.executeScript("window.scrollBy(0, 100);");
//
//        });
        return trackList;
    }

}