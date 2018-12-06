#!/bin/sh
#path
BASEPATH="/home/chanct/job/tj_stat"
#jarName
CRONJOBJAR="tj-stat-job.jar"

#kill
pid=`ps -ef | grep $CRONJOBJAR|grep -v grep|awk '{print $2}'`

if [ "$pid" = "" ];then
   echo "hn-manager-job service is not running"
else
   kill -9 $pid
fi
vpid=`ps -ef | grep $CRONJOBJAR|grep -v grep|awk '{print $2}'`
if [ "$vpid" = "" ];then
   echo "kill hn-manager-job service success!old_pid=$pid"
else
   echo "kill hn-manager-job service failed!"
fi

#start
java -jar $BASEPATH/$CRONJOBJAR >/dev/null 2>&1 &

vvpid=`ps -ef | grep $CRONJOBJAR|grep -v grep|awk '{print $2}'`
if [ "$vvpid" = "" ];then
   echo "startup hn-manager-job service failed!"
else
   echo "startup hn-manager-job service success!new_pid=$vvpid"
fi
