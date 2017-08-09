#! /bin/bash

if [ ! -d /home/ubuntu/.aws ]
then
    mkdir /home/ubuntu/.aws
fi

touch /home/ubuntu/.aws/credentials
echo "[default]" > /home/ubuntu/.aws/credentials
echo "aws_access_key_id="$1 >> /home/ubuntu/.aws/credentials
echo "aws_secret_access_key="$2 >> /home/ubuntu/.aws/credentials
echo "region=ap-southeast-2" >> /home/ubuntu/.aws/credentials

sudo apt-get update -y

## Install the AWS CLI
sudo apt-get install awscli -y

## Install JDK for gradle
sudo apt-get install default-jdk -y
