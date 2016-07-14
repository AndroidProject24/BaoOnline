package com.toan_itc.data.model.rss;
/**
 * Created by Toan.IT
 * Date: 29/06/2016
 */

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
        return "RssFeedItem{" +
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

    public String getPublicationDate() {
        return pubDate;
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

    public boolean isEqualTo(RssFeedItem o) {
        if (o.getTitle().equals(title) && o.getDescription().equals(description) && o.getPublicationDate().equals(pubDate)) {
            return true;
        }
        else
            return false;
    }
    public boolean hasImage() {
        return this.getImage() != null && !getImage().isEmpty();
    }
    RssFeedItem extractDescription() {
        String HTMLSTring = this.getDescription();
        Document html = Jsoup.parse(HTMLSTring);
        setArticle(html.body().text());
        setImage(html.getElementsByTag("img").first().attr("src"));
        setArticleLink(html.getElementsByTag("a").first().attr("href"));
        return this;
    }
}
