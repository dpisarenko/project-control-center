mvn clean
mvn release:clean
git commit -am "Automatic commit before mvn release:prepare"
mvn release:prepare
mvn release:perform
