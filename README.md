# kotlin-datatypes

A collection of Kotlin Multiplatform libraries to represent complex data types.

Check out [the docs](https://boswelja.github.io/kotlin-datatypes/) for details!

## What's available?

[Bitrate](./bitrate) for reading from and converting to units of digital data transfer, for example Mebibits per second to Gibibits per second.

[Capacity](./capacity) for reading from and converting to digital storage types, for example Megabytes to Gigabytes.

[Percentage](./percentage) for reading from and converting to the most common percentage formats, for example `1.0f` to `100`.

[Temperature](./temperature) for reading from and converting to Celsius, Fahrenheit, Kelvin, Rankine and Réaumur.

## Setup

```kt
kotlin {
    sourceSets {
        commonMain.dependencies {
            val datatypesVersion = "1.1.0"

            // Add dependency on Bitrate
            implementation("io.github.boswelja.datatypes:bitrate:$datatypesVersion")

            // Add dependency on Capacity
            implementation("io.github.boswelja.datatypes:capacity:$datatypesVersion")

            // Add dependency on Percentage
            implementation("io.github.boswelja.datatypes:percentage:$datatypesVersion")

            // Add dependency on Temperature
            implementation("io.github.boswelja.datatypes:temperature:$datatypesVersion")
        }
    }
}
```

## Platform support

All libraries currently support Android, iOS, macOS, tvOS, watchOS, JVM, Linux, Windows and WASM-JS.
For now, they are only accessible from Kotlin Multiplatform projects.
We are exploring publishing native libraries for use directly in other languages.

## Why?

I was tired of having to write functions that convert between units, like hours to seconds
(`5 / 60.0 / 60.0`). After a bit of looking around, I found out Kotlin had a built-in `Duration`
that could handle time conversions! `5.hours.inWholeSeconds` is much more readable.

After discovering `Duration`, I thought "well why not do the same for units of digital storage".
And then I got carried away, so here we are with a repository full of many different types.
