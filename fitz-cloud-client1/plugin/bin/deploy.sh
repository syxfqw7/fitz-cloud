#!/bin/bash
#
# description: Starts and stops the appliction
# author:jack.xue

#RUNNING_USER=appadmin
ADATE=`date +%Y%m%d%H%M%S`
APP_NAME=${project.build.finalName}
APP_PROFILE=dev

cd ..
APP_HOME=`pwd`
echo "APP_HOME:$APP_HOME"

if [ ! -d "$APP_HOME/logs" ];then
  mkdir $APP_HOME/logs
fi

if [ -n "$2" ];then
  APP_PROFILE=$2
fi

echo ">>>>>> [app_profile]: $APP_PROFILE" 

LOG_PATH=$APP_HOME/logs/$APP_NAME.out
GC_LOG_PATH=$APP_HOME/logs/gc-$APP_NAME-$ADATE.log
echo ">>>>>> LOG_PATH:$LOG_PATH"

# JMX监控需用到
#JVM参数
JVM_OPTS="-Dspring.profiles.active=$APP_PROFILE -Dname=$APP_NAME -Xms1024M -Xmx1024M -XX:+HeapDumpOnOutOfMemoryError -XX:+PrintGCDateStamps -Xloggc:$GC_LOG_PATH -XX:+PrintGCDetails -XX:NewRatio=1 -XX:SurvivorRatio=30 -XX:+UseParallelGC -XX:+UseParallelOldGC"
JAR_FILE=$APP_HOME/$APP_NAME.jar

pid=0
start(){
  checkpid
  if [ ! -n "$pid" ]; then
    nohup java -jar $JVM_OPTS $JAR_FILE > $LOG_PATH 2>&1 &
    echo "#####################################"
    echo "启动完成，按CTRL+C退出日志界面即可>>>>>"
    echo "#####################################"
    sleep 1s
    tail -f $LOG_PATH
  else
      echo ">>>>>> $APP_NAME is already started! PID: $pid"   
  fi

}

status(){
   checkpid
   if [ ! -n "$pid" ]; then
     echo "$APP_NAME not runing"
   else
     echo "$APP_NAME runing PID: $pid"
   fi 
}

checkpid(){
    pid=`ps -ef |grep $JAR_FILE |grep -v grep |awk '{print $2}'`
}

stop(){
    checkpid
    if [ ! -n "$pid" ]; then
     echo ">>>>>> $APP_NAME is not runing"
    else
      echo ">>>>>> $APP_NAME has stopped..."
      kill -9 $pid
    fi 
}

restart(){
    stop 
    sleep 1s
    start
}

case $1 in  
          start) start;;  
          stop)  stop;; 
          restart)  restart;;  
          status)  status;;   
              *)  echo "require start|stop|restart|status"  ;;  
esac 

