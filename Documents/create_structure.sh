#!/bin/bash

#set -o errexit


create_index () {
  echo echo "${2:?}" > "${1:?}"/index.txt
}


ORIGIN='/home/akira/IdeaProjects/subgrup-prop12.5'
DESTINATION='/home/akira/Documents'
FOLDER_NAME='PROP_ENTREGA_FINAL'
PROGRAM_NAME="layout_generator"

cd "${ORIGIN:?}"

if [ -d "${DESTINATION:?}"/"${FOLDER_NAME:?}" ]; then
  rm -r "${DESTINATION:?}"/"${FOLDER_NAME:?}"
fi

echo "${DESTINATION:?}"/"${FOLDER_NAME:?}"

mkdir "${DESTINATION:?}"/"${FOLDER_NAME:?}"


cd "${DESTINATION:?}"/"${FOLDER_NAME:?}"
echo "Allue Mengual, Nil
Bravo Melia, Andreu
Cobos Navarro, Ivan
Granados Martin, Arnau

nil.allue@estudiantat.upc.edu
andreu.bravo@estudiantat.upc.edu
ivan.cobos@estudiantat.upc.edu
arnau.granados@estudiantat.upc.edu
" > equip.txt


mkdir ./FONTS
create_index FONTS "Classes de model i Controlador de Domini i Driver de Funcionalitat
"


mkdir ./DOCS
create_index DOCS "DOCS:

Conté tota la documentació del projecte:
 - diagrama de casos d'us
 - diagrama de classes de model
 - doc amb: descripcio dels casos d'us, breu descripcio dels atributs i metodes de les classes de model, relacio de les classes implementades per cada membre de l'equip, descripcio de les estructures de dades i algorismes emprats per implementar les funcionalitats de l'entrega.
 - javadoc
"

mkdir ./EXE
create_index EXE "Directori de sortida pels .jar (driver ja compilat) i els .class . Tots els arxius sortiran un cop s'hagi compilat amb el Makefile contingut al directori /FONTS
"



cp -r ${ORIGIN:?}/src ./FONTS

cd FONTS
ln -s src/main/java/edu/upc/prop/cluster125 CODE_HERE


cd ..

if [ -d ./FONTS/src/data ]; then
  mv ./FONTS/src/data ./EXE
else
  mkdir ./EXE/data
fi

cp -r ${ORIGIN:?}/Documents/* ./DOCS

cd "${ORIGIN:?}"

JAVA_HOME=/usr/lib/jvm/java-11-openjdk ${ORIGIN:?}/gradlew shadowJar
JAVA_HOME=/usr/lib/jvm/java-11-openjdk ${ORIGIN:?}/gradlew javadoc

mv ${ORIGIN:?}/build/libs/gradlepoc-1.0-SNAPSHOT-all.jar "${DESTINATION:?}"/"${FOLDER_NAME:?}"/EXE/"${PROGRAM_NAME:?}".jar

cp -r ${ORIGIN:?}/build/docs/javadoc "${DESTINATION:?}"/"${FOLDER_NAME:?}"/DOCS/




echo "#!/bin/bash

java -jar ${PROGRAM_NAME:?}.jar
" > "${DESTINATION:?}"/"${FOLDER_NAME:?}"/EXE/execute.sh