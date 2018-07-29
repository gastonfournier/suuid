# suuid
![](https://api.travis-ci.org/gastonfournier/suuid.svg?branch=master)

This is just a class that provides a simple functionality: representing a uuid with less characters than it's standard form. 
Provided representation is in base 64 so it can be safely used in URLs.

For example the uuid:

`70ccb163-e1c4-4079-8f9d-4d091bf51e75`

can be represented as:

`cMyxY-HEQHmPnU0JG_UedQ`

which is 10 characters less than it's standard representation (I'm not counting the separators).

If this not satisfies your needs, I recommend you looking at this library: http://hashids.org/java/

# How suuid works

It's actually an extension of `java.util.UUID` class, but since this class is final the workaround is to wrap it and delegate.

It provides the same methods as UUID class.
