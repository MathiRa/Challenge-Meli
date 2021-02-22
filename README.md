# challenge
[![SonarCloud](https://sonarcloud.io/images/project_badges/sonarcloud-white.svg)](https://sonarcloud.io/dashboard?id=MathiRa_challenge)
[![Build Status](https://travis-ci.com/MathiRa/challenge.svg?branch=main)](https://travis-ci.com/MathiRa/challenge)
[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=MathiRa_challenge&metric=bugs)](https://sonarcloud.io/dashboard?id=MathiRa_challenge)
[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=MathiRa_challenge&metric=code_smells)](https://sonarcloud.io/dashboard?id=MathiRa_challenge)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=MathiRa_challenge&metric=coverage)](https://sonarcloud.io/dashboard?id=MathiRa_challenge)
[![Duplicated Lines (%)](https://sonarcloud.io/api/project_badges/measure?project=MathiRa_challenge&metric=duplicated_lines_density)](https://sonarcloud.io/dashboard?id=MathiRa_challenge)
[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=MathiRa_challenge&metric=sqale_rating)](https://sonarcloud.io/dashboard?id=MathiRa_challenge)
[![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=MathiRa_challenge&metric=security_rating)](https://sonarcloud.io/dashboard?id=MathiRa_challenge)
[![Technical Debt](https://sonarcloud.io/api/project_badges/measure?project=MathiRa_challenge&metric=sqale_index)](https://sonarcloud.io/dashboard?id=MathiRa_challenge)
[![Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=MathiRa_challenge&metric=vulnerabilities)](https://sonarcloud.io/dashboard?id=MathiRa_challenge)

[![Quality gate](https://sonarcloud.io/api/project_badges/quality_gate?project=MathiRa_challenge)](https://sonarcloud.io/dashboard?id=MathiRa_challenge)

## Tech

- [Java 11](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
- [Spring Boot](https://spring.io/projects/spring-boot)
- [Redis](https://redis.io/)
- [GKE](https://cloud.google.com/kubernetes-engine?hl=es)
- [Travis CI](https://travis-ci.org/)

## Example

```curl -X POST -H 'Content-Type: application/json' -i 'http://35.226.162.40/mutant' --data '{"dna":["AAAAAA","AAGTGC","TTATGT","AGAAGG","CCTCTA","TCACTG"]}'```

```curl -X GET -i 'http://35.226.162.40/stats'```

El build esta automatizado asi como tambien el análisis en SonarCloud a través de Travis CI. Como futura mejora se podria incluir el deploy, actualmente se hace manualmente con la herramienta kubectl.


