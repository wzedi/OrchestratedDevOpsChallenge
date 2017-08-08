#! /bin/bash

sudo apt update

## Install PIP, used to install AWS CLI
sudo apt -y install python-pip

## Ensure PIP is at the latest version
pip install --upgrade pip

## Install the AWS CLI
pip install --upgrade --user awscli
