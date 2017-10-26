package exceptions;

/**
 * Created by Olha_Chuchuk on 9/14/2017.
 */
public class IllegalRowException extends RuntimeException {
    public IllegalRowException(Exception e) {
        super(e);
    }

    public IllegalRowException() {

    }
}
