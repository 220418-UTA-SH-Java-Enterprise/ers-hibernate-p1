sudo yum install java-1.8.0-openjdk-devel -y
java -version
sudo yum install maven -y
mvn -version
sudo yum install git -y
git --version
sudo amazon-linux-extras install tomcat8.5 -y
tomcat version
git clone https://github.com/220418-UTA-SH-Java-Enterprise/ers-hibernate-p1.git
cd ers-hibernate-p1/EmployeeReimbursmentSystem
ls
mvn package
ls
pwd
ls target/
cd ..
sudo cp target/EmployeeReimbursmentSystem.war /var/lib/tomcat/webapps/
sudo service tomcat start
sudo service tomcat status



Path: /home/ec2-user/ers-hibernate-p1/EmployeeReimbursmentSystem/target/EmployeeReimbursmentSystem.war
 
Endpoint URL: http://18.208.130.214:8080/EmployeeReimbursmentSystem