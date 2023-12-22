#!/bin/bash

# Exit on error and undefined variable
set -euo pipefail

# Function to create index files with provided folder and message
create_index () {
  echo "${2:?}" > "${1:?}/index.txt"
}

# Function to check if JAVA_HOME is a valid Java path
check_java_home() {
  if [ -d "/usr/lib/jvm/java-11-openjdk" ] ; then

    JAVA_CMD="/usr/lib/jvm/java-11-openjdk"
  else
    JAVA_CMD=$JAVA_HOME
  fi
}

# Variables for paths and names
ORIGIN='/home/akira/IdeaProjects/subgrup-prop12.5'
DESTINATION='/home/akira/Documents'
FOLDER_NAME='PROP_ENTREGA_FINAL'
PROGRAM_NAME="layout_generator"

# Ensuring the origin directory exists
cd "${ORIGIN:?}"

# Remove existing destination folder if it exists
if [ -d "${DESTINATION:?}/${FOLDER_NAME:?}" ]; then
  rm -r "${DESTINATION:?}/${FOLDER_NAME:?}"
fi

# Create new delivery folder
mkdir -p "${DESTINATION:?}/${FOLDER_NAME:?}"

# Move to the newly created folder
cd "${DESTINATION:?}/${FOLDER_NAME:?}"

# Create team file
cat <<EOF > equip.txt
Allue Mengual, Nil
Bravo Melia, Andreu
Cobos Navarro, Ivan
Granados Martin, Arnau

nil.allue@estudiantat.upc.edu
andreu.bravo@estudiantat.upc.edu
ivan.cobos@estudiantat.upc.edu
arnau.granados@estudiantat.upc.edu
EOF

# Create directories and index files
mkdir -p ./FONTS ./DOCS ./EXE
create_index FONTS "Classes de model i Controlador de Domini i Driver de Funcionalitat"
create_index DOCS "DOCS:

                  Conté tota la documentació del projecte:
                   - diagrama de casos d'us
                   - diagrama de classes de model
                   - doc amb: descripcio dels casos d'us, breu descripcio dels atributs i metodes de les classes de model, relacio de les classes implementades per cada membre de l'equip, descripcio de les estructures de dades i algorismes emprats per implementar les funcionalitats de l'entrega.
                   - javadoc
                  "
create_index EXE "Directori de sortida pels .jar (driver ja compilat) i els .class . Tots els arxius sortiran un cop s'hagi compilat amb el Makefile contingut al directori /FONTS
                 "

# Copy source to FONTS and create symlink
cp -r "${ORIGIN:?}/src" ./FONTS
cd FONTS && ln -s src/main/java/edu/upc/prop/cluster125 CODE_HERE

mkdir libs
cp "${ORIGIN:?}"/GradlePOC-1.0-SNAPSHOT/lib/gson-2.8.9.jar libs/

# Add Makefile to FONTS directory
cat <<EOF >Makefile
SRC_DIR = ./src/main/java
BUILD_DIR = ../EXE/build
JAR_NAME = layout_generator.jar
EXE_DIR = ../EXE
LIB_DIR = ./libs
CLASSPATH = \$(LIB_DIR)/*.jar

JAVAC = javac
JAR = jar

SOURCES = \$(wildcard \$(SRC_DIR)/edu/upc/prop/cluster125/**/*.java) \$(wildcard \$(SRC_DIR)/edu/upc/prop/cluster125/**/**/*.java)
CLASSES = \$(SOURCES:.java=.class)

all: \$(BUILD_DIR) \$(JAR_NAME)

\$(BUILD_DIR):
	@mkdir -p \$@

\$(JAR_NAME): \$(SOURCES)
	@echo "Compiling Java sources..."
	\$(JAVAC) -d \$(BUILD_DIR) -cp \$(CLASSPATH) \$(SOURCES)
	@echo "Creating jar file..."
	@mkdir -p \$(EXE_DIR)
	\$(JAR) cvfe \$(EXE_DIR)/\$(JAR_NAME) edu.upc.prop.cluster125.presentation.CtrlPresentacio -C \$(BUILD_DIR) .

clean:
	@echo "Cleaning up..."
	@rm -rf \$(BUILD_DIR) \$(EXE_DIR)/\$(JAR_NAME)
EOF

cd ..

# Check JAVA_HOME validity
check_java_home

cd "${ORIGIN:?}"
# Compile and move the Java program
"${ORIGIN:?}/gradlew" shadowJar -Dorg.gradle.java.home="$JAVA_CMD"
#"${ORIGIN:?}/gradlew" javadoc -Dorg.gradle.java.home="$JAVA_CMD"
mv "${ORIGIN:?}/build/libs/gradlepoc-1.0-SNAPSHOT-all.jar" "${DESTINATION:?}/${FOLDER_NAME:?}/EXE/${PROGRAM_NAME:?}.jar"
cp -r "${ORIGIN:?}/build/docs/javadoc" "${DESTINATION:?}/${FOLDER_NAME:?}/DOCS/"

cp -a "${ORIGIN:?}/Documents/." "${DESTINATION:?}/${FOLDER_NAME:?}/DOCS/"

# Create the execute script with correct execution permissions
echo "#!/bin/bash
java -jar ${PROGRAM_NAME:?}.jar
" > "${DESTINATION:?}/${FOLDER_NAME:?}/EXE/execute.sh"

chmod +x "${DESTINATION:?}/${FOLDER_NAME:?}/EXE/execute.sh"
