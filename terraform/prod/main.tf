provider "aws" {
  region = "eu-west-3"
}

# VPC Configuration
resource "aws_vpc" "main" {
  cidr_block = "10.0.0.0/16"
  
  tags = {
    Name = "prod-vpc"
    Environment = "production"
  }
}

# EKS Cluster
resource "aws_eks_cluster" "prod" {
  name     = "prod-cluster"
  role_arn = aws_iam_role.eks_cluster.arn

  vpc_config {
    subnet_ids = aws_subnet.private[*].id
  }
}

# Node Group
resource "aws_eks_node_group" "prod" {
  cluster_name    = aws_eks_cluster.prod.name
  node_group_name = "prod-nodes"
  node_role_arn   = aws_iam_role.eks_nodes.arn
  subnet_ids      = aws_subnet.private[*].id

  scaling_config {
    desired_size = 2
    max_size     = 4
    min_size     = 1
  }

  instance_types = ["t3.medium"]
}

# RDS Instance
resource "aws_db_instance" "prod" {
  identifier        = "prod-db"
  engine            = "postgres"
  engine_version    = "14"
  instance_class    = "db.t3.medium"
  allocated_storage = 20
  
  username = var.db_username
  password = var.db_password

  vpc_security_group_ids = [aws_security_group.db.id]
  db_subnet_group_name   = aws_db_subnet_group.prod.name
}

# ElasticSearch for ELK Stack
resource "aws_elasticsearch_domain" "prod" {
  domain_name           = "prod-logs"
  elasticsearch_version = "7.10"

  cluster_config {
    instance_type = "t3.small.elasticsearch"
  }

  ebs_options {
    ebs_enabled = true
    volume_size = 10
  }
} 