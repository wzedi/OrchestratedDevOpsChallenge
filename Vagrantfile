# -*- mode: ruby -*-
# vi: set ft=ruby :

def provisioned?(vm_name='default', provider='virtualbox')
  File.exist?(".vagrant/machines/#{vm_name}/#{provider}/action_provision")
end

Vagrant.configure("2") do |config|
  config.vm.box = "ubuntu/xenial64"
  config.vm.synced_folder ".", "/opt/OrchestratedDevOpsChallenge"

  if !provisioned? || ARGV[1] == '--provision'
    print "Please enter your AWS credentials\n"
    print "Access Key ID: "
    accessKeyId = STDIN.gets.chomp
    print "Secret Access Key: "
    secretAccessKey = STDIN.gets.chomp
    print "\n"

    config.vm.provision "shell", path: "provision/vagrant/setup.sh", args: [accessKeyId, secretAccessKey]
  end
end
