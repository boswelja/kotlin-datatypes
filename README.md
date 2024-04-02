# kotlin-datatypes

A collection of Kotlin Multiplatform libraries to represent complex data types.

## What's available?

[Capacity](./capacity) for reading from and converting to digital storage types, for example Megabytes to Gigabytes.

[Percentage](./percentage) for reading from and converting to the most common percentage formats, for example `1.0f` to `100`.

[Temperature](./temperature) for reading from and converting to Celsius, Fahrenheit, Kelvin, Rankine and Réaumur.

## Setup

Stay tuned for setup instructions.

## Platform support

All libraries currently support JVM and Android. There are no blockers for other platforms, we just
need to figure out publishing.

## Why?

I was tired of having to write functions that convert between units, like hours to seconds
(`5 / 60.0 / 60.0`). After a bit of looking around, I found out Kotlin had a built-in `Duration`
that could handle time conversions! `5.hours.inWholeSeconds` is much more readable.

After discovering `Duration`, I thought "well why not do the same for units of digital storage".
And then I got carried away, so here we are with a repository full of many different types.
