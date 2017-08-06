package io.orchestrated.companyNews.models;

import io.orchestrated.companyNews.objectStore.Model;

public class NewsArticle extends Model
{
    private String headline;
    private String story;

    public void setHeadline(String headline)
    {
        this.headline = headline;
    }

    public void setStory(String story)
    {
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
