#!/bin/bash

		export JAEGER_AGENT_HOST

echo "Saving pets"
java -jar app.jar --cmd save -j samples/pet1.json
java -jar app.jar --cmd save -j samples/pet2.json
java -jar app.jar --cmd save -j samples/pet3.json
java -jar app.jar --cmd save -j samples/pet4.json
java -jar app.jar --cmd save -j samples/pet5.json
java -jar app.jar --cmd save -j samples/pet6.json
java -jar app.jar --cmd save -j samples/pet7.json
java -jar app.jar --cmd save -j samples/pet8.json
java -jar app.jar --cmd save -j samples/pet9.json
java -jar app.jar --cmd save -j samples/pet10.json


echo "Querying Pets"
java -jar app.jar --cmd getid -i 1
java -jar app.jar --cmd getid -i 2
java -jar app.jar --cmd getid -i 3
java -jar app.jar --cmd getid -i 4
java -jar app.jar --cmd getid -i 5
java -jar app.jar --cmd getid -i 6
java -jar app.jar --cmd getid -i 7
java -jar app.jar --cmd getid -i 8
java -jar app.jar --cmd getid -i 9
java -jar app.jar --cmd getid -i 10

echo "Querying by Status"
java -jar app.jar --cmd getstatus -s FOSTER
java -jar app.jar --cmd getstatus -s INSHELTER


