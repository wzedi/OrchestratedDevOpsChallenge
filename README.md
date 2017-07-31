# OrchestratedDevOpsChallenge

## Principles Applied

## Justification for decisions
* Three environments have been created:
  * A training environment - not expected to scale or perform at production levels
  * A limited release environment - supports subsecond response times and continuity through single server failures
  * A full public release environment - adds scalable object storage for full scale production system
* The training environment delivers static content directly from S3 while the production environments use CloudFront for improved performance
* The full public release environment includes DynamoDB for scalable object storage. While prevayler is suitable for testing, prototyping and smaller systems it will not scale.

## Tool Selection and Configuration

## Recommendations
