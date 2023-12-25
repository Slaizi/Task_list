package ru.Bogachev.task_list.domain.exception;

public class ImageUploadException extends RuntimeException {
    public ImageUploadException(final String message) {
        super(message);
    }
}
