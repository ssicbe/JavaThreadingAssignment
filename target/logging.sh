#!/bin/bash
size=$(du -sb JavaThreading.Log | awk ' { print $1 } ')
if [ $size -lt 10 ]
then
echo " log file size is less to be archived"
else
echo "Archiving Log File"
mv "JavaThreading.Log" "archive/"
fi
exit 0;
