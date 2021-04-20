- `./gradlew run`
- `curl -v -F file0=@"/path/to/25-30mb/file/on/your/system" -F json="{\"test\":\"12345\"}" http://localhost:8080/upload`
- Previous step x 5-10
- Observe "io.netty.util.internal.OutOfDirectMemoryError: failed to allocate 16777216 byte(s) of direct memory (used: 117440519, max: 128974848)"

This repo as is is testing Netty 4.1.59, however you can uncomment `//details.useVersion '4.1.63.Final'` in the `build.gradle` to use the latest version. Note that with using the latest version the upload takes a significantly longer amount of time. That is likely a separate issue to the memory leak though.
