#!/bin/bash

export MYSQL_HOST=192.168.x.x
export MYSQL_USER=x
export MYSQL_PASS=x

mvn clean install

unset MYSQL_HOST
unset MYSQL_USER
unset MYSQL_PASS