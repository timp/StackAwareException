package uk.pizey.tim.apiexception;

public class Thrower {
    public void throwIt()  {
        throw new StackAwareException(new RuntimeException("A problem somewhere"));
    }
}
