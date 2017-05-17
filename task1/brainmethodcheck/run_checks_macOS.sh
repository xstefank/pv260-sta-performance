#!/bin/bash
mvn clean install

echo "Runnning checks..."
cd target

jar xf brain-method-check-1.0-SNAPSHOT.jar

java -cp brain-method-check-1.0-SNAPSHOT.jar:checkstyle-7.7-all.jar com.puppycrawl.tools.checkstyle.Main -c brain_check.xml -f plain -o brain_check_result.txt ExampleBrainMethodClass.java
java -cp brain-method-check-1.0-SNAPSHOT.jar:checkstyle-7.7-all.jar com.puppycrawl.tools.checkstyle.Main -c brain_check_passing_for_example_class.xml -f plain -o passing_brain_check_result.txt ExampleBrainMethodClass.java

echo -e 'Done.\n'

echo -e 'Results:\n'

echo 'Check1:'
cat brain_check_result.txt
echo -e '\nCheck2:'
cat passing_brain_check_result.txt
