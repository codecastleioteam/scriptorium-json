# JSON Scriptorium
Fluent Java 8+ API for writing JSON output.

## Write early, write often
Almost every method writes as much output as possible directly to your
`Appendable`, so your memory footprint doesn't scale with the size or
complexity of your output. Meanwhile, a naturally nested fluent interface
makes it easy to produce valid JSON.

## Stay fluent
The JSON Scriptorium interfaces accept a wide variety of input, so you
can do less converting and null-checking. Powerful inscription features
allow you to express flow control functionally, and compose polyglot
output.

## It's time to use JSON Scriptorium when...
- Your codebase is mostly write-only POJOs that exist solely to be
object-mapped
- You need to output a wide variety of small JSON documents, but you
want more structure than `List<Object>` and `Map<String, Object>`
- You need to generate a little bit of JSON with a minimum of
fuss
- You need to generate JSON documents with programmatically-driven
keys, types, or structure
- You're serializing collections of objects that can't or won't
all be in memory at the same time, and outputting them to a stream

## Getting started
JSON Scriptorium is not yet in Maven Central. To include in your
project, you will need to clone this repo and
[scriptorium-core](https://github.com/dougvalenta/scriptorium-core).

### Maven Installation
Build and install `scriptorium-core` first, then `scriptorium-json` by
running Maven in each project's directory:

```
mvn clean install
```

To include JSON Scriptorium in your Maven project, add this block
to the `dependencies` section of your POM:

``` xml
<dependency>
    <groupId>net.dougvalenta.scriptorium</groupId>
    <artifactId>scriptorium-json</artifactId>
    <version>1.0-SNAPSHOT</version>
</dependency>
```

### JAR Installation
Build and package `scriptorium-core` first, then `scriptorium-json` by
running Maven in each project's directory:

```
mvn clean package
```

Copy each project's JAR from its `target` directory.

## Basic Usage

``` java
try (final JsonObjectDocument document = Json.object(System.out)) {
    document.with("key1", "value1")
        .with("key2", 99)
        .withNull("key3")
        .key("key4")
            .array()
                .with(1)
                .withTrue()
                .with("three")
                .with(new BigInteger("900000000000000000000000"))
                .then()
        .withFalse("key5");
}
```

``` json
{
    "key1": "value1",
    "key2": 99,
    "key3": null,
    "key4": [
        1,
        true,
        "three",
        900000000000000000000000
    ],
    "key5": false
}
```

(Whitespace has been added for clarity. Out-of-the-box, JSON Scriptorium
does not output whitespace.)

A conformant JSON document is always an object at the top level, never
an array. In real life, this isn't always the case. JSON Scriptorium
supports these JSON document fragments:

``` java
try (final JsonArrayDocument document = Json.array(System.out)) {
    document.with(1)
        .withTrue()
        .with("three")
        .object()
            .with("key1", "value1")
            .with("key2", 99)
            .withNull("key3")
}
```

``` json
[
    1,
    true,
    "three",
    {
        "key1": "value1",
        "key2": 99,
        "key3": null
    }
]
```

Note that closing the document properly closes all dangling nested
elements.

## Additional Resources

- [JavaDoc](https://dougvalenta.github.io/scriptorium-json/apidocs)
