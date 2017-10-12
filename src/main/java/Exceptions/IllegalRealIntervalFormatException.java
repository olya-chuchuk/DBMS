package Exceptions;

/**
 * Created by Olha_Chuchuk on 10/12/2017.
 */
public class IllegalRealIntervalFormatException extends RuntimeException {
    public IllegalRealIntervalFormatException(NumberFormatException e) {
        super(e);
    }

    public IllegalRealIntervalFormatException() {
        super();
    }
}
