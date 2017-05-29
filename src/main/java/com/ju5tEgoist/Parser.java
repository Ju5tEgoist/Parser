package com.ju5tEgoist;


import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.*;
import java.util.*;

/**
 * Created by yulia on 28.05.17.
 */

public class Parser {
    private Doc doc = new Doc();
    private String name = "";
    private String url = "";
    private String brand = "";
    private String initialPrice = "";
    private String price = "";
    private String articleID = "";
    private String description = "";
    private final String URL = "https://www.aboutyou.de";
    Writer writer = new Writer();

    List<Product> products = new ArrayList<>();
    public void getAllParameters(String keyWord) throws IOException {
        String sURL = String.format("https://www.aboutyou.de/suche?term=%s&category=20201", keyWord);
        List<Product> products = dataFromPages(sURL);
       writer.write(products);
    }


    private int getPagesNumber(Document document) {
        String pagesNumberString = "";
        int pagesNumber;
        Elements links = document.select("ul.yiiPager li.page");
        if(links.size() == 0){
            pagesNumberString = "1";
        }
        Elements pages = links.select("a");
        for (Element element:pages) {
            pagesNumberString = element.text();
        }
        try {
            pagesNumber = Integer.parseInt(pagesNumberString);
        } catch (NumberFormatException e)
        {
            pagesNumber = 0;
        }
        return pagesNumber;
    }

    public List<Product> dataFromPages(String sURL) throws IOException {
        Document document = doc.getDocument(sURL);
        List<String> links = new ArrayList<>();
        Elements divName = document.select("div.js-product-name");
        Elements aName = divName.select("a");
        Elements divPrice = document.select("div.js-product-price");
        Elements divArticle = document.select("div.col-xs-4 ");
        Elements article = divArticle.select("article");

        for (int i = 1; i <= getPagesNumber(document); i++) {
            if (i != 1) {
                document = doc.getDocument(sURL + "&page=" + i);
            }
            int index = getElementsNumber(document);
            for (int j = 0; j < index; j++) {

                Element nameElement = aName.get(j);

                //name
                name = nameElement.text();

                //url
                url = nameElement.attr("href");
                links.add(url);
                Element art = article.get(j);

                //articleID
                articleID = art.attr("id");
                String result = URL + url;
                document = doc.getDocument(result);
                Elements divBrand = document.select("div.col-xs-10 a");

                //brand
                brand = divBrand.attr("title");
                Element priceElement = divPrice.get(j);

                //initialPrice
                initialPrice = priceElement.getElementsByClass("isStriked").text();

                //price
                price = priceElement.getElementsByClass("actual-price").text();
                String mainURL = "https://www.aboutyou.de";
                    String ur = mainURL + links.get(j);
                    document = doc.getDocument(ur);

                    //description
                    description = document.select("div.description-text").text();
                Elements colors = document.select("div.adp-stylepicker div.col-xs-10 a");
                Element element;

                //color
                String color = "";
                for (int k = 0; k < colors.size(); k++) {
                    element = colors.get(k);
                     color += element.attr("title");
                }


                products.add(new Product(name, brand, price, initialPrice, description, articleID, "0", url, color));
            }

        }
        return products;
    }



    private int getElementsNumber(Document document){
        int elementsNumber = 0;
        Elements elements = document.select("div.col-xs-4 article");
        for (Element element:elements) {
            elementsNumber++;
        }
        return elementsNumber;
    }
}
