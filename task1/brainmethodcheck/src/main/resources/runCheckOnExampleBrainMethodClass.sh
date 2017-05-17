#!/bin/bash

PROJECT_DIR=$1

cd $PROJECT_DIR

echo "Runnning checks..."
java -cp target/brain-method-check-1.0-SNAPSHOT.jar:lib/checkstyle-7.7-all.jar \
com.puppycrawl.tools.checkstyle.Main -c src/main/resources/brain_check.xml \
-f plain -o brain_check_result.txt src/main/java/cz/muni/fi/pv260/chkstyle/ExampleBrainMethodClass.java

echo -e 'Done.\n'

echo -e 'Results:\n'

cat $PROJECT_DIR/brain_check_result.txt

