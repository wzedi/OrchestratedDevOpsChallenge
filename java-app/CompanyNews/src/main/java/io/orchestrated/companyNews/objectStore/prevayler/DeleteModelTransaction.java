package io.orchestrated.companyNews.objectStore.prevayler;

import org.prevayler.TransactionWithQuery;

import io.orchestrated.companyNews.objectStore.Model;

import java.io.Serializable;
import java.util.Date;

public class DeleteModelTransaction implements TransactionWithQuery<PrevaylerObjectStore, Model>, Serializable {

    private static final long serialVersionUID = 29184719872309l;

    private int id;

    public DeleteModelTransaction() 
    {
    }

    public DeleteModelTransaction(int id) 
    {
        this.id = id;
    }

    public Model executeAndQuery(PrevaylerObjectStore prevaylerObjectStore, Date executionTime) throws Exception 
    {
        return prevaylerObjectStore.prevalentDelete(this.id);
    }
}
