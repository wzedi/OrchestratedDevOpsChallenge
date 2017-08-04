package io.orchestrated.companyNews.objectStore.prevayler;

import java.io.Serializable;
import java.io.IOException;

import java.util.Collection;
import java.util.Hashtable;
import java.util.Random;

import io.orchestrated.companyNews.objectStore.ObjectStore;
import io.orchestrated.companyNews.objectStore.Model;

import org.prevayler.Prevayler;
import org.prevayler.PrevaylerFactory;


public class PrevaylerObjectStore implements ObjectStore, Serializable
{
    private final String DATA_FOLDER = "data";
    private final Hashtable<Integer, Model> models;
    private final Random idGenerator;

    public PrevaylerObjectStore () throws Exception
    {
        this.models = new Hashtable<Integer, Model>();
        this.idGenerator = new Random();

        final Prevayler prevayler = PrevaylerFactory.createPrevayler(this, DATA_FOLDER);
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

    public int store(Model modelToStore)
    {
        if (!modelToStore.hasId()) {
            modelToStore.setId(this.idGenerator.nextInt());
        }

        this.models.put(modelToStore.getId(), modelToStore);

        return modelToStore.getId();
    }

    public Model get(int id)
    {
        return this.models.get(Integer.valueOf(id));
    }

    public Collection<Model> getList()
    {
        return this.models.values();
    }

    public void delete(int id)
    {
        this.models.remove(Integer.valueOf(id));
    }
}
