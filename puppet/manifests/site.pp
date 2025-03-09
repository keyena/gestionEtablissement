node default {
  include profile::base
  include profile::java
  include profile::docker
}

node 'app-server' {
  include profile::base
  include profile::java
  include profile::docker
  include profile::spring_boot
}

node 'monitoring-server' {
  include profile::base
  include profile::docker
  include profile::elk
  include profile::prometheus
}

class base {
  package { ['curl', 'wget', 'git']:
    ensure => installed,
  }

  file { '/etc/timezone':
    ensure  => present,
    content => "Europe/Paris\n",
  }
}

class docker {
  package { 'docker-ce':
    ensure => installed,
  }

  service { 'docker':
    ensure => running,
    enable => true,
  }
}

class monitoring {
  package { ['prometheus-node-exporter', 'filebeat']:
    ensure => installed,
  }

  service { 'prometheus-node-exporter':
    ensure => running,
    enable => true,
  }

  service { 'filebeat':
    ensure => running,
    enable => true,
  }

  file { '/etc/filebeat/filebeat.yml':
    ensure  => present,
    content => template('monitoring/filebeat.yml.erb'),
    notify  => Service['filebeat'],
  }
}

class development {
  package { ['maven', 'openjdk-17-jdk']:
    ensure => installed,
  }
}

class production {
  package { 'openjdk-17-jre':
    ensure => installed,
  }

  cron { 'cleanup-logs':
    command => 'find /var/log -type f -mtime +7 -delete',
    user    => 'root',
    hour    => 2,
    minute  => 0,
  }
} 