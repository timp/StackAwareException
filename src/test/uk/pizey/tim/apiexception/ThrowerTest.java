package uk.pizey.tim.apiexception;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ThrowerTest {
    @Test
    public void throwerTest() {
        Thrower t = new Thrower() ;
        try {
            t.throwIt();
        } catch (Exception e) {
            assertEquals("uk.pizey.tim.apiexception.Thrower.throwIt line 5: " +
                    "java.lang.RuntimeException: A problem somewhere", e.getMessage());
        }
    }

    @Test
    public void wrappedException() {
        StackAwareException it = new StackAwareException(new Exception("Inner exception message"));
        assertEquals("uk.pizey.tim.apiexception.ThrowerTest.wrappedException line 21: " +
                "java.lang.Exception: Inner exception message", it.getMessage());
    }
}
