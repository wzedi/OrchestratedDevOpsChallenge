# OrchestratedDevOpsChallenge

## AWS User
* You will need a valid AWS IAM user account
* Obtain an active access key ID and secret access key
* Attach the AdminsitratorAccess managed policy to this user
* Create a Key Pair in the EC2 Management Console and install the priavet key on your localhost

## Project setup
* You will need a computer with git, vagrant and VirtualBox installed and correctly configured.
* Clone the project from github: git@github.com:wzedi/OrchestratedDevOpsChallenge.git
* Launch the vagrant virtual machine: vagrant up - you will be prompted to enter the AWS Access Key ID and Secret Access Key when launching the VM
* Connect to the vagrant machine: vagrant ssh
* Change directory to /opt/OrchestratedDevOpsChallenge
* Use gradle to build and test the application: ./gradlew test or ./gradlew build

## Provision AWS Stacks
* Provision the stacks with ./scripts/provision - the script will prompt for the Key Pair name created in the AWS User section above
* Monitory stack provisioning in the Cloud Formation console

## Deploy the Application
* After a successful build deploy the application with ./scripts/deploy
* Monitor deployment progress in the Code Deploy console
* Find the CompanyNews servers in the list of EC2 servers in the management console and get the public IP for the server

## Run the application
* Browse to http://{public IP}/CompanyNew to run the application

## Principles Applied
* Every attempt has been made to keep the code and templates DRY - not repeating code
* SOLID OO principles have been applied

## Justification for decisions
* Static content is stored in an Amazon S3 bucket
* Dynamic content is delivered through a Java app server running on an Amazon EC2 instance
* The training environment delivers static content directly from S3 while the production environments use CloudFront for improved performance
* The full public release environment includes DynamoDB for scalable object storage. While Prevayler is suitable for testing, prototyping and smaller systems it will not scale for a wordlwide production system. Prevayler would be constrained by the physical capacity of the server it is running on while DynamoDB can scale to support very large datasets while providing consistent throughput.
* In production the app servers are deployed into an auto scaling group in a private subnet with traffic routed to the servers through an ELB. The auto scaling group provides continuity through single instance failures and the ELB with scaling group facilitates scaling.
* The production environments include Bastion servers in the public subnet. These servers act as SSH jump boxes to the app server EC2 instances in the private subnet. There is one bastion server per AZ. While this is not strictly required it does ensure SSH access will be available in the unikely event of an AZ failure. For cost optimisation the bastion servers could be stopped and only started when SSH access is required.
* The production environments include NAT gateways for Internet access from the EC2 servers in the private subnet. This allows for OS package and app dependency installation. There is one NAT gateway per AZ for high availability. There is an option to use the bastion EC2 instances as NAT instances with a custom high availability script.
* For the Java app added indirection for the storage technology so that Prevayler can be replaced with alternative technologies, like AWS CLI with DynamoDB without requiring changes in the application code.

## Tool Selection and Configuration
* gradle - build tool and dependency manager. Generated wrapper script for project independence across dev environments.
* vagrant - virtual maching creating a portable development environment
* AWS Cloud Formation - infrastructure as code
* AWS Code Deploy - application deployment system
