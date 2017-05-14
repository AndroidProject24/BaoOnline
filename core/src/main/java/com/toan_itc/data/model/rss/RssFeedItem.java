package com.toan_itc.data.model.rss;
/**
 * Created by Toan.IT
 * Date: 29/06/2016
 */

import com.tickaroo.tikxml.annotation.PropertyElement;
import com.tickaroo.tikxml.annotation.Xml;
import com.toan_itc.data.utils.TimeUtils;

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
    @PropertyElement
    String enclosure;
    String image;
    String article;
    String articleLink;


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

    public String getPubDate() {
        return TimeUtils.getStringToDate(this.pubDate);
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

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setEnclosure(String enclosure) {
        this.enclosure = enclosure;
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

    public boolean isEmpty() {
        return (title == null || "".equals(title)) && (link == null || "".equals(link));
    }

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
    RssFeedItem extractDescriptionDantri() {
        String HTMLSTring = this.getDescription();
        Document html = Jsoup.parse(HTMLSTring);
        setArticle(html.body().text());
        setImage(html.getElementsByTag("img").first().attr("src").replace("80_50","300_250"));
        return this;
    }
    RssFeedItem extractDescriptionKenh14() {
        String HTMLSTring = this.getDescription();
        Document html = Jsoup.parse(HTMLSTring);
        setArticle(html.body().text());
        setImage(html.getElementsByTag("img").first().attr("src").replace("110_69","300_250"));
        return this;
    }
    RssFeedItem extractDescriptionVietnamNet() {
        String HTMLSTring = this.getDescription();
        Document html = Jsoup.parse(HTMLSTring);
        setArticle(html.body().text());
        setImage(html.getElementsByTag("img").first().attr("src").replace("220","300"));
        return this;
    }
    public String image(){
        String HTMLSTring = this.getDescription();
        Document html = Jsoup.parse(HTMLSTring);
        return html.getElementsMatchingText("url").text();
    }
}
