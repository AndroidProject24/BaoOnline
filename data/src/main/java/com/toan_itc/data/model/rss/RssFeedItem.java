package com.toan_itc.data.model.rss;
/**
 * Created by Toan.IT
 * Date: 29/06/2016
 */

import android.annotation.SuppressLint;

import com.tickaroo.tikxml.annotation.PropertyElement;
import com.tickaroo.tikxml.annotation.Xml;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

@Xml(name = "item")
public class RssFeedItem{

    @PropertyElement
    String title;
    @PropertyElement
    String pubDate;
    @PropertyElement
    String link;
    @PropertyElement
    String description;
    private String image;
    private String article;
    private String articleLink;


    @Override
    public String toString() {
        return "RealmFeedItem{" +
                "title='" + title + '\'' +
                ", pubDate='" + pubDate + '\'' +
                ", link='" + link + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", article='" + article + '\'' +
                ", articleLink='" + articleLink + '\'' +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    @SuppressLint("SimpleDateFormat")
    public long getPubDate() {
       /* SimpleDateFormat f = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z");
        Date d = null;
        try {
            d = f.parse(pubDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return d.getTime();*/
        return 0;
    }

    public String getLink() {
        return link;
    }


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public String getArticleLink() {
        return articleLink;
    }

    public void setArticleLink(String articleLink) {
        this.articleLink = articleLink;
    }

    public RssFeedItem() {}

    public boolean hasImage() {
        return this.getImage() != null && !getImage().isEmpty();
    }
    RssFeedItem extractDescription() {
        String HTMLSTring = this.getDescription();
        Document html = Jsoup.parse(HTMLSTring);
        setArticle(html.body().text());
        setImage(html.getElementsByTag("img").first().attr("src"));
       // setArticleLink(html.getElementsByTag("a").first().attr("href"));
        return this;
    }
}
