package uk.pizey.tim.apiexception;

public class StackAwareException extends RuntimeException {
    public StackAwareException(Throwable cause) {
        super(cause);
    }

    @Override
    public String getMessage() {
        String m = super.getMessage();
        if (getStackTrace().length > 0) {
            m = describe(getStackTrace()[0]) + ": " + m;
        }
        return m;
    }
    String describe(StackTraceElement ste){
        String s = "";

        s = ste.getClassName();

        return s + "." + ste.getMethodName() +
                        (ste.getLineNumber() >= 0 ?
                                " line " + ste.getLineNumber()  : ""
                                );

    }
}
