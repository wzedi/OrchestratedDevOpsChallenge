package io.orchestrated.companyNews.objectStore;

import java.io.Serializable;

public class Model implements Serializable
{
    private static final long serialVersionUID = 293724293479l;
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

    public boolean equals(Object o)
    {
        return ((Model)o).hashCode() == hashCode();
    }

    public int hashCode()
    {
        return getId();
    }
} 
