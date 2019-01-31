# JSON Scriptorium
Fluent Java API for writing JSON output

### Get JSON Scriptorium from Maven Central
``` xml
<dependency>
    <groupId>io.codecastle.scriptorium</groupId>
    <artifactId>scriptorium-json</artifactId>
    <version>1.0.2</version>
</dependency>
```

### Add JSON Scriptorium as a module
``` java
requires io.codecastle.scriptorium.json;
```

As of version 1.0.2, Java 8 support has been dropped.

### When to use JSON Scriptorium
JSON Scriptorium provides an intuitive, fluent API to writing JSON output directly to your underlying [Appendable](https://docs.oracle.com/javase/8/docs/api/java/lang/Appendable.html), making it incredibly flexible, even for output whose keys or structure is determined at runtime. This approach also gives it a small memory footprint, even for very large output.

Use JSON Scriptorium when:
- You need to be able to output a wide variety of small JSON documents
- Your codebase is overrun with "POJOs" that exist only to be object mapped (and you're probably spending more time getting them to object-map correctly than writing real code)
- You need to bring in a new dependency to write JSON output anyway, and you're looking for something lightweight that puts you in control
- You need to produce JSON output for data that can't or won't all be in memory at the same time

## Basic usage
The [Json](https://scriptorium.codecastle.io/apidocs/scriptorium-json/1.0/io/codecastle/scriptorium/json/Json.html) class
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
to produce a string, use [StringBuilder](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/lang/StringBuilder.html). 
If you are writing to an `OutputStream` (or a `Writer` that is), make sure you use a 
[BufferedWriter](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/io/BufferedWriter.html), as most of JSON Scriptorium's 
output is written character-by-character.

### Staying fluent
JSON Scriptorium's fluent API is designed to make it easy to write correct JSON output from Java code. If you stick
with the fluent interface (i.e., don't assign fluent return values to variables and then call them out of order),
Java's compile-time type checks will prevent you from producing invalid output. JSON Scriptorium provides a number of
features to help you "stay fluent":
- Methods accept a wide variety of input types, including both boxed and unboxed primitives
- Methods accept a wide variety of input values, with predictable, non-exceptional behavior on null arguments (see
[API docs](https://scriptorium.codecastle.io/apidocs/scriptorium-json/1.0) for details)
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

