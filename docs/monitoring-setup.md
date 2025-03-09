# Guide de déploiement du monitoring

## 1. Préparation
```bash
# Créer le namespace monitoring
kubectl apply -f monitoring/prometheus/prometheus-config.yml
```

## 2. Déploiement de Prometheus
```bash
# Déployer Prometheus
kubectl apply -f monitoring/prometheus/prometheus-deployment.yml

# Vérifier que Prometheus est en cours d'exécution
kubectl get pods -n monitoring
```

## 3. Déploiement de Grafana
```bash
# Déployer Grafana
kubectl apply -f monitoring/grafana/grafana-deployment.yml

# Vérifier que Grafana est en cours d'exécution
kubectl get pods -n monitoring
```

## 4. Accès aux interfaces
```bash
# Obtenir l'URL de Grafana
kubectl get svc grafana -n monitoring

# Accéder à Grafana
# URL: http://<EXTERNAL-IP>
# Identifiants par défaut:
# - Username: admin
# - Password: admin
```

## 5. Configuration des dashboards
1. Se connecter à Grafana
2. Aller dans "Dashboards" > "Import"
3. Importer le fichier `monitoring/grafana/dashboards/app-metrics.json`

## 6. Métriques disponibles
- CPU Usage (%)
- Memory Usage (bytes)
- Disk Usage (%)
- Request Rate (requests/second)
- Request Latency
- Error Rate

## 7. Alertes
Les alertes peuvent être configurées dans Grafana pour :
- CPU > 80%
- Mémoire > 80%
- Disque > 90%
- Taux d'erreur > 5%

## 8. Logs
Pour voir les logs :
```bash
# Logs de l'application
kubectl logs -f deployment/gestion-etablissement -n gestion-etablissement

# Logs de Prometheus
kubectl logs -f deployment/prometheus -n monitoring

# Logs de Grafana
kubectl logs -f deployment/grafana -n monitoring
``` 