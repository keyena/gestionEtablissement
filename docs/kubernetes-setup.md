# Guide d'installation Kubernetes

## 1. Installation de Minikube
```bash
# Windows (avec chocolatey)
choco install minikube

# Linux
curl -LO https://storage.googleapis.com/minikube/releases/latest/minikube-linux-amd64
sudo install minikube-linux-amd64 /usr/local/bin/minikube
```

## 2. Installation de kubectl
```bash
# Windows (avec chocolatey)
choco install kubernetes-cli

# Linux
curl -LO "https://dl.k8s.io/release/$(curl -L -s https://dl.k8s.io/release/stable.txt)/bin/linux/amd64/kubectl"
sudo install -o root -g root -m 0755 kubectl /usr/local/bin/kubectl
```

## 3. Démarrer Minikube
```bash
minikube start
```

## 4. Vérifier l'installation
```bash
kubectl version
minikube status
``` 