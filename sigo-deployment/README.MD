

# Criando as imagens dos microserviços

É necessário ter o Maven instalado.

Acessar o repositório raiz do microserviço e rodar o seguinte comando:
```sh
$ mvn package
```

# Subindo o ambiente atraveś do Docker Compose


Criar os seguintes bancos de dados:

```sh
CREATE DATABASE sigo_partner;
GRANT ALL PRIVILEGES ON sigo_partner.* TO 'sigo';

CREATE DATABASE sigo_standard;
GRANT ALL PRIVILEGES ON sigo_standard.* TO 'sigo';
```
Após, rodar o seguinte comando no Docker.
```sh
 docker-compose up -d
```

# Subindo o ambiente atraveś do Terraform

Acessar o repositório do terraform.

```sh
$ terraform init
```


```sh
$ terraform apply
```

Acessar a pasta sigo-deployment

```sh
$ aws eks --region us-east-1 update-kubeconfig --name sigo-eks-Q0llbYh6 # nome do cluster
```

```sh
$ kubectl get svc
```

```sh
$ kubectl apply -f 
```

Implantando o serviço de Banco de Dados, responsável pela comunicação da AWS RDS (deve ser criada manualmente) com o Kubernetes:

```sh
$ kubectl apply -f mysql-service.yml
```

Implantando os microserviços:
```sh
$ kubectl apply -f sigo-partner
$ kubectl apply -f sigo-standard
$ kubectl apply -f sigo-process
```

Para visulaizar todos os pods implantados para o sigo:
```sh
$ kubectl get pods -n sigo
```

Para visulaizar os logs de um pod específico:
```sh
$ kubectl -n sigo logs -f sigo-partner-deployment-777b4b895d-rcjtf # Nome do pod
```


Confiuração do ALB

```sh
$ kubectl apply -f rbac-role.yaml
```

```sh
$ kubectl annotate serviceaccount -n kube-system alb-ingress-controller \
eks.amazonaws.com/role-arn=arn:aws:iam::[ARN]:role/eks-alb-ingress-controller # Informar o ARN
```

```sh
$ kubectl apply -f alb-ingress-controller.yml
```

```sh
$ kubectl apply -f ingress.yml
```

```sh
$ kubectl get ingress/sigo-ingress -n sigo
```

```sh
$ kubectl -n kube-system logs alb-ingress-controller-665fb4cc5d-gl26v  # Nome do POD do ALB
```
Para demais dúvidas ou detalhes, seguir a documentação da AWS: [ALB Ingress Controller on Amazon EKS
](https://docs.aws.amazon.com/eks/latest/userguide/alb-ingress.html)