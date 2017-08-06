package io.orchestrated.companyNews.objectStore.prevayler;

import org.prevayler.TransactionWithQuery;

import io.orchestrated.companyNews.objectStore.Model;

import java.io.Serializable;
import java.util.Date;
import java.util.Random;

public class CreateModelTransaction implements TransactionWithQuery<PrevaylerObjectStore, Model>, Serializable {

    private static final long serialVersionUID = 18236198973l;

    private Model model;
    private final Random idGenerator;

    public CreateModelTransaction() 
    {
        this.idGenerator = new Random();
    }

    public CreateModelTransaction(Model model) 
    {
        this.model = model;
        this.idGenerator = new Random();
    }

    public Model executeAndQuery(PrevaylerObjectStore prevaylerObjectStore, Date executionTime) throws Exception 
    {
        this.model.setId(this.idGenerator.nextInt());
        prevaylerObjectStore.prevalentStore(this.model);
        return model;
    }
}
