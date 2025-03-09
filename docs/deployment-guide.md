# Guide de déploiement Kubernetes

## 1. Préparation
```bash
# Vérifier que minikube est démarré
minikube status

# Si non démarré, le démarrer
minikube start
```

## 2. Création du namespace et des configurations
```bash
# Créer le namespace
kubectl apply -f k8s/namespace.yml

# Créer la configuration
kubectl apply -f k8s/configmap.yml
```

## 3. Déploiement de PostgreSQL
```bash
# Déployer PostgreSQL
kubectl apply -f k8s/postgres.yml

# Vérifier que PostgreSQL est démarré
kubectl get pods -n gestion-etablissement
```

## 4. Déploiement de l'application
```bash
# Déployer l'application
kubectl apply -f k8s/deployment.yml

# Vérifier le déploiement
kubectl get deployments -n gestion-etablissement
kubectl get pods -n gestion-etablissement
```

## 5. Accéder à l'application
```bash
# Obtenir l'URL de l'application
minikube service gestion-etablissement -n gestion-etablissement
```

## 6. Surveillance
```bash
# Voir les logs de l'application
kubectl logs -f deployment/gestion-etablissement -n gestion-etablissement

# Voir l'état des pods
kubectl get pods -n gestion-etablissement -w

# Voir les détails d'un pod (remplacer POD_NAME)
kubectl describe pod POD_NAME -n gestion-etablissement
```

## 7. Mise à jour de l'application
```bash
# Mettre à jour l'image
kubectl set image deployment/gestion-etablissement \
  gestion-etablissement=registry.gitlab.com/your-group/gestion-etablissement:new-version \
  -n gestion-etablissement

# Vérifier le rollout
kubectl rollout status deployment/gestion-etablissement -n gestion-etablissement
```

## 8. Nettoyage
```bash
# Supprimer tout
kubectl delete namespace gestion-etablissement
``` 