package com.toan_itc.baoonline.mvp.model.rss;
/**
 * Created by Toan.IT
 * Date: 29/06/2016
 */

import com.tickaroo.tikxml.annotation.PropertyElement;
import com.tickaroo.tikxml.annotation.TextContent;
import com.tickaroo.tikxml.annotation.Xml;

@Xml(name = "item")
public class RssFeedItem{


    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "RssFeedItem{" +
                "title='" + title + '\'' +
                ", publicationDate='" + pubDate + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public String getDescription() {
        return description;
    }

    public String getPublicationDate() {
        return pubDate;
    }

    @PropertyElement
    String title;
    @PropertyElement
    String pubDate;
    @TextContent
    String description;

    public RssFeedItem(String title, String description, String publicationDate) {
        this.title = title;
        this.description = description;
        this.pubDate = publicationDate;
    }
    public RssFeedItem() {}

    public boolean isEqualTo(RssFeedItem o) {
        if (o.getTitle().equals(title) &&
                o.getDescription().equals(description) &&
                o.getPublicationDate().equals(pubDate)) {
            return true;
        }
        else
            return false;
    }

    /*public Item extractDescription() {
        String HTMLSTring = this.getDescription();
        Document html = Jsoup.parse(HTMLSTring);
        setArticle(html.body().text());
        setImage(html.getElementsByTag("img").first().attr("src"));
        setArticleLink(html.getElementsByTag("a").first().attr("href"));
        return this;
    }*/
}
