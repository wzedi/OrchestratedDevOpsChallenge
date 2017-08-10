package io.orchestrated.companyNews.objectStore.prevayler;

import org.prevayler.Query;

import io.orchestrated.companyNews.objectStore.Model;

import java.util.Date;

public class GetModel implements Query<PrevaylerObjectStore, Model> {

    private int id;

    public GetModel(int id) {
        this.id = id;
    }

    public Model query(PrevaylerObjectStore prevaylerObjectStore, Date executionTime) throws Exception {
        return prevaylerObjectStore.prevalentGet(id);
    }
}
