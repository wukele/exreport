@echo off
copy zlib.dll %systemroot%\system32\
regsvr32 dsoframer.ocx /s
regsvr32 FileHelper.dll /s

echo ×é¼þ×¢²áÍê±Ï
pause