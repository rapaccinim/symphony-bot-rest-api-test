# Orders Bot API example
## About
This simple projec demonstrates how to create a Symphony Bot that interacts with Symphony's REST APIs.

## Creating a Bot using Symphony Bot Generator
* you need to install 2 tools: *yeoman* and the *bot generator* itself, using the `npm install -g yo generator-symphony`
* then you create a dedicated project folder and you move into it
* you can finally launch Symphony Bot Generator: `yo symphony 2.0`
* the wizard will ask you some configuration-related questions:
	* pod host (e.g. `develop2.symphony.com`)
	* bot username (use the username that was assigned to you in the sandbox)
	* choose a `Bot Application`
	* `Java` will be the programming language
	* right now you will use Java without any kind of framework
	* choose `Maven` as build system
	* project `groupId`: something like `com.yourbank.foo`
	* project `artifactId`: e.g. `orders-bot`
	* package: you could use the same you gave to the `groupId`
* please note that the Bot Generator will create a pair of RSA keys, but you have to ignore them and use instead the ones that Symphony gave to you when creating the sandbox
* after creating the project, it's suggested to move to the project root:
	* the `config.yaml` file coming from the `resources` folder
	* the `rsa` keys folder, so that they are not compiled into the deployment artifact

## Configuration file
TBD: more description about the `config.yaml` file.

Considering that you moved the `config.yaml` to the project root, you need to change the following code in the `main`:
* before: `final SymphonyBdk bdk = new SymphonyBdk(loadFromClasspath("/config.yaml"));`
* after: `final SymphonyBdk bdk = new SymphonyBdk(loadFromFile("config.yaml"));`

## Java version
Go to the `pom.xml` and for the `maven-compiler-plugin` change the configuration:
* `<source>` to 11
* `<target>` to 11