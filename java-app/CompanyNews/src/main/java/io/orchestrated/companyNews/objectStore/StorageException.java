package io.orchestrated.companyNews.objectStore;

public class StorageException extends Exception
{
    public StorageException()
    {
        super();
    }

    public StorageException(String message, Throwable throwable)
    {
        super(message, throwable);
    }
}
