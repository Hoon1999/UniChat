#!/usr/bin/env bash

# 내 프로젝트의 위치
REPOSITORY=/opt/unichat

APP_NAME=unichat
JAR_NAME=$(ls $REPOSITORY/build/lib/ | grep '.jar' | tail -n 1)
JAR_PATH=$REPOSITORY/build/lib/$JAR_NAME

echo "> 현재 구동 중인 애플리케이션 pid 확인"

CURRENT_PID=$(pgrep -f $APP_NAME)

echo "현재 구동 중인 애플리케이션 pid: $CURRENT_PID"

if [ -z "$CURRENT_PID" ]; then
  echo "현재 구동 중인 애플리케이션이 없으므로 종료하지 않습니다."
else
  echo "> kill -9 $CURRENT_PID"
  kill -9 $CURRENT_PID
  sleep 5
fi

echo "> 새 애플리케이션 배포"

echo "> JAR NAME: $JAR_NAME"

echo "> $JAR_NAME 에 실행권한 추가"

chmod +x $JAR_NAME

echo "> $JAR_NAME 실행"

nohup java -Duser.timezone=Asia/Seoul -jar $JAR_NAME >> $REPOSITORY/nohup.out 2>&1 &
