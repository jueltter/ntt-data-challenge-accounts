package ec.dev.samagua.ntt_data_challenge_accounts.utils_exceptions;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = false)
public class RepositoryException extends RuntimeException {

    private String code;
    private List<ec.dev.samagua.ntt_data_challenge_accounts.utils_exceptions.RepositoryExceptionDetail> details;

    public RepositoryException(String message, Throwable cause) {
        super(message, cause);
    }

    public static <T extends Throwable> RepositoryException getCreateException(T originalException) {
        ec.dev.samagua.ntt_data_challenge_accounts.utils_exceptions.RepositoryExceptionDetail detail = ec.dev.samagua.ntt_data_challenge_accounts.utils_exceptions.RepositoryExceptionDetail.builder()
                .code(originalException.getClass().getCanonicalName())
                .message(originalException.getMessage())
                .build();

        RepositoryException ex = new RepositoryException("An error has occurred while creating the object", originalException);
        ex.setCode("CREATION_EXCEPTION");
        ex.setDetails(List.of(detail));
        return ex;
    }

    public static <T extends Throwable> RepositoryException getReadException(T originalException) {
        ec.dev.samagua.ntt_data_challenge_accounts.utils_exceptions.RepositoryExceptionDetail detail = ec.dev.samagua.ntt_data_challenge_accounts.utils_exceptions.RepositoryExceptionDetail.builder()
                .code(originalException.getClass().getCanonicalName())
                .message(originalException.getMessage())
                .build();

        RepositoryException ex = new RepositoryException("An error has occurred while reading for objects", originalException);
        ex.setCode("READ_EXCEPTION");
        ex.setDetails(List.of(detail));
        return ex;
    }

    public static <T extends Throwable> RepositoryException getUpdateException(T originalException) {
        ec.dev.samagua.ntt_data_challenge_accounts.utils_exceptions.RepositoryExceptionDetail detail = ec.dev.samagua.ntt_data_challenge_accounts.utils_exceptions.RepositoryExceptionDetail.builder()
                .code(originalException.getClass().getCanonicalName())
                .message(originalException.getMessage())
                .build();

        RepositoryException ex = new RepositoryException("An error has occurred while updating the object", originalException);
        ex.setCode("UPDATE_EXCEPTION");
        ex.setDetails(List.of(detail));
        return ex;
    }

    public static <T extends Throwable> RepositoryException getDeleteException(T originalException) {
        ec.dev.samagua.ntt_data_challenge_accounts.utils_exceptions.RepositoryExceptionDetail detail = ec.dev.samagua.ntt_data_challenge_accounts.utils_exceptions.RepositoryExceptionDetail.builder()
                .code(originalException.getClass().getCanonicalName())
                .message(originalException.getMessage())
                .build();

        RepositoryException ex = new RepositoryException("An error has occurred while deleting the object", originalException);
        ex.setCode("DELETE_EXCEPTION");
        ex.setDetails(List.of(detail));
        return ex;
    }

}
