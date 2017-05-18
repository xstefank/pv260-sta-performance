#!/bin/bash

cd brainmethodcheck

# builde project
mvn clean install

echo "Runnning checks..."
java -cp target/brain-method-check-1.0-SNAPSHOT.jar:lib/checkstyle-7.7-all.jar \
com.puppycrawl.tools.checkstyle.Main -c src/main/resources/brain_check.xml \
-f plain -o brain_check_result.txt src/main/java/cz/muni/fi/pv260/chkstyle/example/ExampleBrainMethodClass.java

java -cp target/brain-method-check-1.0-SNAPSHOT.jar:lib/checkstyle-7.7-all.jar \
com.puppycrawl.tools.checkstyle.Main -c src/main/resources/brain_check_passing_for_example_class.xml \
-f plain -o passing_brain_check_result.txt src/main/java/cz/muni/fi/pv260/chkstyle/example/ExampleBrainMethodClass.java

echo -e 'Done.\n'

echo -e 'Results:\n'

echo 'Check1:'
cat brain_check_result.txt
echo -e '\nCheck2:'
cat passing_brain_check_result.txt
