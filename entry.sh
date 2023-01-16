#!/bin/sh

set -e
nombrePesona=${nombre}
sed -i "s|NOMBRE|$nombrePesona|g" /usr/src/myapp/prac_variables_entorno
export texto=$(cat prac_variables_entorno)
java -jar /usr/src/myapp/demo-Variable.jar

exec "$@"
