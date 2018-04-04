# JSON Scriptorium
Fluent Java API for writing JSON output.

## When to use JSON Scriptorium
- Your codebase is mostly write-only POJOs that exist solely to be
object-mapped
- You need to output a wide variety of small JSON documents, but you
want more structure than `List<Object>` and `Map<String, Object>`
- You need to generate a little bit of JSON with a minimum of
fuss
- You need to generate JSON documents with programmatically-driven
keys, types, or structure
- You're working with collections of objects that can't or won't
all be in memory at the same time, and serializing them to a stream

## Basic Usage

``` Java
Json.object(System.out) // pass in any Appendable
    .with("key1", "value1")
    .with("key2", 99)
    .withNull("key3")
    .key("key4").array()
        .with(1)
        .withTrue()
        .with("three")
        .with(new BigInteger("900000000000000000000000"))
        .then()
    .close();
```

``` JSON
{
    "key1": "value1",
    "key2": 99,
    "key3": null,
    "key4": [
        1,
        true,
        "three",
        900000000000000000000000
    ]
}
```

(Whitespace has been added for clarity. Out-of-the-box, JSON Scriptorium
does not output whitespace.)

A conformant JSON document is always an object at the top level, never
and array. In real life, this isn't always the case. JSON Scriptorium
supports these JSON document fragments:

``` Java
Json.array(System.out)
    .with(1)
    .withTrue()
    .with("three")
    .object()
        .with("key1", "value1")
        .with("key2", 99)
        .withNull("key3")
        .then()
    .close();
```

``` JSON
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

## Understanding Deep Fluency
JSON Scriptorium provides a fluent API to the recursively nested
structures of JSON documents. To achieve this, JSON Scriptorium
objects have methods that return nested contexts. These nested
contexts can be fluently closed using a `then` method that returns
the parent (the object they were originally returned by).

Many objects in the API are also `Closeable`. When these objects are
closed (if they have a `then` method, that method calls `close`),
*any* dangling unclosed inner nested structures will be closed (in
order) as well.

## Staying Fluent
JSON Scriptorium handles many input errors gracefully so you don't
have to break method chains to check your input:
- Null values and elements will be output as JSON null literals
(never the string "null")
- Key-value pairs with null keys will be skipped
- Appending null CharSequences and Characters is a no-op
- Non-finite floats and doubles (not supported by the JSON spec)
will output as null literals
- Convenience methods for JSON objects to skip key-value pairs with
null or non-finite values

In addition, JSON Scriptorium provides several methods for inversion
of flow control:

``` Java
object.withIf(includeUri, (o) -> {
        o.with("uri", uri);
    }).withEach(map.entrySet(), (entry, o) -> {
        o.with(entry.getKey(), entry.getValue());
    }).withIfNotNull(person, (p, o) -> {
        o.object("person")
            .with("name", person.getName())
            .with("id", person.getId().toString());
        // any dangling open structures, like this nested object,
        // will be closed after this function returns
    });
```

[JavaDoc](https://dougvalenta.github.io/scriptorium-json/apidocs)
