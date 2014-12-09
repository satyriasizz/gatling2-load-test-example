Gatling 2 load tests
=========================

Use the application.conf file (src/test/resources/application.conf) to configure properties.

To start tests you need sbt installed on your computer: http://www.scala-sbt.org/download.html

Then you have installed sbt, you can go to project directory and run:

$ sbt gatling:test

If test result is successful, then you can see report in target/gatling/gatlingsimulation-xxxxx/index.html

Test could be unsuccessful if some of config values were incorrect (emails, tokens, serverUrl) so recheck it, if test result still unsuccessful - look at the logs to find errors.