package com.cafe24.jblog.exception;

public class MemberDaoException extends RuntimeException {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public MemberDaoException() { 
	super( "UserDaoException Occurs" );
    }

    public MemberDaoException(String message) { 
	super( message );
    }
}
