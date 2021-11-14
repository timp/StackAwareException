# An Exception wrapper suitable for a RESTful API

## User Story

### As a third line support engineer
### I want to be able to go to the server class that throws an exception reported by a client
### So that I do not need to look for the stack trace in the server logs

## Example 
### Client code
    if (responseCode != 200) {
        throw new TaskException(
            "Error occurred while processing the scan response: " + 
            "response code: " + responseCode + 
            " response body: " + responseContent.getResponseBody());
    } 
### Server code
        if (null != header && header.startsWith(BEARER)) {
            String token = header.substring(BEARER.length()).trim();
            try {
                final Jws<Claims> jws = Jwts.parser().setSigningKeyResolver(jwtPublicKeyResolver)
                        .setAllowedClockSkewSeconds(3)
                        .parseClaimsJws(token);
            } catch (JwtException ex) {
                String errorMessage = "Invalid JWT token. ";
                setError(httpServletResponse, errorMessage + ex.getMessage());
                return;
            }
        }

This results in the following being reported by the second level support agent monitoring the client logs: 

    [Error occurred while processing the scan response : response code: 401 response body: Invalid JWT token. Error accessing publickey Api]:

What we, as Third Line Support, want is to know which server class throws the exception, 
ideally without grepping the code base or opening the server logs.

A better Exception message would be: 

    [Problem with scan response: status code: 401, body: com.corp.server.validation.JwtValidator line 72: JWT token Exception: Error accessing Public Key API]

This is the motivation for the StackAwareException, a wrapper exception 
which adds the class, method 
and line number of the first element of the wrapped exception's stack trace. 

