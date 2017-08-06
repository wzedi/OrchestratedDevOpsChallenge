package io.orchestrated.companyNews.objectStore.prevayler;

import java.io.Serializable;
import java.io.IOException;

import java.util.Collection;
import java.util.Hashtable;

import io.orchestrated.companyNews.objectStore.ObjectStore;
import io.orchestrated.companyNews.objectStore.Model;
import io.orchestrated.companyNews.objectStore.StorageException;

import org.prevayler.Prevayler;
import org.prevayler.PrevaylerFactory;


public class PrevaylerObjectStore implements ObjectStore, Serializable
{
    private final String DATA_FOLDER = "data";
    private final Hashtable<Integer, Model> models;
    transient private Prevayler<PrevaylerObjectStore> prevayler = null;

    private static final long serialVersionUID = 10298730129l;

    public PrevaylerObjectStore ()
    {
        this.models = new Hashtable<Integer, Model>();
    }

    private Prevayler<PrevaylerObjectStore> getPrevayler() throws Exception
    {
        if (this.prevayler == null) {
            prevayler = PrevaylerFactory.createPrevayler(this, DATA_FOLDER);
            Thread snapShotThread = new Thread() {
                public void run() {
                    while (true) {
                        try {
                            Thread.sleep(500);
                            prevayler.takeSnapshot();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            };
            snapShotThread.start();
        }

        return this.prevayler;
    }

    private void commit()
    {
        try {
            getPrevayler().takeSnapshot();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // prevalent operations
    protected void prevalentStore(Model modelToStore)
    {
        this.models.put(Integer.valueOf(modelToStore.getId()), modelToStore);
    }

    protected Model prevalentGet(int id)
    {
        return this.models.get(Integer.valueOf(id));
    }

    protected Collection<Model> prevalentGetList()
    {
        return this.models.values();
    }

    protected Model prevalentDelete(int id)
    {
        return this.models.remove(Integer.valueOf(id));
    }

    // object store implementation
    public Model store (Model modelToStore) throws StorageException
    {
        Model storedModel = null;
        try {
            CreateModelTransaction createModelTransaction = new CreateModelTransaction(modelToStore);
            storedModel = getPrevayler().execute(createModelTransaction);
        } catch (Exception exception) {
            throw new StorageException("Unable to create model", exception);
        }

        commit();
        return storedModel;
    }

    public Model get(int id) throws StorageException
    {
        Model retrievedModel = null;
        try {
            GetModel getModel = new GetModel(id);
            retrievedModel = getPrevayler().execute(getModel);
        } catch (Exception exception) {
            throw new StorageException("Unable to get model with id " + id, exception);
        }

        commit();
        return retrievedModel;
    }

    public Collection<Model> getList() throws StorageException
    {
        Collection<Model> retrievedList;
        try {
            GetModelList getModelList = new GetModelList();
            retrievedList = getPrevayler().execute(getModelList);
        } catch (Exception exception) {
            throw new StorageException("Unable to get model list", exception);
        }

        commit();
        return retrievedList;
    }

    public Model delete(int id) throws StorageException
    {
        Model deletedModel = null;

        try {
            DeleteModelTransaction deleteModelTransaction = new DeleteModelTransaction(id);
            deletedModel = getPrevayler().execute(deleteModelTransaction);
        } catch (Exception exception) {
            throw new StorageException("Unable to delete model with id " + id, exception);
        }

        commit();
        return deletedModel;
    }
}
