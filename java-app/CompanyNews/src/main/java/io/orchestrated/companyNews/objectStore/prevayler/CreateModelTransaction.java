package io.orchestrated.companyNews.objectStore.prevayler;

import org.prevayler.TransactionWithQuery;

import io.orchestrated.companyNews.objectStore.Model;

import java.io.Serializable;
import java.util.Date;
import java.util.Random;

public class CreateModelTransaction implements TransactionWithQuery<PrevaylerObjectStore, Model>, Serializable {

    private static final long serialVersionUID = 18236198973l;

    private Model model;

    public CreateModelTransaction() 
    {
    }

    public CreateModelTransaction(Model model) 
    {
        this.model = model;
    }

    public Model executeAndQuery(PrevaylerObjectStore prevaylerObjectStore, Date executionTime) throws Exception 
    {
        this.model.setId(new Random().nextInt());
        prevaylerObjectStore.prevalentStore(this.model);
        return model;
    }
}
