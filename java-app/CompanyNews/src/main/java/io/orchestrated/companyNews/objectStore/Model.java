package io.orchestrated.companyNews.objectStore;

import java.io.Serializable;

public class Model implements Serializable
{
    private int id = 0;

    public boolean hasId()
    {
        return id != 0;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getId()
    {
        return this.id;
    }
} 
