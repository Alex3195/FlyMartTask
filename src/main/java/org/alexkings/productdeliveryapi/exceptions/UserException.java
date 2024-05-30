package org.alexkings.productdeliveryapi.exceptions;

import java.sql.SQLException;

public class UserException extends SQLException {
    public UserException(String message) {
        super(message);
    }
}
