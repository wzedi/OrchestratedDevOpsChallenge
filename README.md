# OrchestratedDevOpsChallenge

## Principles Applied

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


## Recommendations
