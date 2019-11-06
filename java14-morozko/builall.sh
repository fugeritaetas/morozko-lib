#!/bin/bash
FILES=*
CURR_DIR=${PWD}
for f in $FILES
do
  echo "Processing ${CURR_DIR}/$f file..."
  # take action on each file. $f store current file name
  cd ${CURR_DIR}/$f
  if [[ -e ${CURR_DIR}/$f ]]; then
  	ant
  fi
done
