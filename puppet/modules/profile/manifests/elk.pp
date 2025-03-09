class profile::elk {
  file { '/opt/elk':
    ensure => directory,
    owner  => 'elk',
    group  => 'elk',
    mode   => '0755',
  }

  file { '/opt/elk/docker-compose.yml':
    ensure  => file,
    source  => 'puppet:///modules/profile/elk/docker-compose.yml',
    require => File['/opt/elk'],
  }

  docker_compose { 'elk':
    compose_files => ['/opt/elk/docker-compose.yml'],
    ensure       => present,
    require      => File['/opt/elk/docker-compose.yml'],
  }

  file { '/etc/logstash/conf.d/spring.conf':
    ensure  => file,
    content => template('profile/logstash/spring.conf.erb'),
    notify  => Docker_compose['elk'],
  }
} 