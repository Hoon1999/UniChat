#!/usr/bin/env bash

# 내 프로젝트의 위치
REPOSITORY=/opt/unichat

APP_NAME=unichat
JAR_NAME=$(ls $REPOSITORY/build/libs/ | grep '.jar' | tail -n 1)
JAR_PATH=$REPOSITORY/build/libs/$JAR_NAME

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

echo "> 새 애플리케이션 배포를 시작합니다."

echo "> JAR PATH: $JAR_PATH"

echo "> $JAR_PATH 에 실행권한 추가"
chmod +x $JAR_PATH

echo "> $JAR_PATH 실행"

nohup java -Duser.timezone=Asia/Seoul -jar $JAR_PATH >> $REPOSITORY/nohup.out 2>&1 &
