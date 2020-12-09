# ligature-formats
Support for reading and writing various RDF serialization formats for Ligature.

## Status
This project is just starting up again after a couple restarts/rewrites.
The long term goal of this project is to support the most commonly used RDF serialization formats within Ligature.
I'm going to try to keep this file up-to-date with what is currently supported, but also check the issues and tests.

## Building
This project requires SBT to be installed.
On Linux/Mac I recommend using https://sdkman.io/ to manage SBT installs.
Once that is set up use `sbt test` to run tests `sbt publishLocal` to install the artifact locally.
