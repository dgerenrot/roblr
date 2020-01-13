package org.jozin.classalias;

public class ClassAlreadyRegisteredException extends RuntimeException {
    private String clazz;

    public ClassAlreadyRegisteredException() {
        super();
    }

    public ClassAlreadyRegisteredException(String msg, String alias) {
        super((msg != null ?  msg : "") +
                String.format(" [%s %s]", ClassAlreadyRegisteredException.class.getSimpleName(), alias));
        this.clazz = alias;
    }

    public ClassAlreadyRegisteredException(String msg) {
        super(msg);
    }

    public ClassAlreadyRegisteredException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public String getFaultyClassName() {
        return clazz;
    }
}
