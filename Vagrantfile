# -*- mode: ruby -*-
# vi: set ft=ruby :

Vagrant.configure("2") do |config|
  config.vm.box = "ubuntu/xenial64"
  config.vm.provision "shell", path: "provision/vagrant/setup.sh"
  config.vm.synced_folder ".", "/opt/OrchestratedDevOpsChallenge"
end
