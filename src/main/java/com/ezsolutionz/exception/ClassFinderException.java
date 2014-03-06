package com.ezsolutionz.exception;

/**
 * A customer exception class which will be used to propagate all exceptions
 * to the user of the library. It is defined as a {@link RuntimeException} so
 * as to keep the exception messages unobtrusive
 * 
 * @author Dinuka Arseculeratne
 *
 */
public class ClassFinderException extends RuntimeException {

    /**
	 * 
	 */
    private static final long serialVersionUID = 993518867346902740L;

    public ClassFinderException(final String message) {

        super(message);
    }

    public ClassFinderException(final Throwable throwable, String message) {
        super(message, throwable);
    }

    public ClassFinderException(Throwable throwable) {
        super(throwable);
    }
}
