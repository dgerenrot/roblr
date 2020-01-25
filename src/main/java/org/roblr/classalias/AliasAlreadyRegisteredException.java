package org.roblr.classalias;

public class AliasAlreadyRegisteredException extends RuntimeException {
    private String alias;

    public AliasAlreadyRegisteredException() {
        super();
    }

    public AliasAlreadyRegisteredException(String msg, String alias) {
        
        super((msg != null && msg.length() > 0 ?  msg : "") +
               String.format(" [%s %s]", AliasAlreadyRegisteredException.class.getSimpleName(), alias));

        this.alias = alias;
    }

    public AliasAlreadyRegisteredException(String msg) {
        super(msg);
    }

    public AliasAlreadyRegisteredException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public String getAlias() {
        return alias;
    }
}
