#!/bin/bash

apt-get update
apt-get install -y git
apt-get install -y wget
apt-get install -y libxss1 libappindicator1 libindicator7
wget https://dl.google.com/linux/direct/google-chrome-stable_current_amd64.deb
apt-get install -y ./google-chrome*.deb

cd ../
ls -l

mkdir "petclinicApp"
cd petclinicApp

git clone https://gitlab.com/a.leguidec/conduite-de-tests-di5
cd conduite-de-tests-di5

if [[ -f "pom.xml" ]] ;
then
    mvn package
    mvn spring-boot:run &
    cd ../../
    cd petclinic-functional-tests-di5/
    ls -l
    if [[ -f "pom.xml" ]] ;
    then
      mvn clean test -Dsuite=testng
    else
      wrong directory
    fi
    exit 0
else
  exit 1
fi