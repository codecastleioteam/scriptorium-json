# JSON Scriptorium
Fluent Java API for writing JSON output

### Get JSON Scriptorium from Maven Central
``` xml
<dependency>
    <groupId>io.codecastle.scriptorium</groupId>
    <artifactId>scriptorium-json</artifactId>
    <version>2.0.0</version>
</dependency>
```

### When to use JSON Scriptorium
JSON Scriptorium provides an intuitive, fluent API to writing JSON output directly to your underlying [Appendable](https://docs.oracle.com/javase/8/docs/api/java/lang/Appendable.html), making it incredibly flexible, even for output whose keys or structure is determined at runtime. This approach also gives it a small memory footprint, even for very large output.

Use JSON Scriptorium when:
- You need to be able to output a wide variety of small JSON documents
- Your codebase is overrun with "POJOs" that exist only to be object mapped (and you're probably spending more time getting them to object-map correctly than writing real code)
- You need to bring in a new dependency to write JSON output anyway, and you're looking for something lightweight that puts you in control
- You need to produce JSON output for data that can't or won't all be in memory at the same time

## Basic usage
The [Json](https://scriptorium.codecastle.io/apidocs/scriptorium-json/2.0/io/codecastle/scriptorium/json/Json.html) class
provides static factory methods for accessing JSON Scriptorium's fluent API:

Example:
``` java
Json.object(System.out)          // writes "{" to System.out and returns a new JsonObjectDocument
    .with("greeting", "Hello!")  // writes '"greeting":"Hello!"' to System.out returns the JsonObjectDocument
    .key("to")                   // writes '"to' to System.out and returns a JsonKey<JsonObjectDocument>
        // we could continue to append to the key here (JsonKey implements Appendable)
        .array()                 // writes '":[' to System.out and returns a JsonArrayNode<JsonObjectDocument>
            .with("Mercury")     // writes '"Mercury"' to System.out and returns the JsonArrayNode
            .with("Venus")       // writes ',"Venus"' to System.out and returns the JsonArrayNode
            .with("Earth")       // ... et cetera
            .with("Mars")
            .with("Jupiter")
            .with("Saturn")
            .with("Uranus")
            .with("Neptune")
        .then()                  // writes "]" to System.out and returns the original JsonObjectDocument
.close();                        // writes "}" to System.out
                                 // Calling close() without calling the JsonArrayNode's then() method, e.g.
                                 // from a try-with-resources block would close the array first!
```
Output:
``` json
{"greeting":"Hello!","to":["Mercury","Venus","Earth","Mars","Jupiter","Saturn","Uranus","Neptune"]}
```

I've passed `System.out` above for simplicity, but `Json.object()` will take any `Appendable`. If you just want 
to produce a string, use [StringBuilder](https://docs.oracle.com/javase/8/docs/api/java/lang/StringBuilder.html). 
If you are writing to an `OutputStream` (or a `Writer` that is), make sure you use a 
[BufferedWriter](https://docs.oracle.com/javase/8/docs/api/java/io/BufferedWriter.html), as most of JSON Scriptorium's 
output is written character-by-character.

### Staying fluent
JSON Scriptorium's fluent API is designed to make it easy to write correct JSON output from Java code. If you stick
with the fluent interface (i.e., don't assign fluent return values to variables and then call them out of order),
Java's compile-time type checks will prevent you from producing invalid output. JSON Scriptorium provides a number of
features to help you "stay fluent":
- Methods accept a wide variety of input types, including both boxed and unboxed primitives
- Methods accept a wide variety of input values, with predictable, non-exceptional behavior on null arguments (see
[API docs](https://scriptorium.codecastle.io/apidocs/scriptorium-json/2.0) for details)
- Inversion of flow control methods allow you to check conditions and iterate without if blocks or loops

Inversion of flow control example:
``` java
Json.array(System.out)
    .withIf(shouldBeNullFirst, JsonArray::withNull)
    .withEach(bytes, (b, array) -> {
        if (b > 12) array.with(b);
    })
.close();
```

## Resources
- [API Docs](https://scriptorium.codecastle.io/apidocs/scriptorium-json/1.0)
- User Guide (coming soon)


## Change log

### 2.0.0

This version contains many changes that were driven by a goal to reduce the amount of
garbage produced by the library and to reduce its overall memory footprint. This has involved
some subtle changes to the API and its usage.

Most intermediate fluent interfaces no longer implement `Closeable`, and the semantics of
`then()` have change.

Perhaps unsurprisingly, version 2.0.0 is **not binary compatible** with previous versions of this
library. In many cases, user code written against previous versions of this library will
still compile and exhibit identical behavior.

In general, if your existing code does not assign intermediate fluent 
return values to variables (which is not a recommended practice), then your code should
continue to behave as it did with previous versions of this library.

- Now packaged as a multi-release `jar` with a proper `module-info.class` for Java versions
above 8.

- The following interfaces no longer extend `Closeable` and no longer have a `close()` method:
  - `FluentNode` (changed in core)
  - `JsonArrayNode`
  - `JsonObjectNode`
  - `JsonValue`

- Non-public classes and non-public members of public classes have been removed or renamed

- Public classes have become interfaces:
  - `JsonArrayDocument`
  - `JsonArrayNode`
  - `JsonKey`
  - `JsonObjectDocument`
  - `JsonObjectNode`
  - `JsonValue`

- Return types of `JsonInscription` methods have changed for compatibility with inscription
changes in core.

### 1.0.1
Provide an Automatic-Module-Name for forward compatibility 
with the Java 9+ module system: `io.codecastle.scriptorium.json`

