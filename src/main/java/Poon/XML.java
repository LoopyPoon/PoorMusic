package Poon;

import Poon.Models.Track;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XML {
     protected void createXml(List<Track> trackList) {
         System.out.println("Создание файла XML...");
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("playlist");
            doc.appendChild(rootElement);

            for (Track track : trackList) {
                Element track1 = doc.createElement("track");
                rootElement.appendChild(track1);

                Element author = doc.createElement("author");
                author.appendChild(doc.createTextNode(track.getTrackAuthor()));
                track1.appendChild(author);

                Element title = doc.createElement("title");
                title.appendChild(doc.createTextNode(track.getTrackName()));
                track1.appendChild(title);

                Element url = doc.createElement("url");
                url.appendChild(doc.createTextNode(track.getUrl()));
                track1.appendChild(url);
            }

            doc.setXmlStandalone(true);
            doc.normalizeDocument();
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty(OutputKeys.STANDALONE, "yes");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("src/main/java/Poon/playlist.xml"));
            transformer.transform(source, result);

            System.out.println("Файл создан!");

        } catch (ParserConfigurationException | TransformerException e) {
            throw new RuntimeException(e);
        }
    }

    protected void addXmlNote(String author, String title, String url) {
        System.out.println("Добавление записи в файл XML...");
        try {
            File xmlFile = new File("src/main/java/Poon/playlist.xml");

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();

            Element playlist = doc.getDocumentElement();

            Element trackNew = doc.createElement("track");
            playlist.appendChild(trackNew);

            Element authorNew = doc.createElement("author");
            authorNew.appendChild(doc.createTextNode(author));
            trackNew.appendChild(authorNew);

            Element titleNew = doc.createElement("title");
            titleNew.appendChild(doc.createTextNode(title));
            trackNew.appendChild(titleNew);

            Element urlNew = doc.createElement("url");
            urlNew.appendChild(doc.createTextNode(url));
            trackNew.appendChild(urlNew);

            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(xmlFile);
            transformer.transform(source, result);

        } catch (ParserConfigurationException | SAXException | IOException | TransformerException e) {
            throw new RuntimeException(e);
        }
    }

    protected void findTrack(String title) {
        System.out.println("Поиск записи...");
        List<String> foundList = new ArrayList<>();
        try {
            File xmlFile = new File("src/main/java/Poon/playlist.xml");
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(xmlFile);

            XPathFactory xPathFactory = XPathFactory.newInstance();
            XPath xPath = xPathFactory.newXPath();
//            XPathExpression expression = xPath.compile("//track[child::author[text() = '" + title + "']]");
            XPathExpression expression = xPath.compile("//track[child::author[text()[contains(., '" + title + "')]]]");
//            XPathExpression expression = xPath.compile("/playlist/track/author[text()[contains(text(), '" + title + "')]]");
            NodeList nodeList = (NodeList) expression.evaluate(doc, XPathConstants.NODESET);
//            Element firstElement = (Element) nodeList.item(0);
            int tracks = nodeList.getLength();
            System.out.println("Найдено " + tracks + " композиций по запросу: " + title);
            for (int i = 0; i < tracks; i++) {
                Element el = (Element) nodeList.item(i);
                String foundAuthor = el.getElementsByTagName("author").item(0).getTextContent();
                String foundTitle = el.getElementsByTagName("title").item(0).getTextContent();
                String foundUrl = el.getElementsByTagName("url").item(0).getTextContent();
                System.out.printf("%s - %s, %s\n", foundAuthor, foundTitle, foundUrl);
            }
        } catch (ParserConfigurationException | IOException | SAXException | XPathExpressionException e) {
            throw new RuntimeException(e);
        }
    }

}
