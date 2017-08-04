package io.orchestrated.companyNews.models;

import io.orchestrated.companyNews.objectStore.Model;

public class NewsArticle extends Model
{
    private String headline;
    private String story;

    public NewsArticle(String headline, String story)
    {
        this.headline = headline;
        this.story = story;
    }

    public String getHeadline()
    {
        return this.headline;
    }

    public String getStory()
    {
        return this.story;
    }
} 
