#!/bin/bash

cd target

java --module-path mods --module ms.refactored.kuncengen.cli $1 $2 $3
