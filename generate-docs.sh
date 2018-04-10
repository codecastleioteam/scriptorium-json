mvn clean javadoc:javadoc
rm -rf gh-pages/apidocs/$1
mv target/site/apidocs gh-pages/apidocs/$1
