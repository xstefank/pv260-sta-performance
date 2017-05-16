#!/bin/bash
echo 'Extracting ...'
cd ../../../out/artifacts/brainmethodcheck_jar/
jar xf brainmethodcheck.jar
java -cp brainmethodcheck.jar:checkstyle-7.7-all.jar com.puppycrawl.tools.checkstyle.Main -c brain_check.xml -f plain -o brain_check_result.txt ExampleBrainMethodClass.java
echo 'Done.'
echo
echo 'Results:'
echo
cat brain_check_result.txt
echo
cd ../../../src/main/resources/
