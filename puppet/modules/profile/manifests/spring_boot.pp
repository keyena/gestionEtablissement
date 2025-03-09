class profile::spring_boot {
  file { '/opt/app':
    ensure => directory,
    owner  => 'app',
    group  => 'app',
    mode   => '0755',
  }

  docker::run { 'gestion-etablissement':
    image            => 'gestion-etablissement:latest',
    ports           => ['8080:8080'],
    env             => [
      'SPRING_PROFILES_ACTIVE=prod',
      'SPRING_DATASOURCE_URL=jdbc:mysql://mysql:3306/gestion_etablissement',
    ],
    restart_service => true,
    pull_on_start  => true,
  }

  file { '/etc/filebeat/filebeat.yml':
    ensure  => file,
    content => template('profile/filebeat.yml.erb'),
    notify  => Service['filebeat'],
  }
} 