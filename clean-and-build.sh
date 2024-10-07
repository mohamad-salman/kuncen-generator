#!/bin/bash

echo ""
echo "--> menghapus build sebelumnya..."
rm -rf target

mkdir target
mkdir target/classes
mkdir target/mods

echo "--> membuat modul kuncen-generator-api..."
javac -d target/classes/ms.refactored.kuncengen.api \
	$(find kuncen-gen-api/src/main/java -name "*.java")

jar --create --file=target/mods/ms.refactored.kuncengen.api@1.0.jar \
	--module-version=1.0 \
	-C target/classes/ms.refactored.kuncengen.api .

echo "--> membuat modul kuncen-generator-cli..."
javac --module-path target/mods \
	-d target/classes/ms.refactored.kuncengen.cli \
	$(find kuncen-gen-cli/src/main/java -name "*.java")

jar --create --file=target/mods/ms.refactored.kuncengen.cli@1.0.jar \
	--module-version=1.0 \
	--main-class ms.refactored.kuncengen.cli.Main \
	-C target/classes/ms.refactored.kuncengen.cli .
