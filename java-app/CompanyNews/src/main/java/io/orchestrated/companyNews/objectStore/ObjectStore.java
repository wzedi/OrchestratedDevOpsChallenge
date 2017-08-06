package io.orchestrated.companyNews.objectStore;

import java.util.Collection;

public interface ObjectStore
{
    /**
     * Store the given object.
     */
    Model store(Model modelToStore) throws StorageException;

    /**
     * Retrieve the object with the specified ID.
     */
    Model get(int id) throws StorageException;

    /**
     * Get the list of all stored objects.
     */
    Collection<Model> getList() throws StorageException;

    /**
     * Delete the the object with the specified ID.
     */
    Model delete(int id) throws StorageException;
}
