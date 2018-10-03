#!/bin/bash
size=$(du -sb JavaThreading.Log | awk '{ print $1 }')
echo $size
if [ $size -lt 10 ]
then
	echo "log file size is less"
else
	echo "Archiving"
mv "JavaThreading.Log" "archive/"
fi
exit 0;
