package io.orchestrated.companyNews.objectStore.prevayler;

import org.prevayler.Query;

import io.orchestrated.companyNews.objectStore.Model;

import java.util.Date;
import java.util.Collection;

public class GetModelList implements Query<PrevaylerObjectStore, Collection<Model>> {

    public GetModelList() {
    }

    public Collection<Model> query(PrevaylerObjectStore prevaylerObjectStore, Date executionTime) throws Exception {
        return prevaylerObjectStore.prevalentGetList();
    }
}
