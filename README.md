# drools-atm-example

# Datos de ejemplo
Pueden encontrarse en BaseData.scala y son

| nro | pin | banco | saldo | valida | intentos | fecha|
|-----|-----|-------|-------|--------|----------|------|
|1    |1111 |Banco Union|2000|true| 1|31-09-2020|
|2    |2222 |Banco Union|0.0 |true| 2|31-09-2020|
|3|3333|Banco Union|1000|true|0|31-09-2020|
|4|4444|Banco Ganadero|200.20|false|3|31-09-2020|
|5|5555|Banco Nacional de Bolivia|200.20|true|0|31-01-2016|
|6|6666|Banco Sol|1200.23|true|0|31-09-2020|
|7|7777|Banco Mercantil Santa Cruz|8000|true|0|31-09-2020|
|8|8888|Banco Mercantil Santa Cruz|10000|true|0|31-09-2020|
|9|9999|Banco Nacional de Bolivia|0.20|true|0|31-09-2020|
|10|1010|Banco Sol|100.20|true|0|31-09-2020|

# Requisitos

Se requiere Java 8

# Ejecucion desde las fuentes

Se requiere sbt

Dentro de la consola de dos o bash, ejecutar: sbt run

# Empaquetamiento

Se requiere sbt

Dentro de la consola de dos o bash, ejecutar: sbt assembly

# Generacion de un instalador

Se requiere sbt

Dentro de la consola de dos o bash ejecutar: jdkPackager:packageBin