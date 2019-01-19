# blue-spurs-dev-test
Development test for Blue Spurs

The application is using mave as the build tool. To build it just use:
`mvn clean install`

A file called blue-spurs-test.jar will be generated under ${YOUR_BASE_PATH}/target.

Deployment procedure:
* Create a directory on the destination server
* Copy the files ${YOUR_BASE_PATH}/target/blue-spurs-test.jar and ${YOUR_BASE_PATH}/script/blue-spurs-test.sh to the created directory.
* Give execution rights to the script copied on the previous step.
  * `chmod +x blue-spurs-test.sh`

Starting / Stopping the application:
* __Start__
  * Go to the deployed directory
  * Use the command: `./blue-spurs-test.sh start`
* __Stop__
  * Go to the deployed directory
  * Use the command: `./blue-spurs-test.sh stop`
