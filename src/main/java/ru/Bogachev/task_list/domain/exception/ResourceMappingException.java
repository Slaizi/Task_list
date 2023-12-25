package ru.Bogachev.task_list.domain.exception;

public class ResourceMappingException extends RuntimeException {
    public ResourceMappingException(final String message) {
        super(message);
    }
}
