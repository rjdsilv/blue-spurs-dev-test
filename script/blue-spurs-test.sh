#!/bin/bash

function showHelp {
	echo "################################################## :: Help."
	echo "Use ./blue-spurs-test.sh [start | stop | restart | help] &"
}

function startServer {
	nohup java -jar blue-spurs-test.jar > /dev/null 2>&1 &
}

function stopServer {
	PID=`ps -ef | grep "blue-spurs-test.jar" | grep -v grep | sed -r 's/([^ ]+ +){1}([^ ]+).*/\2/'`

	if [[ -n "$PID" ]]; then
		echo "########## :: Stopping the Application running under the PID = $PID."
		`kill -n 15 ${PID}`
		for i in `seq 1 5`
		do
			echo -ne ". "
			sleep 1s
		done
		echo "."
		echo "########## :: Application stopped successfully."
	else
		echo "##########cd tart :: Application already stopped."
	fi;
}

case $1 in
start)
	startServer
	;;
stop)
	stopServer
	;;
restart)
	stopServer
	startServer
	;;
help)
	showHelp
	;;
esac