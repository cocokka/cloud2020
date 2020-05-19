#Install consul on CentOS7
##1. download: wget https://releases.hashicorp.com/consul/1.6.1/consul_1.6.1_linux_amd64.zip
##2. unzip consul_1.6.1_linux_amd64.zip
##3. if no unzip, then install it: yum -y install unzip
##4. ./consul agent -dev -ui -node=consul-dev -client=192.168.25.128
##5. visit link: http://192.168.25.128:8500
##6. close firewall if needed: systemctl stop firewalld