class profile::base {
  package { ['curl', 'wget', 'git']:
    ensure => installed,
  }

  file { '/etc/timezone':
    ensure  => file,
    content => "Europe/Paris\n",
  }

  service { 'ntp':
    ensure => running,
    enable => true,
  }

  file { '/etc/security/limits.conf':
    ensure => file,
    source => 'puppet:///modules/profile/limits.conf',
  }

  file { '/etc/sysctl.d/99-sysctl.conf':
    ensure => file,
    source => 'puppet:///modules/profile/sysctl.conf',
  }
} 