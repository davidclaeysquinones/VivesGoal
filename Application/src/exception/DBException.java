/*
 * ApplicationException.java
 *
 * Created on 29 maart 2005, 14:02
 */
package exception;

/**
 *
 * @author Katrien
 */
public class DBException extends Exception {

    public DBException() {
        super();
    }

    public DBException(String s) {
        super(s);
    }
}
