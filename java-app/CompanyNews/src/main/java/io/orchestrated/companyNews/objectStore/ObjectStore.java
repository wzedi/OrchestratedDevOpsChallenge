package io.orchestrated.companyNews.objectStore;

import java.util.Collection;

public interface ObjectStore
{
    /**
     * Store the given object.
     */
    int store(Model modelToStore);

    /**
     * Retrieve the object with the specified ID.
     */
    Model get(int id);

    /**
     * Get the list of all stored objects.
     */
    Collection<Model> getList();

    /**
     * Delete the the object with the specified ID.
     */
    void delete(int id);
}
