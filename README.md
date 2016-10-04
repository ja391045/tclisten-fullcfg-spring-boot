# Spring Boot Tomcat Listener's Full Config Starter #

A spring boot starter which gives the user very fine grained control over Tomcat's APR and HTTP connectors via properties.

## Contents ##

* [Intended Use Case](#intended-use-case)
* [Requirements](#requirements)
* [Quick Start](#quick-start)
* [Default Behavior](#default-behavior)
* [Support Matrix](#support-matrix)
* [Documentation](#documentation)
* [In-Depth Examples](#in-depth-examples)

## Intended Use Case ##

This starter should be used when one requires very fine grained control over embedded Tomcat connectors, but wishes that control to remain externalized.  This is a very narrow use case, and if you are usure about whether or not you need the fine grained control offered, it's likely that it is not needed.

In most cases, Spring Boot's standard `server.*` and `server.tomcat.*` properties give embedded Tomcat users enough external configuration options to meet their needs.  In rare cases, such as when multiple connectors with different options are required, more control over Tomcat's configuration is needed.  To that end, Spring Boot provides programatic methods of fine-tuning Tomcat.  The downside to the programatic approach is that the fine-tuning isn't inherently external.  This starter remedies that sitution by providing externalized, `application.properties` based configuration for any and all tuning which is supported by Tomcat 8.0.

## Requirements ##

See [Support Matrix](#support-matrix) below.

## Quick Start ##


```xml
<!-- pom.xml -->
<dependency>
  <groupId>com.github.ja391045<groupId>
  <artifactId>tclisten-fullcfg-spring-boot-starter<artifactId>
  <version>1.3.0.RELEASE</version>
</dependency>
```

application.yaml: An example of a single, HTTP Blocking I/O connector, listening on port 8080.  

```yaml
---
tc:
  nioHttpConnectors:
    - address: 0.0.0.0
      port: 8080
```

application.yaml: An example of a NIO2 HTTP connector listening on port 8080 of an interface, and an AJP connector, listening on port 8080 of localhost.

```yaml
---
tc:
  nio2HttpConnectors:
    - address: myhost.mydomain.com
      port: 8080
  nio2AjpConnectors:
  	- address: localhost
  	  port: 8080
```

application.yaml: An example of a BIO HTTP listener on port 8080, redirecting to an APR/Native SSL listener on port 8443.

```yaml
---
tc:
  bioHttpConnectors:
    - address: 0.0.0.0
    - port: 8080
    - redirectPort: 8443
  aprHttpConnectors:
    - address: 0.0.0.0
      port: 443
      secure: true
      scheme: https
      sslEnabled: true
      ssl:
        SSLCertificateKeyFile: path/to/my-key.pem
        SSLCertificateFile: path/to/my-cert.pem
```

Finally, an example of a NIO2 SSL listener, with SSL Client Authentication.

```yaml
---
tc:
  nio2HttpConnectors:
  - address: 0.0.0.0
    port: 8443
    scheme: https
    secure: true
    sslEnabled: true
    ssl:
      clientAuth: true
      keystoreFile: /path/to/my-keystore.jks
      keystorePass: mykeypass
      keyAlias: my-server-certificate
      truststoreFile: /path/to/my-cacerts.jks
      truststorePass: my-cacerts-password
```

## Default Behavior ##

If no `tc.*` properties are supplied in `application.properties` or external configuration, the following defaults are assumed.  You may note that these defaults exactly mirror a vanilla server.xml configuration from a binary distribution of Tomcat 8.

* If APR/Native is available.
    * An APR AJP listener binds to 0.0.0.0:8009 with a port 8443 redirect.
    * An APR HTTP listener binds to 0.0.0.0:8080 with a port 8443 redirect.
* If APR/Native is absent.
    * A NIO AJP listener binds to 0.0.0.0:8009 with a port 8443 redirect.
    * A NIO HTTP listener binds to 0.0.0.0:8080 with a port 8443 redirect.
 
## Support Matrix ##

* 1.3.0.RELEASE
    * Java 1.8
    * Spring Boot 1.3.0
    * Embedded Tomcat 8
    * \(Optional\) APR/Native 1.2


## Documentation ##

Configuring embedded tomcat connectors all takes place under `tc.*` properties.   Under `tc`, there are 8 main sub-properties, each of which correspond to a main "type" of Tomcat connector.

```yaml
tc:
  bioHttpConnectors:
  bioAjpConnectors:
  nioHttpConnectors:
  nioAjpConnectors:
  nio2HttpConnectors:
  nio2AjpConnectors:
  aprHttpConnectors:
  aprAjpConnectors:
```

Under each `tc.\*Connectors` heading, a list of maps should be present.  Each list element represents a separate connector of the heading type.  And the map elements inside the list elements represent individual properties for the separate connector.  All properties  documented in the [Apache Tomcat 8 Configuration Reference](https://tomcat.apache.org/tomcat-8.0-doc/config/http.html) are supported.

To continue with our yaml example, the following snippet outlines the arrangement of property data within the defined YAML structure.

```yaml
tc:
  nioHttpConnectors:  
    - connector1-property1: value1
      connector1-property2: value2
      connector1-property3: value3
    - connector2-property1: value1
      connector2-property2: value2
      connector2-property3: value3
  nio2AjpConnectors:
    - connector1-property1: value1
      connector1-property2: value2
    - connector2-property1: value1
```

Finally, certain connectors have even more nested elements.  All HTTP type connectors may utilize the `ssl` sub-element.  The NIO2 type connectors, both HTTP & AJP, have a `socket` sub-element, and NIO type connectors have a `socket` and `selectorPool` sub-element.

```yaml
tc:
    bioHttpConnectors:
      - connector1-property1: value1
        ssl:
      	  connector1-ssl-property1: value
    nioHttpConnectors:
      - connector1-property1: value1
        ssl:
          connector1-ssl-property1: value
        socket:
          connector1-socket-property1: value
        selectorPool:
          connector1-selectorpool-property1: value
```

Any default values listed in  [Apache Tomcat 8 Configuration Reference](https://tomcat.apache.org/tomcat-8.0-doc/config/http.html) are retained as the default value here, so not every property need be specified.  In short, if you must specify a value in Tomcat's server.xml, then you must specify the value here.  Otherwise, the default may be accepted.

Finally, a quick note on APR connectors.   If an APR type connector is specified by configuration, but the [APR/Native library](https://tomcat.apache.org/tomcat-8.0-doc/apr.html) is not installed, or is not available, an error will occur on application startup.  Right now, this is a feature, not a bug.  If a production specification dictates that APR/Native must be used, it is important that the use of the connector is assured, rather than auto-selecting another connector in the absence of APR.  On the TODO list is the addition of the HTTP/1.1 & AJP/1.3 protocols as top level connectors.  These protocols when specified should auto-switch between APR when available, or NIO if APR is absent.


## In-Depth Examples ##

Hardened SSL with non-secure redirection (Pure Java NIO).

```yaml
tc:
  nioHttpConnectors:
    - allowTrace: false
      enableLookups: false
      address: 0.0.0.0
      port: 8080
      maxConnections: 1000
      redirectPort: 8443
    - allowTrace: false
      enableLookups: false
      address: 0.0.0.0
      port: 8443
      scheme: https
      secure: true
      sslEnabled: true
      ssl:
        sslEnabledProtocols: TLSv1.2
        sslProtocol: TLSv1.2
        ciphers: ECDHE-ECDSA-AES256-GCM-SHA384:ECDHE-RSA-AES256-GCM-SHA384:ECDHE-ECDSA-CHACHA20-POLY1305:ECDHE-RSA-CHACHA20-POLY1305:ECDHE-ECDSA-AES128-GCM-SHA256:ECDHE-RSA-AES128-GCM-SHA256:ECDHE-ECDSA-AES256-SHA384:ECDHE-RSA-AES256-SHA384:ECDHE-ECDSA-AES128-SHA256:ECDHE-RSA-AES128-SHA256
        useServerCipherSuitesOrder: true
        keystoreFile: /path/to/my-keystore.jks
        keystorePass: somepassword
        keyAlias: my-cert-alias
```

Hardened SSL with non-secure redirection (APR/Native)

```yaml
tc:
  aprHttpConnectors:
    - enableLookups: false
      allowTrace: false
      address: 0.0.0.0
      port: 8080
      redirectPort: 8443
    - enableLookups: false
      allowTrace: false
      address: 0.0.0.0
      port: 8443
      scheme: https
      secure: true
      sslEnabled: true
      ssl:
        SSLProtocol: TLSv1.2
        SSLDisableSessionTickets: true
        SSLHonorCipherOrder: true
        SSLCipherSuite: ECDHE-ECDSA-AES256-GCM-SHA384:ECDHE-RSA-AES256-GCM-SHA384:ECDHE-ECDSA-CHACHA20-POLY1305:ECDHE-RSA-CHACHA20-POLY1305:ECDHE-ECDSA-AES128-GCM-SHA256:ECDHE-RSA-AES128-GCM-SHA256:ECDHE-ECDSA-AES256-SHA384:ECDHE-RSA-AES256-SHA384:ECDHE-ECDSA-AES128-SHA256:ECDHE-RSA-AES128-SHA256
        SSLCertificateFile: /path/to/my-cert.pem
        SSLCertificateKeyFile: /path/to/my-key.pem
        SSLPassword: mykeypassphrase
```
