# SUUID is an extension of java.util.UUID class to be able to use a shorter version of the uuid inside urls 
![](https://api.travis-ci.org/gastonfournier/suuid.svg?branch=master)

`suuid.SUUID` is just a class that provides a simple functionality: representing a uuid with less characters than it's standard form. 
Provided representation is in base 64 so it can be safely used in URLs.

For example the uuid:

`70ccb163-e1c4-4079-8f9d-4d091bf51e75`

can be represented as:

`cMyxY-HEQHmPnU0JG_UedQ`

which is 10 characters less than it's standard representation (I'm not counting the separators).

If this not satisfies your needs, I recommend you looking at this library: http://hashids.org/java/

# How to use it

*TODO:* import maven dependency. By the moment copy&paste class or checkout the project and import.

* Create a SUUID from an existing UUID: `new SUUID(uuid);`
* Create a random SUUID: `SUUID.randomSUUID()`
* Create a SUUID from two longs (analog to UUID constructor): `new SUUID(mostSignificantBits, leastSignificantBits)`

To obtain the base64 representation you have to call `suuid.toBase64()`. The method `toString()` prints the same as it's corresponding UUID.

# How suuid works

It's actually an extension of `java.util.UUID` class, but since this class is final the workaround is to wrap it and delegate.

It provides the same methods as UUID class.
